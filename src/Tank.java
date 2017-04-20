import java.awt.Color;
import java.util.ArrayList;

// Class that implements the Tank units that each player controls in the Game
// The tank class is a thin wrapper around an EZImage that represents the tank
// sprite

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

	// Constructor for tank objects that takes in a string corresponding to the
	// file
	// name of the tank sprite image, the starting x and y coordinates, a player
	// ID
	// for managing spawned projectile collisions, and a char array that holds
	// the
	// key character values that will be used for controlling the tank movement
	public Tank(String imageName, int _x, int _y, int _playerID, char[] directions) {
		tankSprite = EZ.addImage(imageName, _x, _y);
		shield = EZ.addCircle(_x, _y, 100, 100, Color.BLUE, false);
		shield.hide();
		hp = 100;
		coolDownTime = 1500;
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

	// Method that allows tanks to be damaged when hit by a projectile
	public void takeDamage(int amount) {
		hp -= amount;
		if (hp <= 0) {
			dead = true;
		}
	}

	// Method for enabling tank movement via key presses
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

		// These if statements are to make sure that the tanks can't
		// move off the edge of the screen
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

	// Method that facilitates collision between tanks and any obstacles that
	// may be distributed about the map
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
				tankSprite.translateTo(x, d - 16 - (h / 2));
				shield.translateTo(x, d - 16 - (h / 2));
			}
			if (topEdge <= d + 16 && bottomEdge >= d + 16 && tankSprite.getXCenter() >= a - 16
					&& tankSprite.getXCenter() <= a + 16) {
				tankSprite.translateTo(x, d + 16 + h / 2);
				shield.translateTo(x, d + 16 + h / 2);
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

	// Method that facilitates collision detection between tanks and projectiles
	public boolean collideWithProjectiles(Projectiles projectile) {
		return tankSprite.isPointInElement(projectile.getX(), projectile.getY())
				&& playerID != projectile.getSpawnedBy();
	}

	// Method that facilitates collision between the two tanks so that they
	// cannot
	// simply pass through one another and will instead bounce off of one
	// another
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

	// Method that enables the tanks to fire projectiles at each other
	// When a tank fires a projectile, that projectile is moved to the
	// tanks position, and important information is passed on to the
	// projectile such as which player fired it, that it is on screen,
	// its angle of rotation for ricochet mechanics, it's ricochet count,
	// and it's movement speed (direction)
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
	
	// Method for getting the hp of a Tank object
	public int getHealth() {
		return hp;
	}

	// Method for keeping track of whether a tank is dead or alive
	public boolean isDead() {
		return dead;
	}

	// Get the rotation of the tank sprite
	public double getRotation() {
		return tankSprite.getRotation();
	}

	// Activate the shield when a tank collides with a shield power up
	public void activateShield() {
		shielded = true;
		shield.show();
	}
	
	// Deactivate the shield once the shield power up has timed out
	public void deactivateShield() {
		shielded = false;
		shield.hide();
	}

	// Upgrade the tank's weapon power when it collides with a weapon power up
	public void upgradeWeapon() {
		weaponPower = weaponPower + 20;
	}

	// Downgrade the tank's weapon power once it's weapon power up times out
	public void downgradeWeapon() {
		weaponPower -= 20;
	}
	// Upgrade the tank's speed when it collides with a speed boost
	public void upgradeSpeed() {
		speed = speed * 2;
	}
	
	// Downgrade the tank's speed once it's speed power up times out
	public void downgradeSpeed() {
		
		speed = speed / 2;
		
	}
	
	// Heal the tank's HP when it collides with a Med Kit
	public void repairTank() {
		hp = hp + 10;
	}

	// The following methods are for getting the edges of the tank sprite image
	// for collision detection
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

	// Wrappers around the EZImage methods for getting (x, y) coordinates
	public int getXcenter() {
		return tankSprite.getXCenter();
	}

	public int getYcenter() {
		return tankSprite.getYCenter();
	}

	// Wrapper around the EZImage method for enabling certain types of
	// collisions
	public boolean isPointInElement(int posx, int posy) {
		return tankSprite.isPointInElement(posx, posy);
	}

}
