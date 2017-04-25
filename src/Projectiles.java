import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
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
	private int threshold = 16;

	public Projectiles(int posx, int posy) {
		x = posx;
		y = posy;
		speed = 5.0f;
		onScreen = false;
		spawnedBy = 0;
		ricochetCount = 1;
		bullet = new GiffAnime("deathBall_64__3.png", posx, posy, 64, 64, 60, 3);
	}

	void moveForward() {
		bullet.moveForward(speed);
		bullet.animate();
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

	void ObstacleRicochet(ArrayList<Integer> xS, ArrayList<Integer> yS) {
		for (int i = 0; i < xS.size(); i++) {
			int a = xS.get(i);
			int d = yS.get(i);
			if (this.getLeftEdge()+20 <= a - threshold && this.getRightEdge()-20 >= a - threshold && getY() >= d - threshold && getY() <= d + threshold) {
				if (ricochetCount == 0) {
					onScreen = false;
					bullet.translateTo(-100, -100);
				}
				if (ricochetCount > 0) {
					bullet.rotateTo(360 - bullet.getRotation());
					speed = -speed;
					ricochetCount--;
				}
			} else if (this.getLeftEdge()+20 <= a + threshold && this.getRightEdge()-20 >= a + threshold && getY() >= d - threshold
					&& getY() <= d + threshold) {
				if (ricochetCount == 0) {
					onScreen = false;
					bullet.translateTo(-100, -100);
				}
				if (ricochetCount > 0) {
					bullet.rotateTo(360 - bullet.getRotation());
					speed = -speed;
					ricochetCount--;
				}
			} else if (this.getBottomEdge()-20 >= d - threshold && this.getTopEdge()+20 <= d - threshold && getX() >= a - threshold
					&& getX() <= a + threshold) {
				if (ricochetCount == 0) {
					onScreen = false;
					bullet.translateTo(-100, -100);
				}
				if (ricochetCount > 0) {
					bullet.rotateTo(180 - bullet.getRotation());
					speed = -speed;
					ricochetCount--;
				}
			} else if (this.getTopEdge()+20 <= d + threshold && this.getBottomEdge()-20 >= d + threshold && getX() >= a - threshold
					&& getX() <= a + threshold) {
				if (ricochetCount == 0) {
					onScreen = false;
					bullet.translateTo(-100, -100);
				}
				if (ricochetCount > 0) {
					bullet.rotateTo(540 - bullet.getRotation());
					speed = -speed;
					ricochetCount--;
				}
			}
		}
	}

	void resetSpeed() {
		speed = Math.abs(speed);
	}

}
