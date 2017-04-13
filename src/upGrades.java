
public class upGrades {
	private long time;
	private int x, y, xLeft, xRight, yLeft, yRight, counter, fps, pixel, frames;
	EZImage sheet;

	public upGrades(String fileName, int posX, int posY, int pixelSize, int milliSecondsPerFrame, int numberOfFrames) {
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
		sheet.hide();
	}

	private void setImagePosition() {
		sheet.translateTo(x, y);
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
			sheet.show();
			xLeft += pixel;
			xRight += pixel;
			time = System.currentTimeMillis();
			counter++;
		}
	}
}
