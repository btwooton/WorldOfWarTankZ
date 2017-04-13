
public class BulletTest {

	public static void main(String[] args) throws java.io.IOException {
		// TODO Auto-generated method stub
		int width = 720;
		int height = (width / 12) * 9;
		EZ.initialize(width, height);
		upGrades test = new upGrades("theOnsBig.png", 100, 100, 64, 200, 5);
		upGrades sheild = new upGrades("Sheild_224_3.png", 200, 200, 224, 200, 3);
		upGrades deathBall = new upGrades("deathBall_96__3.png", 300, 200, 96, 100, 3);
		upGrades nos = new upGrades("nos _96_7.png", 400, 200, 96, 200, 7);
		upGrades bullet = new upGrades("bullet_96_8.png", 500, 200, 96, 50, 8);
		upGrades health = new upGrades("health_64_8.png", 500, 400, 64, 100, 8);
		upGrades sheildGen = new upGrades("generator_64_9.png", 300, 400, 64, 100, 9);
		upGrades firstAid = new upGrades("firstAid_64_10.png", 200, 400, 64, 100, 10);


		while (true) {
			test.spawn();
			sheild.spawn();
			deathBall.spawn();
			nos.spawn();
			bullet.spawn();
			health.spawn();
			sheildGen.spawn();
			firstAid.spawn();
			EZ.refreshScreen();
		}

	}
}
