import java.awt.Color;
import java.util.Random;

public class Projectiles {
	EZGroup bullet;
	private EZCircle[] frames;
	int x, y;
	float directionX;
	float directionY;
	int bounces;
	float speed;
	private int numFrames;
	private int maxFrames = 50;
	private long duration;
	private long starttime;
	private boolean loopIt;
	private boolean starting;
	private boolean stopped;
	private boolean visibility;

	public Projectiles(int posx, int posy) {
		Random rg = new Random();
		x = posx;
		y = posy;
		bullet = EZ.addGroup();

		frames = new EZCircle[maxFrames];
		for (int i = 0; i < maxFrames; i++) {
			frames[i] = EZ.addCircle(posx, posy, rg.nextInt(100) + 50, rg.nextInt(100) + 50,
					new Color(rg.nextInt(250), rg.nextInt(250), rg.nextInt(250)), true);
			frames[i].hide();
			bullet.addElement(frames[i]);
			if (i >= maxFrames - 1) {
				i = 0;
			}

		}
		numFrames = frames.length;
		setLoop(true);
		starting = true;
		stopped = false;
		visibility = true;
	}

	void translateBy(int posx, int posy) {
		bullet.translateBy(posx, posy);

	}

	void setLoop(boolean loop) {
		loopIt = loop;
	}

	void restart() {
		starting = true;
	}

	void stop() {
		stopped = true;
	}

	void hide() {
		visibility = false;
		for (int i = 0; i < numFrames; i++)
			frames[i].hide();
	}

	void show() {
		visibility = true;
	}

	boolean go() {
		if (stopped)
			return false;
		if (starting) {
			starttime = System.currentTimeMillis();
			starting = false;
		}
		if ((System.currentTimeMillis() - starttime) > duration) {
			if (loopIt) {
				restart();
				return true;
			}

			return false;
		}

		float normTime = (float) (System.currentTimeMillis() - starttime) / (float) duration;

		int currentFrame = (int) (((float) numFrames) * normTime);
		if (currentFrame > numFrames - 1)
			currentFrame = numFrames - 1;

		for (int i = 0; i < numFrames; i++) {
			if (i != currentFrame)
				frames[i].hide();
		}

		if (visibility)
			frames[currentFrame].show();
		else
			frames[currentFrame].hide();
		return true;

	}

}
