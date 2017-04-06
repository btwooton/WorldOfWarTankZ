import java.awt.Color;
import java.util.Random;

public class DummyProjectiles {
	
	private EZCircle bullet;
	private int x, y;

	private float speed;
	private boolean onScreen;


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


}
