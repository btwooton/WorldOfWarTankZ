
public class Tank {
	
	private EZImage tankSprite;
	private int hp;
	private double speed;
	private double x, y;
	private int w, h;
	private int weaponPower;
	private char directionUp, directionLeft, directionDown, directionRight;
	private boolean shielded;

	
	public Tank(String imageName, int _x, int _y, char[] directions) {
		tankSprite = EZ.addImage(imageName, _x, _y);
		hp = 100;
		speed = 1;
		x = _x;
		y = _y;
		w = tankSprite.getWidth();
		h = tankSprite.getHeight();
		weaponPower = 10;
		shielded = false;
		directionUp = directions[0];
		directionLeft = directions[1];
		directionDown = directions[2];
		directionRight = directions[3];
	}
	
	public void takeDamage(float amount) {
		hp -= amount;
	}
	
	public void moveAround(int worldHeight, int worldWidth) {
		if (EZInteraction.isKeyDown(directionUp) && y > 0) {
			tankSprite.translateTo(x, y -= speed);
		}
		else if (EZInteraction.isKeyDown(directionLeft) && x > 0) {
			tankSprite.translateTo(x -= speed, y);
		}
		else if (EZInteraction.isKeyDown(directionDown) && y < worldHeight) {
			tankSprite.translateTo(x, y += speed);
		}
		else if (EZInteraction.isKeyDown(directionRight) && x < worldWidth) {
			tankSprite.translateTo(x += speed, y);
		}
	}

}
