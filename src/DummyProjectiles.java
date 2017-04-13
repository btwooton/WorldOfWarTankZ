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
		return bullet.getYCenter() - bullet.getHeight() / 2;
	}

	int getBottomEdge() {
		return bullet.getYCenter() + bullet.getHeight() / 2;
	}

	int getRightEdge() {
		return bullet.getXCenter() + bullet.getWidth() / 2;
	}

	int getLeftEdge() {
		return bullet.getXCenter() - bullet.getWidth() / 2;
	}

	void setOffScreen(int worldHeight, int worldWidth, boolean hasCollided) {

		if (this.getBottomEdge() < -20) {
			onScreen = false;
		}

		if (this.getTopEdge() > worldHeight + 20) {
			onScreen = false;
		}

		if (this.getRightEdge() < -20) {
			onScreen = false;
		}

		if (this.getLeftEdge() > worldWidth + 20) {
			onScreen = false;
		}

		if (hasCollided) {
			onScreen = false;
		}
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

		if (this.getBottomEdge() >= worldHeight && ricochetCount > 0) {
			bullet.rotateTo(180 - bullet.getRotation());
			speed = -speed;
			ricochetCount--;

		} else if (this.getLeftEdge() <= 0 && ricochetCount > 0) {
			bullet.rotateTo(360 - bullet.getRotation());
			speed = -speed;
			ricochetCount--;
		} else if (this.getRightEdge() >= worldWidth && ricochetCount > 0) {
			bullet.rotateTo(360 - bullet.getRotation());
			speed = -speed;
			ricochetCount--;
		} else if (this.getTopEdge() <= 0 && ricochetCount > 0) {
			bullet.rotateTo(540 - bullet.getRotation());
			speed = -speed;
			ricochetCount--;
		}
	}

	void resetSpeed() {
		speed = Math.abs(speed);
	}

}
