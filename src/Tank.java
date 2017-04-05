
public class Tank {
	
	private EZImage tankSprite;
	private float hp;
	private float speed;
	private float x, y;
	private float w, h;
	private float weaponPower;
	private boolean shielded;

	
	public Tank(String imageName, int _x, int _y) {
		tankSprite = EZ.addImage(imageName, _x, _y);
		hp = 100;
		speed = 1;
		x = _x;
		y = _y;
		w = tankSprite.getWidth();
		h = tankSprite.getHeight();
		weaponPower = 10;
		shielded = false;
	}
	

}
