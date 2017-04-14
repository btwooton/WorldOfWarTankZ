import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;

public class Prototype3 {
	
	static int x, y, HealthP1, width, height, Swidth, Sheight;
	static int screenWidth = 0;
	static int screenHeight = 0;
	static Projectiles[] projectiles = new Projectiles[100];
	static int nextProjectile = 0;

	public static void main(String[] args) throws java.io.IOException {
		Scanner fileScanner = new Scanner(new FileReader("MapMaker.txt"));

		Swidth = fileScanner.nextInt();
		Sheight = fileScanner.nextInt();
		String inputText = fileScanner.nextLine();
		screenWidth = Swidth * 32;
		screenHeight = Sheight * 32;

		EZ.initialize(Swidth * 32, Sheight * 32); // initialize the window

		// set to white background
		EZ.setBackgroundColor(new Color(255, 255, 255));
		
		for (int line = 0; line < Sheight; line++) {

			inputText = fileScanner.nextLine();
			System.out.println(inputText);

			for (int i = 0; i < inputText.length(); i++) {

				char ch = inputText.charAt(i);

				switch (ch) {
				case 'D':
					EZ.addImage("dirt.png", i * 32 + 16, line * 32 + 16);
					break;
				case 'G':
					EZ.addImage("grass.png", i * 32 + 16, line * 32 + 16);
					break;
				default:
					// Do nothing
					break;

				}
			}
		}

		// draw my character
		Tank Player1 = new Tank("Tank.png", screenWidth / 2 , screenHeight / 2,
				1, new char[] {'w','a','s','d'});
		Tank Player2 = new Tank("Tank.png", screenWidth/4, screenHeight/4,
				2, new char[] {'i','j','k','l'});
		
		for (int i = 0; i < projectiles.length; i++) {
			projectiles[i] = new Projectiles(-100, -100);
		}

  
		while(!(Player1.isDead() || Player2.isDead())) {
			
			  Player1.moveAround(32, 32, screenWidth - 32, screenHeight - 32);
			  Player2.moveAround(32, 32, screenWidth - 32, screenHeight - 32);
			  			  
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
