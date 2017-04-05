
public class BulletTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int width = 230;
		int height = (width / 12) * 9;
		EZ.initialize(width, height);

		Projectiles bulletTest = new Projectiles(width / 2, height / 2);

		while (true) {
			bulletTest.go();
			EZ.refreshScreen();
		}

	}

}
