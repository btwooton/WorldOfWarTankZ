
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

	void spawn() {
		if (counter >= frames) {
			xLeft = 0;
			xRight = pixel;
			counter = 0;
		}
		if (System.currentTimeMillis() - time >= fps) {
			setImagePosition();
			xLeft += pixel;
			xRight += pixel;
			time = System.currentTimeMillis();
			counter++;
		}
	}

	void moveForward(float spd) {
		sheet.moveForward(spd);

	}

	void translateTo(double x, double y) {
		sheet.translateTo(x, y);

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
