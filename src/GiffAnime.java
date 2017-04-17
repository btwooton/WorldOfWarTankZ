
public class GiffAnime {

	private long time;
	private int x, y, xLeft, xRight, yLeft, yRight, counter, fps, pixel, frames;
	EZImage sheet;

	public GiffAnime(String fileName, int posX, int posY, int pixelSize, int milliSecondsPerFrame, int numberOfFrames) {
		time = System.currentTimeMillis();
		x = posX;
		y = posY;
		xLeft = 0;
		xRight = pixelSize;
		yLeft = 0;
		yRight = pixelSize;
		counter = 0;
		pixel = pixelSize;
		frames = numberOfFrames;
		fps = milliSecondsPerFrame;
		sheet = EZ.addImage(fileName, posX, posY);
	}

	private void setImagePosition() {
		sheet.setFocus(xLeft, yLeft, xRight, yRight);
	}

	void spawn() {// Animates sprite sheet.
		if (counter >= frames) {
			xLeft = 0;
			xRight = pixel;
			counter = 0;
			/*
			 * <===============================================================>
			 * If counter is >=frames Image position is set back to first frame
			 * of sprite sheet.
			 * <===============================================================>
			 * >
			 */
		}
		if (System.currentTimeMillis() - time >= fps) {
			setImagePosition();
			xLeft += pixel;
			xRight += pixel;
			time = System.currentTimeMillis();
			counter++;
			/*
			 * <===============================================================>
			 * If current time minus time >= fps(how many seconds you want each
			 * frame to last.) Set Image position, then add the size of each
			 * frame to top left x and bottom right x
			 * <===============================================================>
			 */
		}

	}

	void moveForward(float spd) {
		sheet.moveForward(spd);

	}

	void translateTo(double x, double y) {
		sheet.translateTo(x, y);

	}

	public boolean isPointInElement(int posx, int posy) {
		return sheet.isPointInElement(posx, posy);
	}

	int getXCenter() {
		return sheet.getXCenter();

	}

	int getYCenter() {
		return sheet.getYCenter();

	}

	void rotateTo(double rotationalValue) {
		sheet.rotateTo(rotationalValue);
	}

	void hide() {
		sheet.hide();
	}

	void show() {
		sheet.show();
	}

	double getRotation() {
		return sheet.getRotation();
	}

	int getHeight() {
		return sheet.getHeight();
	}

	int getWidth() {
		return sheet.getWidth();
	}

}
