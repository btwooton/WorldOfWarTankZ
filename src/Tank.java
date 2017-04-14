import java.awt.Color;

public class Tank {
	
	private EZImage tankSprite;
	private EZCircle shield;
	private int hp;
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
		speed = 2;
		x = _x;
		y = _y;
		w = tankSprite.getWidth();
		h = tankSprite.getHeight();
		weaponPower = 10;
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
	
	public void moveAround(int leftArenaEdge, int topArenaEdge,
			int rightArenaEdge, int bottomArenaEdge) {
		int topEdge = (int)y - (h/2);
		int bottomEdge = (int)y + (h/2);
		int leftEdge = (int)x - (w/2);
		int rightEdge = (int)x + (w/2);
		
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
			tankSprite.translateTo(x, bottomArenaEdge - (h/2));
			shield.translateTo(x, bottomArenaEdge - (h/2));
		}
		if (topEdge < topArenaEdge) {
			tankSprite.translateTo(x,topArenaEdge + h/2);
			shield.translateTo(x, topArenaEdge + h/2);
		}
		if (leftEdge < leftArenaEdge) {
			tankSprite.translateTo(leftArenaEdge + w/2, y);
			shield.translateTo(leftArenaEdge + w/2, y);
		}
		if (rightEdge > rightArenaEdge) {
			tankSprite.translateTo(rightArenaEdge - (w/2), y);
			shield.translateTo(rightArenaEdge - (w/2), y);
		}
	}
	
	public boolean collideWithProjectiles(Projectiles projectile) {
		return tankSprite.isPointInElement(projectile.getX(), projectile.getY()) &&
				playerID != projectile.getSpawnedBy();	
	}
	
	
	public void fireProjectile(Projectiles projectile) {
		
		projectile.translateTo(x, y);
		
		projectile.setAngle(Math.floorMod((int)tankSprite.getRotation(), 360));
	
		projectile.setOnScreen();
		projectile.setSpawnedBy(playerID);
		projectile.setFirePower(weaponPower);
		projectile.resetSpeed();
		projectile.resetRicochetCount(1);
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

}
