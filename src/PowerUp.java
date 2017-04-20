import java.util.ArrayList;
import java.util.Random;

/*
 * ============================================================================================================
 * PowerUp class represents the different EZImage upgrades that the Tank players can obtain during gameplay.
 * ============================================================================================================
 */
public class PowerUp {
	Random rg = new Random();
	private int x, y;
	long time;
	private Name name;
	GiffAnime sprite;

	public static enum Name {
		REPAIR, SHEILD, SPEED, POWER;
	}

	/*
	 * =========================================================================
	 * Uses an enum name class to randomly choose name of up grade
	 * =========================================================================
	 */
	public PowerUp(int posx, int posy) {
		name = Name.values()[rg.nextInt(Name.values().length)];
		time = System.currentTimeMillis();
		x = posx;
		y = posy;
		/*
		 * =====================================================================
		 * Switch statement - Assigns Random Enum name to sprite animation
		 * =====================================================================
		 */
		switch (name) {
		case REPAIR:
			sprite = new GiffAnime("health_64_9.png", x, y, 64, 64, 100, 9);
			break;
		case SHEILD:
			sprite = new GiffAnime("generator_64_9.png", x, y, 64, 64, 100, 9);
			break;
		case SPEED:
			sprite = new GiffAnime("nos _96_7.png", x, y, 96, 96, 100, 7);
			break;
		case POWER:
			sprite = new GiffAnime("plasmaCrystal_86_18.png", x, y, 86, 86, 100, 18);
			break;
		}

	}

	void animate() {
		sprite.animate();
	}

	int randomX() {
	return rg.nextInt(900) + 100;
	
	}

	int randomY() {
		return rg.nextInt(500) + 50;
	}

	public Name getName() {
		return name;
	}

	int getXcenter() {
		return sprite.getXCenter();
	}

	int getYcenter() {
		return sprite.getYCenter();
	}

	void remove() {
		sprite.translateTo(-100, -100);
	}

	void translateTo(double x, double y) {
		sprite.translateTo(x, y);
	}

	public boolean isPointInElement(int posx, int posy) {
		return sprite.isPointInElement(posx, posy);
	}

	/*
	 * =========================================================================
	 * Collision for tank and upgrade
	 * =========================================================================
	 */
	public boolean tankUpgrade(Tank player, int tankWidth, int tankHeight, PowerUp array) {
		if ((array.isPointInElement(player.getXcenter() - tankWidth / 2, player.getYcenter() - tankHeight / 2))
				|| (array.isPointInElement(player.getXcenter() - tankWidth / 2, player.getYcenter() + tankHeight / 2))
				|| (array.isPointInElement(player.getXcenter() + tankWidth / 2, player.getYcenter() - tankHeight / 2))
				|| (array.isPointInElement(player.getXcenter() + tankWidth / 2,
						player.getYcenter() + tankHeight / 2))) {
			return true;
		} else {
			return false;
		}
	}

}
