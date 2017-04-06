
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
		
		if (EZInteraction.isKeyDown(directionUp)) {
			tankSprite.moveForward(speed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		if (EZInteraction.isKeyDown(directionLeft)) {
			tankSprite.turnLeft(1);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		if (EZInteraction.isKeyDown(directionDown)) {
			tankSprite.moveForward(-speed);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		if (EZInteraction.isKeyDown(directionRight)) {
			tankSprite.turnRight(1);
			x = tankSprite.getXCenter();
			y = tankSprite.getYCenter();
		}
		
		if (bottomEdge > worldHeight) {
			tankSprite.translateTo(x, worldHeight - (h/2));
		}
		if (topEdge < 0) {
			tankSprite.translateTo(x, h/2);
		}
		if (leftEdge < 0) {
			tankSprite.translateTo(w/2, y);
		}
		if (rightEdge > worldWidth) {
			tankSprite.translateTo(worldWidth - (w/2), y);
		}
	}
	
	//public void projectileCollide(Projectiles projectile) {
	//}
	
	public void fireProjectile() {
		
	}

}
