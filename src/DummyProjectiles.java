import java.awt.Color;
import java.util.Random;

public class DummyProjectiles {
	/*EZGroup bullet;
	private EZCircle[] frames;
	private EZCircle[] wepUp;*/
	
	private EZCircle bullet;
	int x, y;
	/*float directionX;
	float directionY;
	int bounces;*/
	float speed;
	boolean onScreen;
	/*private int numFrames;
	private int maxFrames = 50;
	private long duration;
	private long starttime;
	private boolean loopIt;
	private boolean starting;
	private boolean stopped;
	private boolean visibility;
	private boolean upGrade;*/

	public DummyProjectiles(int posx, int posy) {
		Random rg = new Random();
		x = posx;
		y = posy;
		speed = 3.0f;
		onScreen = false;
		bullet = EZ.addCircle(x, y, 20, 20, Color.RED, true);
		
	}
	void moveForward() {
		bullet.moveForward(speed);
	}
	
	void translateTo(double x, double y) {
		bullet.translateTo(x, y);
	}
	
	void stopProjectile() {
		bullet.moveForward(0);
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
	
	void setOnScreen() {
		onScreen = true;
	}


}
