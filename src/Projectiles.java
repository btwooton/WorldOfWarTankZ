import java.awt.Color;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Projectiles {
	private int x, y;
	private float speed;
	private boolean onScreen;
	private int spawnedBy;
	private int firePower;
	private int ricochetCount;
	GiffAnime bullet;

	public Projectiles(int posx, int posy) {
		Random rg = new Random();
		x = posx;
		y = posy;
		speed = 3.0f;
		onScreen = false;
		spawnedBy = 0;
		ricochetCount = 1;
		bullet = new GiffAnime("deathBall_96__3.png", posx, posy, 96, 100, 3);
	}

	void moveForward() {
		bullet.moveForward(speed);
		bullet.spawn();
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
