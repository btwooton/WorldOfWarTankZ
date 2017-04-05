
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
		speed = 2;
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
		int topEdge = (int)y - (h/2);
		int bottomEdge = (int)y + (h/2);
		int leftEdge = (int)x - (w/2);
		int rightEdge = (int)x + (w/2);
		
		if (EZInteraction.isKeyDown(directionUp) && topEdge > 0) {
			tankSprite.moveForward(speed);
		}
		else if (EZInteraction.isKeyDown(directionLeft) && leftEdge > 0) {
			tankSprite.turnLeft(1);
		}
		else if (EZInteraction.isKeyDown(directionDown) && bottomEdge < worldHeight) {
			tankSprite.moveForward(-speed);
		}
		else if (EZInteraction.isKeyDown(directionRight) && rightEdge < worldWidth) {
			tankSprite.turnRight(1);
		}
	}
	
	//public void projectileCollide(Projectiles projectile) {
	//}
	
	//public void fireProjectile()

}
