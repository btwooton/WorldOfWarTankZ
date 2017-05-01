import java.awt.Color;
import java.util.ArrayList;

// Class that implements the Tank units that each player controls in the Game
// The tank class is a thin wrapper around an EZImage that represents the tank
// sprite

public class Tank {

	private EZImage tankSprite;
	private GiffAnime shield;
	private GiffAnime tankAnime;
	private GiffAnime canonSmoke;
	private GiffAnime explosion;
	private int hp;
	private final int COOL_DOWN_TIME, SHIELD_TIME_OUT, POWER_TIME_OUT, SPEED_TIME_OUT;
	private final int EXPLOSION_TIME;
	private long timeGotShield, timeGotPower, timeGotSpeed, timeOfLastShot;
	private long timeGotHit;
	private double speed, turnSpeed;
	private double x, y;
	private int w, h;
	private int weaponPower;
	private char directionUp, directionLeft, directionDown, directionRight;
	private boolean hasShield, hasSpeed, hasPower, isDamaged, isMoving;
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
		shield = new GiffAnime("Sheild_224_3.png", _x, _y, 224, 224, 100, 3);
		tankAnime = new GiffAnime("tankAnime.png", _x, _y, 75, 56, 100, 10);
		canonSmoke = new GiffAnime("smoke_32_7.png", _x, _y, 32, 32, 100, 7);
		explosion = new GiffAnime("blueExplosion_60_7.png", _x, _y, 60, 60, 100, 7);
		shield.hide();
		tankAnime.hide();
		canonSmoke.hide();
		explosion.hide();
		hp = 100;
		COOL_DOWN_TIME = 1500;
		SPEED_TIME_OUT = 15000;
		SHIELD_TIME_OUT = 15000;
		POWER_TIME_OUT = 15000;
		EXPLOSION_TIME = 700;
		timeGotShield = 0;
		timeGotSpeed = 0;
		timeGotPower = 0;
		timeOfLastShot = 0;
		timeGotHit = 0;
		speed = 2;
		turnSpeed = 1;
		x = _x;
		y = _y;
		w = tankSprite.getWidth();
		h = tankSprite.getHeight();
		weaponPower = 8;
		
		hasShield = false;
		hasSpeed = false;
		hasPower = false;
		isDamaged = false;
		isMoving = false;
		
		playerID = _playerID;
		dead = false;
		directionUp = directions[0];
		directionLeft = directions[1];
		directionDown = directions[2];
		directionRight = directions[3];
		
	}

	// Method that allows tanks to be damaged when hit by a projectile
	public void takeDamage(int amount) {
		if (hasShield) {
			hp -= amount/4;
		}
		else {
			hp -= amount;
		}
		if (hp <= 0) {
			dead = true;
		}
		if (hp < 100) {
			isDamaged = true;
		}
		timeGotHit = System.currentTimeMillis();
		explosion.show();
	}

	// Method for enabling tank movement via key presses
	public void moveAround(int leftArenaEdge, int topArenaEdge, int rightArenaEdge, int bottomArenaEdge) {
		int topEdge = (int) y - (h / 2);
		int bottomEdge = (int) y + (h / 2);
		int leftEdge = (int) x - (w / 2);
		int rightEdge = (int) x + (w / 2);

		if (EZInteraction.isKeyDown(directionUp)) {
			tankSprite.moveForward(speed);
			shield.moveForward((float)speed);
			tankAnime.moveForward((float)speed);
			canonSmoke.moveForward((float)speed);
			explosion.moveForward((float)speed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
			tankSprite.hide();
			tankAnime.show();
			isMoving = true;
			
		}
		if (EZInteraction.isKeyDown(directionLeft)) {
			tankSprite.turnLeft(turnSpeed);
			shield.turnLeft(turnSpeed);
			tankAnime.turnLeft(turnSpeed);
			canonSmoke.turnLeft(turnSpeed);
			explosion.turnLeft(turnSpeed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
			tankSprite.hide();
			tankAnime.show();
			isMoving = true;
		}

		if (EZInteraction.isKeyDown(directionDown)) {
			tankSprite.moveForward(-speed);
			shield.moveForward((float)-speed);
			tankAnime.moveForward((float)-speed);
			canonSmoke.moveForward((float)-speed);
			explosion.moveForward((float)-speed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
			tankSprite.hide();
			tankAnime.show();
			isMoving = true;
		}

		if (EZInteraction.isKeyDown(directionRight)) {
			tankSprite.turnRight(turnSpeed);
			shield.turnRight(turnSpeed);
			tankAnime.turnRight(turnSpeed);
			canonSmoke.turnRight(turnSpeed);
			explosion.turnRight(turnSpeed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
			tankSprite.hide();
			tankAnime.show();
			isMoving = true;
		}
		
		if (EZInteraction.wasKeyReleased(directionUp)) {
			tankSprite.show();
			tankAnime.hide();
			isMoving = false;	
		}
		
		if (EZInteraction.wasKeyReleased(directionLeft)) {
			tankSprite.show();
			tankAnime.hide();
			isMoving = false;
		}

		if (EZInteraction.wasKeyReleased(directionDown)) {
			tankSprite.show();
			tankAnime.hide();
			isMoving = false;
		}

		if (EZInteraction.wasKeyReleased(directionRight)) {
			tankSprite.show();
			tankAnime.hide();
			isMoving = false;
		}
		// These if statements are to make sure that the tanks can't
		// move off the edge of the screen
		if (bottomEdge > bottomArenaEdge) {
			tankSprite.translateTo(x, bottomArenaEdge - (h / 2));
			shield.translateTo(x, bottomArenaEdge - (h / 2));
			tankAnime.translateTo(x, bottomArenaEdge - (h / 2));
			canonSmoke.translateTo(x, bottomArenaEdge - (h / 2));
			explosion.translateTo(x, bottomArenaEdge - (h / 2));
		}
		if (topEdge < topArenaEdge) {
			tankSprite.translateTo(x, topArenaEdge + h / 2);
			shield.translateTo(x, topArenaEdge + h / 2);
			tankAnime.translateTo(x, topArenaEdge + h / 2);
			canonSmoke.translateTo(x,  topArenaEdge + h / 2);
			explosion.translateTo(x,  topArenaEdge + h / 2);
		}
		if (leftEdge < leftArenaEdge) {
			tankSprite.translateTo(leftArenaEdge + w / 2, y);
			shield.translateTo(leftArenaEdge + w / 2, y);
			tankAnime.translateTo(leftArenaEdge + w / 2, y);
			canonSmoke.translateTo(leftArenaEdge + w / 2, y);
			explosion.translateTo(leftArenaEdge + w / 2, y);
		}
		if (rightEdge > rightArenaEdge) {
			tankSprite.translateTo(rightArenaEdge - (w / 2), y);
			shield.translateTo(rightArenaEdge - (w / 2), y);
			tankAnime.translateTo(rightArenaEdge - (w / 2), y);
			canonSmoke.translateTo(rightArenaEdge - (w / 2), y);
			explosion.translateTo(rightArenaEdge - (w / 2), y);
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
				tankAnime.translateTo(x, d - 16 - (h / 2));
				canonSmoke.translateTo(x, d - 16 - (h / 2));
				explosion.translateTo(x, d - 16 - (h / 2));
			}
			if (topEdge <= d + 16 && bottomEdge >= d + 16 && tankSprite.getXCenter() >= a - 16
					&& tankSprite.getXCenter() <= a + 16) {
				tankSprite.translateTo(x, d + 16 + h / 2);
				shield.translateTo(x, d + 16 + h / 2);
				tankAnime.translateTo(x, d + 16 + h / 2);
				canonSmoke.translateTo(x, d + 16 + h / 2);
				explosion.translateTo(x, d + 16 + h / 2);
			}
			if (leftEdge <= a + 16 && rightEdge >= a + 16 && tankSprite.getYCenter() >= d - 16
					&& tankSprite.getYCenter() <= d + 16) {
				tankSprite.translateTo(a + 16 + w / 2, y);
				shield.translateTo(a + 16 + w / 2, y);
				tankAnime.translateTo(a + 16 + w / 2, y);
				canonSmoke.translateTo(a + 16 + w / 2, y);
				explosion.translateTo(a + 16 + w / 2, y);
			}
			if (rightEdge >= a - 16 && leftEdge <= a - 16 && tankSprite.getYCenter() >= d - 16
					&& tankSprite.getYCenter() <= d + 16) {
				tankSprite.translateTo(a - 16 - (w / 2), y);
				shield.translateTo(a - 16 - (w / 2), y);
				tankAnime.translateTo(a - 16 - (w / 2), y);
				canonSmoke.translateTo(a - 16 - (w / 2), y);
				explosion.translateTo(a - 16 - (w / 2), y);
			}
		}
	}

	// Method that facilitates collision detection between tanks and projectiles
	public boolean collideWithProjectiles(Projectiles projectile) {
		if (projectile.isUpgraded()) {
			if (((tankSprite.isPointInElement(projectile.getLeftEdge(), projectile.getTopEdge()))
					|| (tankSprite.isPointInElement(projectile.getLeftEdge(), projectile.getBottomEdge()))
					|| (tankSprite.isPointInElement(projectile.getRightEdge(), projectile.getTopEdge()))
					|| (tankSprite.isPointInElement(projectile.getRightEdge(),
							projectile.getBottomEdge()))) && playerID != projectile.getSpawnedBy()) {
				return true;
			} else {
				return false;
			}
		}
		else {
			return tankSprite.isPointInElement(projectile.getX(), projectile.getY()) &&
					playerID != projectile.getSpawnedBy();
		}
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
			tankAnime.translateTo(x - 2, y + 2);
			shield.translateTo(x - 2, y + 2);
			canonSmoke.translateTo(x - 2, y + 2);
			explosion.translateTo(x - 2, y + 2);
		}
		if (isOverlapXFromLeft && isOverlapYFromTop) {
			tankSprite.translateTo(x - 2, y - 2);
			tankAnime.translateTo(x - 2, y - 2);
			shield.translateTo(x - 2, y - 2);
			canonSmoke.translateTo(x - 2, y - 2);
			explosion.translateTo(x - 2, y - 2);
		}
		if (isOverlapXFromRight && isOverlapYFromBottom) {
			tankSprite.translateTo(x + 2, y + 2);
			tankAnime.translateTo(x + 2, y + 2);
			shield.translateTo(x + 2, y + 2);
			canonSmoke.translateTo(x + 2, y + 2);
			explosion.translateTo(x + 2, y + 2);
		}
		if (isOverlapXFromRight && isOverlapYFromTop) {
			tankSprite.translateTo(x + 2, y - 2);
			tankAnime.translateTo(x + 2, y - 2);
			shield.translateTo(x + 2, y - 2);
			canonSmoke.translateTo(x + 2, y - 2);
			explosion.translateTo(x + 2, y - 2);
		}

	}

	// Method that enables the tanks to fire projectiles at each other
	// When a tank fires a projectile, that projectile is moved to the
	// tanks position, and important information is passed on to the
	// projectile such as which player fired it, that it is on screen,
	// its angle of rotation for ricochet mechanics, it's ricochet count,
	// and it's movement speed (direction)
	public boolean fireProjectile(Projectiles projectile) {

		if (System.currentTimeMillis() - timeOfLastShot >= COOL_DOWN_TIME) {
			projectile.translateTo(x, y);

			projectile.setAngle(Math.floorMod((int) tankSprite.getRotation(), 360));

			projectile.setOnScreen();
			projectile.setSpawnedBy(playerID);
			projectile.setFirePower(weaponPower);
			projectile.resetSpeed();
			projectile.resetRicochetCount(1);
			timeOfLastShot = System.currentTimeMillis();
			if (hasPower) {
				projectile.powerUp();
			}
			if (!hasPower) {
				projectile.powerDown();
			}
			canonSmoke.show();
			return true;
		}
		return false;

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
		if (!hasShield) {
			hasShield = true;
			shield.show();
			timeGotShield = System.currentTimeMillis();
		}
	}
	
	// Deactivate the shield once the shield power up has timed out
	public void deactivateShield() {
		if (hasShield) {
			hasShield = false;
			shield.hide();
		}
	}
	
	// Animate the shield while it is activated
	public void animateShield() {
		if (hasShield) {
			shield.animate();
		}
	}
	
	// Animate the tank sprite while it is moving
	public void animateTank() {
		if (isMoving) {
			tankAnime.animate();
		}
	}

	// Upgrade the tank's weapon power when it collides with a weapon power up
	public void upgradeWeapon() {
		if (!hasPower) {
			weaponPower = weaponPower + 20;
			hasPower = true;
			timeGotPower = System.currentTimeMillis();
		}
	}

	// Downgrade the tank's weapon power once it's weapon power up times out
	public void downgradeWeapon() {
		if (hasPower) {
			hasPower = false;
			weaponPower -= 20;
		}
	}
	// Upgrade the tank's speed when it collides with a speed boost
	public void upgradeSpeed() {
		if (!hasSpeed) {
			speed = speed * 2;
			turnSpeed = turnSpeed * 2;
			hasSpeed = true;
			timeGotSpeed = System.currentTimeMillis();
		}
	}
	
	// Downgrade the tank's speed once it's speed power up times out
	public void downgradeSpeed() {
		if (hasSpeed) {
			speed = speed / 2;
			turnSpeed = turnSpeed / 2;
			hasSpeed = false;
		}
		
	}
	
	// Heal the tank's HP when it collides with a Med Kit
	public void repairTank() {
		if (isDamaged) {
			hp = hp + 10;
			if (hp >= 100) {
				isDamaged = false;
			}
		}
	}
	
	// Method that allows for the Tanks to determine if any of their active power
	// ups have timed-out (ran out of duration)
	public void checkPowerUps() {
		if (System.currentTimeMillis() > timeGotSpeed + SPEED_TIME_OUT && hasSpeed) {
			downgradeSpeed();
		}
		if (System.currentTimeMillis() > timeGotShield + SHIELD_TIME_OUT && hasShield) {
			deactivateShield();
		}
		if (System.currentTimeMillis() > timeGotPower + POWER_TIME_OUT && hasPower) {
			downgradeWeapon();
		}
	}
	
	public void canonSmoke() {
		if (System.currentTimeMillis() - timeOfLastShot >= COOL_DOWN_TIME) {
			canonSmoke.hide();
		}
		canonSmoke.animate();
	}
	
	public void explosion() {
		if (System.currentTimeMillis() - timeGotHit >= EXPLOSION_TIME) {
			explosion.hide();
		}
		explosion.animate();
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
	
	
	public boolean isTankMoving() {
		return isMoving;
	}

}
