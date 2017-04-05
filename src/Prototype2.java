import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;

public class Prototype2 {

	static int screenWidth = 1300;
	static int screenHeight = 700;
	static EZText HealthDisplay1;
	static EZImage Player1;
	static int x, y, HealthP1, width, height;
	
	public static void main(String[] args) throws java.io.IOException {

		
		EZ.initialize(screenWidth, screenHeight); // initialize the window

		// set to white background
		EZ.setBackgroundColor(new Color(255, 255, 255));
		
		HealthP1 = 100;
		String Health1 = "HP: " + HealthP1;
		HealthDisplay1 = EZ.addText(50, 50, Health1, Color.BLACK, 25);

		// draw my character
		Player1 = EZ.addImage("Tank.png", screenWidth / 2 , screenHeight / 2 );

		width = Player1.getWidth(); // store character image width
		height = Player1.getHeight(); // store character image height

		while(true) {
			
			// initialize integer x with character x-value placement
			x = Player1.getXCenter();
			// initialize integer y with character y-value placement
			y = Player1.getYCenter();
			  
			if (EZInteraction.isKeyDown('d')) {
				Player1.turnRight(5);
			} else if (EZInteraction.isKeyDown('a')) {
				Player1.turnLeft(5);
			} else if (EZInteraction.isKeyDown('w')) {
				Player1.moveForward(10);
			} else if (EZInteraction.isKeyDown('s')) {
				Player1.moveForward(-10); 
			} else if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
				
			}
			
			if (x + width / 2 > screenWidth) { // set right screen boundary
				Player1.translateTo(screenWidth - width / 2, y);
			}

			if (x - width / 2 < 0) { // set left screen boundary
				Player1.translateTo(width / 2, y);
			}

			if (y - height / 2 < 0) { // set top screen boundary
				Player1.translateTo(x, height / 2);
			}

			if (y + height / 2 > screenHeight) { // set bottom screen boundary
				Player1.translateTo(x, screenHeight - height / 2);
			}
		  
		  EZ.refreshScreen();
	  }
	}

}
