import java.util.Random;

public class PowerUp {
	Random rg = new Random();
	private int x, y, size;
	long time;
	private Name name;
	GiffAnime sprite;
	boolean onScreen;
	boolean touching;

	public static enum Name {
		REPAIR, SHEILD, SPEED, POWER;
	}

	public PowerUp(int posx, int posy) {
		name = Name.values()[rg.nextInt(Name.values().length)];
		time = System.currentTimeMillis();
		x = posx;
		y = posy;
		touching = false;
		onScreen = false;
		switch (name) {
		case REPAIR:
			sprite = new GiffAnime("health_64_9.png", x, y, 64, 64, 100, 9);
			size = 64;
			break;
		case SHEILD:
			sprite = new GiffAnime("generator_64_9.png", x, y, 64, 64, 100, 9);
			size = 64;
			break;
		case SPEED:
			sprite = new GiffAnime("nos _96_7.png", x, y, 96, 96, 100, 7);
			size = 96;
			break;
		case POWER:
			sprite = new GiffAnime("plasmaCrystal_86_18.png", x, y, 86, 86, 100, 18);
			size = 86;
			break;
		}

	}

	void animate() {
		sprite.animate();
	}

	int randomX() {
		return rg.nextInt(1000);
	}

	int randomY() {
		return rg.nextInt(700);
	}

	public Name getName() {
		return name;
	}

	void hide() {
		sprite.hide();
	}

	void show() {
		sprite.show();
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

	int getHeight() {
		return sprite.getHeight();
	}

	int getWidth() {
		return sprite.getWidth();
	}

	public boolean isPointInElement(int posx, int posy) {
		return sprite.isPointInElement(posx, posy);
	}

	public boolean tankUpgrade(Tank player, int tankWidth, int tankHeight, PowerUp n) {
		if ((n.isPointInElement(player.getXcenter() - tankWidth / 2, player.getYcenter() - tankHeight / 2))
				|| (n.isPointInElement(player.getXcenter() - tankWidth / 2, player.getYcenter() + tankHeight / 2))
				|| (n.isPointInElement(player.getXcenter() + tankWidth / 2, player.getYcenter() - tankHeight / 2))
				|| (n.isPointInElement(player.getXcenter() + tankWidth / 2, player.getYcenter() + tankHeight / 2))) {
			return true;
		} else {
			return false;
		}
	}

	int getTopEdge() {
		return sprite.getYCenter() - sprite.getHeight() / 2;
	}

	int getBottomEdge() {
		return sprite.getYCenter() + sprite.getHeight() / 2;
	}

	int getRightEdge() {
		return sprite.getXCenter() + sprite.getWidth() / 2;
	}

	int getLeftEdge() {
		return sprite.getXCenter() - sprite.getWidth() / 2;
	}

	void setOffScreen(int worldHeight, int worldWidth, boolean hasCollided) {

		if (this.getBottomEdge() < -20) {
			onScreen = false;
		}

		if (this.getTopEdge() > worldHeight + 20) {
			onScreen = false;
		}

		if (this.getRightEdge() < -20) {
			onScreen = false;
		}

		if (this.getLeftEdge() > worldWidth + 20) {
			onScreen = false;
		}

		if (hasCollided) {
			onScreen = false;
		}
	}

	boolean isOnScreen() {
		return onScreen;
	}

	void setOnScreen() {
		onScreen = true;
	}
}
