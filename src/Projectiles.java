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
		
		frames = new EZCircle[6];
		for (int i = 0; i < 6; i++) {
			frames[i] = EZ.addCircle(posx, posy, rg.nextInt(15) + 5, rg.nextInt(15) + 5,
					new Color(rg.nextInt(250), rg.nextInt(250), rg.nextInt(250)), true);
			frames[i].hide();
		}
		numFrames = frames.length;
		setLoop(true);
		starting = true;
		stopped = false;
		visibility = true;
	}
	void translateTo(int posx, int posy){
		
	}

}
