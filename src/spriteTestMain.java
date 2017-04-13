
public class spriteTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int w = 512;
		int h = 512 * 9 / 12;
		EZ.initialize(w, h);
		EZImage test = EZ.addImage("theOnsBig.png", w / 2, h / 2);
		test.hide();
		long time = System.currentTimeMillis();
		int xLeft = 0;
		int xRight = 32;
		int counter = 0;
		int fps = 200;
		while (true) {
			if (counter >= 5) {
				xLeft = 0;
				xRight = 32;
				counter = 0;
			}
			if (System.currentTimeMillis() - time >= fps) {
				test.setFocus(xLeft, 0, xRight, 32);
				test.show();
				xLeft += 32;
				xRight += 32;
				time = System.currentTimeMillis();
				counter++;
			}

			EZ.refreshScreen();
		}
	}
}
