import java.awt.Color;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Projectiles {
	private EZCircle[] frames;
	private EZGroup bullet;
	private String textFile1;
	int x, y;
	int bounces;
	float speed;
	private boolean onScreen;
	private int maxFrames = 6;
	private int frameIndex;
	private int spawnedBy;
	private int firePower;
	private int ricochetCount;
	private int redIndex, greenIndex, blueIndex, widthIndex, heightIndex;
	Scanner s;

	public Projectiles(int posx, int posy, String text) throws java.io.IOException {
		Random rg = new Random();
		textFile1 = text;
		s = new Scanner(new FileReader(textFile1));
		x = posx;
		y = posy;
		frameIndex = 0;
		speed = 3.0f;
		onScreen = false;
		spawnedBy = 0;
		bullet = EZ.addGroup();
		frames = new EZCircle[maxFrames];

		for (int i = 0; i < maxFrames; i++) {
			widthIndex = s.nextInt();
			heightIndex = s.nextInt();
			redIndex = s.nextInt();
			greenIndex = s.nextInt();
			blueIndex = s.nextInt();
			frames[i] = EZ.addCircle(posx, posy, widthIndex, heightIndex, new Color(redIndex, greenIndex, blueIndex),
					true);
			bullet.addElement(frames[i]);
			System.out.println(frames[i]);
			System.out.println(
					widthIndex + " " + heightIndex + " " + redIndex + " " + greenIndex + " " + blueIndex + "\n");
		}
	}

	void hide() {
		bullet.hide();
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


	void fire() {
		if (frameIndex < maxFrames) {
			hide();
			frames[frameIndex].show();
			frameIndex++;
		} else {
			frameIndex = 0;

		}
	}
}
