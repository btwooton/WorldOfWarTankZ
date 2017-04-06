import java.awt.Color;


import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;

public class Prototype3 {
	
	static int screenWidth = 1300;
	static int screenHeight = 700;
	static DummyProjectiles[] projectiles = new DummyProjectiles[100];
	static int nextProjectile = 0;
	


	public static void main(String[] args) {
		EZ.initialize(screenWidth, screenHeight); // initialize the window

		// set to white background
		EZ.setBackgroundColor(new Color(255, 255, 255));

		// draw my character
		Tank Player1 = new Tank("Tank.png", screenWidth / 2 , screenHeight / 2,
				new char[] {'w','a','s','d'});
		Tank Player2 = new Tank("Tank.png", screenWidth/4, screenHeight/4,
				new char[] {'i','j','k','l'});
		
		for (int i = 0; i < projectiles.length; i++) {
			projectiles[i] = new DummyProjectiles(-100, -100);
		}


		while(true) {
			
			  Player1.moveAround(screenHeight, screenWidth);
			  Player2.moveAround(screenHeight, screenWidth);
			  
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
					  if (projectiles[i].getBottomEdge() < -20) {
						  projectiles[i].setOffScreen();
					  }
					  if (projectiles[i].getTopEdge() > screenHeight + 20) {
						  projectiles[i].setOffScreen();
					  }
					  if (projectiles[i].getRightEdge() < -20) {
						  projectiles[i].setOffScreen();
					  }
					  if (projectiles[i].getLeftEdge() > screenWidth + 20) {
						  projectiles[i].setOffScreen();
					  }
				  }    
			  }
			  

	
		  
		  EZ.refreshScreen();
	  }

	}

}
