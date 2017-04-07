import java.awt.Color;
import java.util.Random;

public class Projectiles {
	private EZCircle[] frames;
	private EZGroup bullet;
	int x, y;
	int bounces;
	float speed;
	private boolean onScreen;
	private long time;
	private int maxFrames = 50;
	private int frameIndex;
	
	public Projectiles(int posx, int posy) {
		Random rg = new Random();
		x = posx;
		y = posy;
		frameIndex = 0;
		bullet = EZ.addGroup();
		frames = new EZCircle[maxFrames];
		for (int i = 0; i < maxFrames; i++) {
			frames[i] = EZ.addCircle(posx, posy, rg.nextInt(100) + 50, rg.nextInt(100) + 50,
					new Color(rg.nextInt(250), rg.nextInt(250), rg.nextInt(250)), true);
			System.out.println(frames[i]);
			bullet.addElement(frames[i]);
		}
	}

	void hide() {
		bullet.hide();
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
