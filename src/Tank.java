import java.awt.Color;
import java.util.ArrayList;

public class Tank {

	private EZImage tankSprite;
	private EZCircle shield;
	private int hp;
	private long coolDownTime;
	long timeOfLastShot;
	private double speed;
	private double x, y;
	private int w, h;
	private int weaponPower;
	private char directionUp, directionLeft, directionDown, directionRight;
	private boolean shielded;
	private int playerID;
	private boolean dead;

	public Tank(String imageName, int _x, int _y, int _playerID, char[] directions) {
		tankSprite = EZ.addImage(imageName, _x, _y);
		shield = EZ.addCircle(_x, _y, 100, 100, Color.BLUE, false);
		shield.hide();
		hp = 100;
		coolDownTime = 3000;
		timeOfLastShot = 0;
		speed = 2;
		x = _x;
		y = _y;
		w = tankSprite.getWidth();
		h = tankSprite.getHeight();
		weaponPower = 8;
		shielded = false;
		playerID = _playerID;
		dead = false;
		directionUp = directions[0];
		directionLeft = directions[1];
		directionDown = directions[2];
		directionRight = directions[3];
		
	}

	public void takeDamage(int amount) {
		hp -= amount;
		if (hp <= 0) {
			dead = true;
		}
	}

	public void moveAround(int leftArenaEdge, int topArenaEdge, int rightArenaEdge, int bottomArenaEdge) {
		int topEdge = (int) y - (h / 2);
		int bottomEdge = (int) y + (h / 2);
		int leftEdge = (int) x - (w / 2);
		int rightEdge = (int) x + (w / 2);

		if (EZInteraction.isKeyDown(directionUp)) {
			tankSprite.moveForward(speed);
			shield.moveForward(speed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		if (EZInteraction.isKeyDown(directionLeft)) {
			tankSprite.turnLeft(1);
			shield.turnLeft(1);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		if (EZInteraction.isKeyDown(directionDown)) {
			tankSprite.moveForward(-speed);
			shield.moveForward(-speed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		if (EZInteraction.isKeyDown(directionRight)) {
			tankSprite.turnRight(1);
			shield.turnRight(1);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}

		if (bottomEdge > bottomArenaEdge) {
			tankSprite.translateTo(x, bottomArenaEdge - (h / 2));
			shield.translateTo(x, bottomArenaEdge - (h / 2));
		}
		if (topEdge < topArenaEdge) {
			tankSprite.translateTo(x, topArenaEdge + h / 2);
			shield.translateTo(x, topArenaEdge + h / 2);
		}
		if (leftEdge < leftArenaEdge) {
			tankSprite.translateTo(leftArenaEdge + w / 2, y);
			shield.translateTo(leftArenaEdge + w / 2, y);
		}
		if (rightEdge > rightArenaEdge) {
			tankSprite.translateTo(rightArenaEdge - (w / 2), y);
			shield.translateTo(rightArenaEdge - (w / 2), y);
		}
	}
	
	public void TankObstacle(ArrayList<Integer> xS, ArrayList<Integer> yS) {
		for (int i = 0; i < xS.size(); i++) {
			int a = xS.get(i);
			int d = yS.get(i);
			int topEdge = (int) y - (h / 2);
			int bottomEdge = (int) y + (h / 2);
			int leftEdge = (int) x - (w / 2);
			int rightEdge = (int) x + (w / 2);
			if (bottomEdge >= d - 16 && topEdge <= d - 16 && tankSprite.getXCenter() >= a - 16
					&& tankSprite.getXCenter() <= a + 16) {
				tankSprite.translateTo(x, d-16 - (h / 2));
				shield.translateTo(x, d-16 - (h / 2));
			}
			if (topEdge <= d + 16 && bottomEdge >= d + 16 && tankSprite.getXCenter() >= a - 16
					&& tankSprite.getXCenter() <= a + 16) {
				tankSprite.translateTo(x, d+16 + h / 2);
				shield.translateTo(x, d+16 + h / 2);
			}
			if (leftEdge <= a + 16 && rightEdge >= a + 16 && tankSprite.getYCenter() >= d - 16
					&& tankSprite.getYCenter() <= d + 16) {
				tankSprite.translateTo(a + 16 + w / 2, y);
				shield.translateTo(a + 16 + w / 2, y);
			}
			if (rightEdge >= a - 16 && leftEdge <= a - 16 && tankSprite.getYCenter() >= d - 16
					&& tankSprite.getYCenter() <= d + 16) {
				tankSprite.translateTo(a - 16 - (w / 2), y);
				shield.translateTo(a - 16 - (w / 2), y);
			}
		}
	}

	public boolean collideWithProjectiles(Projectiles projectile) {
		return tankSprite.isPointInElement(projectile.getX(), projectile.getY())
				&& playerID != projectile.getSpawnedBy();
	}

	public void collideWithTanks(Tank other) {

		boolean isOverlapXFromLeft = this.getRightEdge() >= other.getLeftEdge() + 10
				&& this.getLeftEdge() <= other.getLeftEdge();
		boolean isOverlapXFromRight = this.getLeftEdge() <= other.getRightEdge() - 10
				&& this.getRightEdge() >= other.getRightEdge();
		boolean isOverlapYFromTop = this.getBottomEdge() >= other.getTopEdge() - 10
				&& this.getTopEdge() <= other.getTopEdge();
		boolean isOverlapYFromBottom = this.getTopEdge() <= other.getBottomEdge() + 10
				&& this.getBottomEdge() >= other.getBottomEdge();

		if (isOverlapXFromLeft && isOverlapYFromBottom) {
			tankSprite.translateTo(x - 2, y + 2);
		}
		if (isOverlapXFromLeft && isOverlapYFromTop) {
			tankSprite.translateTo(x - 2, y - 2);
		}
		if (isOverlapXFromRight && isOverlapYFromBottom) {
			tankSprite.translateTo(x + 2, y + 2);
		}
		if (isOverlapXFromRight && isOverlapYFromTop) {
			tankSprite.translateTo(x + 2, y - 2);
		}

	}

	public void fireProjectile(Projectiles projectile) {

		if (System.currentTimeMillis() - timeOfLastShot >= coolDownTime) {
			projectile.translateTo(x, y);

			projectile.setAngle(Math.floorMod((int) tankSprite.getRotation(), 360));

			projectile.setOnScreen();
			projectile.setSpawnedBy(playerID);
			projectile.setFirePower(weaponPower);
			projectile.resetSpeed();
			projectile.resetRicochetCount(1);
			timeOfLastShot = System.currentTimeMillis();
		}

	}

	public boolean isDead() {
		return dead;
	}

	public double getRotation() {
		return tankSprite.getRotation();
	}

	public void activateShield() {
		shielded = true;
		shield.show();
	}

	public void upgradeWeapon() {
		weaponPower *= 5;
	}

	public void downgradeWeapon() {
		weaponPower /= 5;
	}

	public int getLeftEdge() {
		return (int) x - (w / 2);
	}

	public int getRightEdge() {
		return (int) x + (w / 2);
	}

	public int getTopEdge() {
		return (int) y - (h / 2);
	}

	public int getBottomEdge() {
		return (int) y + (h / 2);
	}

	public int getXcenter() {
		return tankSprite.getXCenter();
	}

	public int getYcenter() {
		return tankSprite.getYCenter();
	}
	public boolean isPointInElement(int posx, int posy) {
		return tankSprite.isPointInElement(posx, posy);
	}

}
