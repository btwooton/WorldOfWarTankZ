import java.awt.Color;
import java.util.Random;

public class DummyProjectiles {
	
	private EZCircle bullet;
	private int x, y;

	private float speed;
	private boolean onScreen;
	private int spawnedBy;
	private int firePower;
	private int ricochetCount;


	public DummyProjectiles(int posx, int posy) {
		Random rg = new Random();
		x = posx;
		y = posy;
		speed = 3.0f;
		onScreen = false;
		spawnedBy = 0;
		ricochetCount = 1;
		bullet = EZ.addCircle(x, y, 20, 20, Color.RED, true);
		
	}
	void moveForward() {
		bullet.moveForward(speed);
	}
	
	void translateTo(double x, double y) {
		bullet.translateTo(x, y);
	}

	int getX() {
		return bullet.getXCenter();
	}

	int getY() {
		return bullet.getYCenter();
	}
	
	void setAngle(double rotationValue) {
		bullet.rotateTo(rotationValue);
	}
	
	double getAngle() {
		return bullet.getRotation();
	}
	
	int getTopEdge() {
		return bullet.getYCenter() - bullet.getHeight()/2;
	}
	
	int getBottomEdge() {
		return bullet.getYCenter() + bullet.getHeight()/2;
	}
	
	int getRightEdge() {
		return bullet.getXCenter() + bullet.getWidth()/2;
	}
	
	int getLeftEdge() {
		return bullet.getXCenter() - bullet.getWidth()/2;
	}
	
	void setOffScreen() {
		onScreen = false;
	}
	
	boolean isOnScreen() {
		return onScreen;
	}
	
	void setOnScreen() {
		onScreen = true;
	}
	
	void setSpawnedBy(int playerID) {
		spawnedBy = playerID;
	}
	
	int getSpawnedBy() {
		return spawnedBy;
	}
	
	void setFirePower(int power) {
		firePower = power;
	}
	
	int getFirePower() {
		return firePower;
	}
	
	int getRicochetCount() {
		return ricochetCount;
	}
	
	void resetRicochetCount(int value) {
		ricochetCount = value;
	}
	
	void ricochet(int worldHeight, int worldWidth) {
		if (ricochetCount > 0) {
			ricochetCount--;
		}
		if (this.getBottomEdge() >= worldHeight) {
			bullet.rotateTo(180 - bullet.getRotation());
			speed = -speed;
			
		}
		else if (this.getLeftEdge() <= 0) {
			bullet.rotateTo(360 - bullet.getRotation());
			speed = -speed;
		}
		else if (this.getRightEdge() >= worldWidth) {
			bullet.rotateTo(360 - bullet.getRotation());
			speed = -speed;
		}
		else if (this.getTopEdge() <= 0) {
			bullet.rotateTo(540 - bullet.getRotation());
			speed = -speed;
		}
	}
	
	void resetSpeed() {
		speed = Math.abs(speed);
	}

}
