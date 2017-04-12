
public class spriteTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EZ.initialize(512, 512);

		spriteTest test = new spriteTest("New Piskel.png", 100, 100, 32, 32, 10);
		while (true) {
			test.spin();
			EZ.refreshScreen();
		}
	}

}
