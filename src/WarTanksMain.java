import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WarTanksMain {

	// Setting up global variables for screen dimensions
	static int x, y, HealthP1, width, height, Swidth, Sheight, score, score2;
	static int screenWidth = 1280;
	static int screenHeight = 736;
	// Array to hold all of the projectiles for the game
	static Projectiles[] projectiles = new Projectiles[100];
	// For indexing into the projectiles array
	static int nextProjectile = 0;

	// Text file that generates the map tiles
	static String map = "MapMaker.txt";
	// Array to hold all of the PowerUps
	static PowerUp[] power = new PowerUp[50];
	// Random number generator
	static Random rg = new Random();

	static int counter = 0;
	static int powerUpIndex = 0;

	// For displaying the HP of each player
	static EZText scoreDisplay, scoreDisplay2;

	// String that gets displayed for each player's health count
	static String Points, Points2;

	static int size = 30;

	public static void main(String[] args) throws java.io.IOException {
		Sound gameSound = new Sound();
		gameSound.themeLoop();

		// build map
		MapBuilder maps = new MapBuilder(map);

		// pull array list from map class
		ArrayList<Integer> xpos = maps.getXList();
		ArrayList<Integer> ypos = maps.getYList();
		ArrayList<Integer> TankStats;

		// draw my character
		Tank Player1 = new Tank("newTank.png", screenWidth / 4, screenHeight / 2, 1, new char[] { 'w', 'a', 's', 'd' });
		Tank Player2 = new Tank("newTank.png", screenWidth * 3 / 4, screenHeight / 2, 2,
				new char[] { 'i', 'j', 'k', 'l' });

		// set up health and health board
		Points = "Player 1 HP: " + Player1.getHealth();
		Points2 = "Player 2 HP: " + Player2.getHealth();
		scoreDisplay = EZ.addText("ethnocentric rg.ttf", 300, 15, Points, Color.black, 25);
		scoreDisplay2 = EZ.addText("ethnocentric rg.ttf", screenWidth - 300, 15, Points2, Color.black, 25);
		// sound for game

		// Load projectiles and power ups into the arrays
		for (int i = 0; i < projectiles.length; i++) {
			projectiles[i] = new Projectiles(-100, -100);
		}
		for (int i = 0; i < power.length; i++) {
			power[i] = new PowerUp(-100, -100);
		}

		power[powerUpIndex].translateTo(power[powerUpIndex].randomX(), power[powerUpIndex].randomY());
		while (!(Player1.isDead() || Player2.isDead())) {

			// Allow both players to control their tanks
			Player1.moveAround(0, 0, screenWidth, screenHeight);
			Player2.moveAround(0, 0, screenWidth, screenHeight);

			// Check for collision between tanks and map
			Player1.TankObstacle(xpos, ypos);
			Player2.TankObstacle(xpos, ypos);

			// gameSound.tankFX();

			// Animate the shields
			Player1.animateShield();
			Player2.animateShield();

			// Animate the tank sprite during movement
			Player1.animateTank();
			Player2.animateTank();

			// Check status of power ups for duration of effect
			Player1.checkPowerUps();
			Player2.checkPowerUps();

			// Animate the canon smoke if on cool down
			Player1.canonSmoke();
			Player2.canonSmoke();

			// Display explosion animation if hit by projectiles
			Player1.explosion();
			Player2.explosion();

			// Check for collision between tanks
			Player1.collideWithTanks(Player2);
			Player2.collideWithTanks(Player1);

			// Animate current powerUp
			power[powerUpIndex].animate();

			// Check for collision between tanks and power ups
			if (power[powerUpIndex].tankUpgrade(Player1, 30, 30, power[powerUpIndex])) {

				if (power[powerUpIndex].getName() == PowerUp.Name.POWER) {
					Player1.upgradeWeapon();
					gameSound.weaponUpgrade();
				} else if (power[powerUpIndex].getName() == PowerUp.Name.REPAIR) {
					Player1.repairTank();
					EZ.removeEZElement(scoreDisplay);
					Points = "Player 1 HP: " + Player1.getHealth();
					scoreDisplay = EZ.addText("ethnocentric rg.ttf", 300, 15, Points, Color.black, 25);
					gameSound.heal();
				} else if (power[powerUpIndex].getName() == PowerUp.Name.SHEILD) {
					Player1.activateShield();
					gameSound.shieldUpgrade();
				} else if (power[powerUpIndex].getName() == PowerUp.Name.SPEED) {
					Player1.upgradeSpeed();
					gameSound.speedUpgrade();
				}

				power[powerUpIndex].remove();

				powerUpIndex++;

				power[powerUpIndex].remove();

				power[powerUpIndex].translateTo(power[powerUpIndex].randomX(), power[powerUpIndex].randomY());

			}

			// Check for collision between tanks and power ups
			if (power[powerUpIndex].tankUpgrade(Player2, 30, 30, power[powerUpIndex])) {

				if (power[powerUpIndex].getName() == PowerUp.Name.POWER) {
					Player2.upgradeWeapon();
					gameSound.weaponUpgrade();

				} else if (power[powerUpIndex].getName() == PowerUp.Name.REPAIR) {
					Player2.repairTank();
					EZ.removeEZElement(scoreDisplay2);
					Points2 = "Player 2 HP: " + Player2.getHealth();
					scoreDisplay2 = EZ.addText("ethnocentric rg.ttf", screenWidth - 300, 15, Points2, Color.black, 25);
					gameSound.heal();

				} else if (power[powerUpIndex].getName() == PowerUp.Name.SHEILD) {
					Player2.activateShield();
					gameSound.shieldUpgrade();
				} else if (power[powerUpIndex].getName() == PowerUp.Name.SPEED) {
					Player2.upgradeSpeed();
					gameSound.speedUpgrade();

				}

				power[powerUpIndex].remove();

				powerUpIndex++;

				power[powerUpIndex].remove();

				power[powerUpIndex].translateTo(power[powerUpIndex].randomX(), power[powerUpIndex].randomY());
			}

			// If spacebar is pressed, attempt to have Player1 fire a projectile
			if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
				if (Player1.fireProjectile(projectiles[nextProjectile])) {
					nextProjectile = (nextProjectile + 1) % projectiles.length;
					gameSound.fire();
				}
			}

			// If enter is pressed, attemp to have Player2 fire a projectile
			if (EZInteraction.wasKeyReleased(KeyEvent.VK_ENTER)) {
				if (Player2.fireProjectile(projectiles[nextProjectile])) {
					nextProjectile = (nextProjectile + 1) % projectiles.length;
					gameSound.fire();
				}
			}

			// Move any projectiles that are on screen and check for collisions
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
						scoreDisplay = EZ.addText("ethnocentric rg.ttf", 300, 15, Points, Color.black, 25);
						projectiles[i].setOffScreen(screenHeight, screenWidth, true);
						projectiles[i].translateTo(-100, -100);
					}

					if (Player2.collideWithProjectiles(projectiles[i])) {
						Player2.takeDamage(projectiles[i].getFirePower());
						EZ.removeEZElement(scoreDisplay2);
						Points2 = "Player 2 HP: " + Player2.getHealth();
						scoreDisplay2 = EZ.addText("ethnocentric rg.ttf", screenWidth - 300, 15, Points2, Color.black,
								25);
						projectiles[i].setOffScreen(screenHeight, screenWidth, true);
						projectiles[i].translateTo(-100, -100);
					}

				}
			}

			EZ.refreshScreen();
		}

		/*
		 * ======================================================== 
		 * If the game has ended, we clear the screen, display an end game background,
		 *  and an end game message indicating which player has won the game. After a
		 * 3 second pause, we close the game window
		 */
		EZ.removeAllEZElements();
		EZImage gameOverBG = EZ.addImage("gameOverScreen.png", screenWidth / 2, screenHeight / 2);
		gameOverBG.pushToBack();
		if (Player1.getHealth() <= 0) {
			EZ.addText("8-BIT WONDER.TTF", screenWidth / 2, screenHeight / 3, "Player 2 Wins", Color.BLACK, 50);
		}
		if (Player2.getHealth() <= 0) {
			EZ.addText("8-BIT WONDER.TTF", screenWidth / 2, screenHeight / 3, "Player 1 Wins", Color.BLACK, 50);
		}
		EZ.addText("8-BIT WONDER.TTF", screenWidth / 2, screenHeight * 2 / 3, "GAME OVER", Color.BLACK, 50);
		EZ.refreshScreen();
		EZ.pause(3000);
		EZ.closeWindowWithIndex(0);

	}

}
