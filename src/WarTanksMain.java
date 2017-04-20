import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WarTanksMain {

	static int x, y, HealthP1, width, height, Swidth, Sheight, score, score2;
	static int screenWidth = 1280;
	static int screenHeight = 736;
	static Projectiles[] projectiles = new Projectiles[100];
	static int nextProjectile = 0;
	static String map = "MapMaker.txt";
	static PowerUp[] power = new PowerUp[50];
	static Random rg = new Random();
	static int counter = 0;
	static int powerUpIndex = 0;
	static EZText scoreDisplay, scoreDisplay2;
	static String Points, Points2;
	static int size = 30;

	public static void main(String[] args) throws java.io.IOException {

		//build map
		MapBuilder maps = new MapBuilder(map);

		//pull array list from map class
		ArrayList<Integer> xpos = maps.getXList();
		ArrayList<Integer> ypos = maps.getYList();
		ArrayList<Integer> TankStats;
		
		// draw my character
		Tank Player1 = new Tank("Tank.png", screenWidth /4, screenHeight / 2, 1,
				new char[] { 'w', 'a', 's', 'd' });
		Tank Player2 = new Tank("Tank.png", screenWidth * 3 / 4, screenHeight / 2, 2, new char[] { 'i', 'j', 'k', 'l' });

		//set up health and health board
		Points = "Player 1 HP: " + Player1.getHealth();
		Points2 = "Player 2 HP: " + Player2.getHealth();
		scoreDisplay = EZ.addText(300, 15, Points, Color.white, 25);
		scoreDisplay2 = EZ.addText(screenWidth - 300, 15, Points2, Color.white, 25);

		
		for (int i = 0; i < projectiles.length; i++) {
			projectiles[i] = new Projectiles(-100, -100);
		}
		for (int i = 0; i < power.length; i++) {
			power[i] = new PowerUp(-100, -100);
		}

		power[powerUpIndex].translateTo(power[powerUpIndex].randomX(), power[powerUpIndex].randomY());
		

		while (!(Player1.isDead() || Player2.isDead())) {
			Player1.moveAround(32, 32, screenWidth - 32, screenHeight - 32);
			Player2.moveAround(32, 32, screenWidth - 32, screenHeight - 32);
			Player1.TankObstacle(xpos, ypos);
			Player2.TankObstacle(xpos, ypos);

			Player1.collideWithTanks(Player2);
			Player2.collideWithTanks(Player1);

			power[powerUpIndex].animate();
			if (power[powerUpIndex].tankUpgrade(Player1, 30, 30, power[powerUpIndex])) {
				
				if (power[powerUpIndex].getName() == PowerUp.Name.POWER) {
					Player1.upgradeWeapon();
				}
				else if (power[powerUpIndex].getName() == PowerUp.Name.REPAIR) {
					Player1.repairTank();
					EZ.removeEZElement(scoreDisplay);
					Points = "Player 1 HP: " + Player1.getHealth();
					scoreDisplay = EZ.addText(300, 15, Points, Color.white, 25);
				}
				else if (power[powerUpIndex].getName() == PowerUp.Name.SHEILD) {
					Player1.activateShield();
				}
				else if (power[powerUpIndex].getName() == PowerUp.Name.SPEED) {
					Player1.upgradeSpeed();
				}
				
				power[powerUpIndex].remove();

				powerUpIndex++;

				power[powerUpIndex].remove();

				power[powerUpIndex].translateTo(power[powerUpIndex].randomX(), power[powerUpIndex].randomY());
				
				
			}
					
			if (power[powerUpIndex].tankUpgrade(Player2, 30, 30, power[powerUpIndex])) {
				
				if (power[powerUpIndex].getName() == PowerUp.Name.POWER) {
					Player2.upgradeWeapon();
				}
				else if (power[powerUpIndex].getName() == PowerUp.Name.REPAIR) {
					Player2.repairTank();
					EZ.removeEZElement(scoreDisplay2);
					Points2 = "Player 2 HP: " + Player2.getHealth();
					scoreDisplay2 = EZ.addText(screenWidth - 300, 15, Points2, Color.white, 25);
				}
				else if (power[powerUpIndex].getName() == PowerUp.Name.SHEILD) {
					Player2.activateShield();
				}
				else if (power[powerUpIndex].getName() == PowerUp.Name.SPEED) {
					Player2.upgradeSpeed();
				}

				power[powerUpIndex].remove();

				powerUpIndex++;

				power[powerUpIndex].remove();

				power[powerUpIndex].translateTo(power[powerUpIndex].randomX(), power[powerUpIndex].randomY());
			}

			if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
				Player1.fireProjectile(projectiles[nextProjectile]);
				nextProjectile = (nextProjectile + 1) % projectiles.length;
			}

			if (EZInteraction.wasKeyReleased(KeyEvent.VK_ENTER)) {
				Player2.fireProjectile(projectiles[nextProjectile]);
				nextProjectile = (nextProjectile + 1) % projectiles.length;
			}

			for (int i = 0; i < projectiles.length; i++) {

				if (projectiles[i].isOnScreen()) {

					projectiles[i].moveForward();

					projectiles[i].ricochet(screenHeight, screenWidth);

					projectiles[i].setOffScreen(screenHeight, screenWidth, false);

					projectiles[i].ObstacleRicochet(xpos, ypos);

					if (Player1.collideWithProjectiles(projectiles[i])) {
						Player1.takeDamage(projectiles[i].getFirePower());
						EZ.removeEZElement(scoreDisplay);
						Points = "Player 1 HP: " + Player1.getHealth();
						scoreDisplay = EZ.addText(300, 15, Points, Color.white, 25);
						projectiles[i].setOffScreen(screenHeight, screenWidth, true);
						projectiles[i].translateTo(-100, -100);
					}

					if (Player2.collideWithProjectiles(projectiles[i])) {
						Player2.takeDamage(projectiles[i].getFirePower());
						EZ.removeEZElement(scoreDisplay2);
						Points2 = "Player 2 HP: " + Player2.getHealth();
						scoreDisplay2 = EZ.addText(screenWidth - 300, 15, Points2, Color.white, 25);
						projectiles[i].setOffScreen(screenHeight, screenWidth, true);
						projectiles[i].translateTo(-100, -100);
					}

				}
			}

			EZ.refreshScreen();
		}
		EZ.removeAllEZElements();
		EZ.closeWindowWithIndex(0);

	}

}
