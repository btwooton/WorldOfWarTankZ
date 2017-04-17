import java.util.Random;

public class PowerUp {
	Random rg = new Random();
	private int x, y;
	long time;
	private Name name;
	GiffAnime sprite;
	boolean onScreen;
	boolean touching;

	private static enum Name {
		REPAIR, SHEILD, SPEED, POWER;
	}

	public PowerUp(int posx, int posy) {
		name = Name.values()[rg.nextInt(Name.values().length)];
		time = System.currentTimeMillis();
		x = posx;
		y = posy;
		onScreen = false;
		switch (name) {
		case REPAIR:
			sprite = new GiffAnime("health_64_9.png", x, y, 64, 100, 9);
			break;
		case SHEILD:
			sprite = new GiffAnime("generator_64_9.png", x, y, 64, 100, 9);
			break;
		case SPEED:
			sprite = new GiffAnime("nos _96_7.png", x, y, 96, 100, 7);

			break;
		case POWER:
			sprite = new GiffAnime("plasmaCrystal_86_18.png", x, y, 86, 100, 18);

			break;
		}

	}

	void spawn() {
		sprite.spawn();

	}

	int randomX() {
		return rg.nextInt(1000);
	}

	int randomY() {
		return rg.nextInt(700);
	}

	void hide() {
		sprite.hide();
	}

	void show() {
		sprite.show();
	}

	void getXcenter() {
		sprite.getXCenter();
	}

	void getYcenter() {
		sprite.getYCenter();
	}

	public boolean tankIsTouching(int posx, int posy, int upGradeCount) {
		for (int i = 0; i < upGradeCount; i++) {
			if (sprite.isPointInElement(posx - 2, posy - 2))
				return touching;
			if (sprite.isPointInElement(posx + 2, posy - 2))
				return touching;
			if (sprite.isPointInElement(posx - 2, posy + 2))
				return touching;
			if (sprite.isPointInElement(posx + 2, posy + 2))
				return touching;

		}
		return !touching;
	}

	void remove() {
		if (touching) {
			sprite.translateTo(-100, -100);
		}
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
