import java.awt.event.KeyEvent;

public class spriteTest {

	EZImage spriteSheet;
	private int counter, x, y, xLeft, yLeft, xRight, yRight, w, h, speed;

	public spriteTest(String imgFile, int posx, int posy, int frameWidth, int frameHeight, int frames) {
		speed = frames;
		w = frameWidth;
		h = frameHeight;
		x = posx;
		y = posy;
		xLeft = 0;
		yLeft = 0;
		xRight = frameWidth;
		yRight = frameHeight;
		spriteSheet = EZ.addImage(imgFile, posx, posy);
	}

	void spin() {/// fpr=frames per row
		counter++;
		if (counter > counter * 40)
			spriteSheet.translateTo(x, y);
			spriteSheet.setFocus(xLeft, yLeft, xRight, yRight);
		counter = 0;

		if (counter == counter * 10)
			spriteSheet.translateTo(x, y);

			spriteSheet.setFocus(xLeft * 2, yLeft, xRight * 2, yRight);

		if (counter == counter * 20)
			spriteSheet.translateTo(x, y);

			spriteSheet.setFocus(xLeft, yLeft * 2, yRight, yRight * 2);

		if (counter == counter * 30)
			spriteSheet.translateTo(x, y);

			spriteSheet.setFocus(xLeft * 2, yLeft * 2, xRight * 2, yRight * 2);
	}

}
