import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Prototype3 {

	static int x, y, HealthP1, width, height, Swidth, Sheight;
	static int screenWidth = 1280;
	static int screenHeight = 736;
	static Projectiles[] projectiles = new Projectiles[100];
	static int nextProjectile = 0;
	static String map = "MapMaker.txt";
	static PowerUp[] power = new PowerUp[50];
	static Random rg = new Random();
	static int counter = 0;

	public static void main(String[] args) throws java.io.IOException {

		MapBuilder maps = new MapBuilder(map);

		ArrayList<Integer> xpos = maps.getXList();

		ArrayList<Integer> ypos = maps.getYList();

		System.out.println(xpos);
		System.out.println(ypos);

		// draw my character
		Tank Player1 = new Tank("Tank.png", screenWidth *3 / 4, screenHeight / 2, 1, new char[] { 'w', 'a', 's', 'd' });
		Tank Player2 = new Tank("Tank.png", screenWidth / 4, screenHeight / 2, 2, new char[] { 'i', 'j', 'k', 'l' });

		for (int i = 0; i < projectiles.length; i++) {
			projectiles[i] = new Projectiles(-100, -100);
		}
		for (int i = 0; i < power.length; i++) {
			power[i] = new PowerUp(-100, -100);
		}

		while (!(Player1.isDead() || Player2.isDead())) {
			Player1.moveAround(32, 32, screenWidth - 32, screenHeight - 32);
			Player2.moveAround(32, 32, screenWidth - 32, screenHeight - 32);
			Player1.TankObstacle(xpos, ypos);
			Player2.TankObstacle(xpos, ypos);

			Player1.collideWithTanks(Player2);
			Player2.collideWithTanks(Player1);

			if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
				Player1.fireProjectile(projectiles[nextProjectile]);
				nextProjectile = (nextProjectile + 1) % projectiles.length;
			}

			if (EZInteraction.wasKeyReleased(KeyEvent.VK_ENTER)) {
				Player2.fireProjectile(projectiles[nextProjectile]);
				nextProjectile = (nextProjectile + 1) % projectiles.length;
			}
			power[0].remove();
			power[0].spawn();
			power[0].translateTo(400, 400);
			if (power[0].tankIsTouching(Player1, power.length)
					|| power[0].tankIsTouching(Player2, power.length)) {
				power[0].translateTo(200, 200);
			}

			for (int i = 0; i < projectiles.length; i++) {

				if (projectiles[i].isOnScreen()) {

					projectiles[i].moveForward();

					projectiles[i].ricochet(screenHeight, screenWidth);

					projectiles[i].setOffScreen(screenHeight, screenWidth, false);

					projectiles[i].ObstacleRicochet(xpos, ypos);

					if (Player1.collideWithProjectiles(projectiles[i])) {
						projectiles[i].setOffScreen(screenHeight, screenWidth, true);
						projectiles[i].translateTo(-100, -100);
						Player1.takeDamage(projectiles[i].getFirePower());
					}

					if (Player2.collideWithProjectiles(projectiles[i])) {
						projectiles[i].setOffScreen(screenHeight, screenWidth, true);
						projectiles[i].translateTo(-100, -100);
						Player2.takeDamage(projectiles[i].getFirePower());
					}

				}
			}

			EZ.refreshScreen();
		}
		EZ.removeAllEZElements();
		EZ.closeWindowWithIndex(0);

	}

}
