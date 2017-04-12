import java.awt.Color;

public class BulletTest {

	public static void main(String[] args)throws java.io.IOException {
		// TODO Auto-generated method stub
		int width = 720;
		int height = (width / 12) * 9;
		int index = 0;
		int spd = 2;
		int dX = 2;
		int posX = 0;
		int posY = 0;
		EZ.initialize(width, height);
		Projectiles bulletTest = new Projectiles(0, height / 2, "bulletColor");
		while (true) {
			bulletTest.fire();
			if (posX < width) {
				posX += spd;
				bulletTest.translateTo(posX, posY);
			}
			EZ.refreshScreen();
		}

	}
}
