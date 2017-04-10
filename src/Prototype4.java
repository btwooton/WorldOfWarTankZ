import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

//purpose of prototype 4 is to test obstacle borders and controls on generated map

public class Prototype4 {

	static EZText HealthDisplay1;
	static EZImage Player1;
	static int x, y, HealthP1, width, height, Swidth, Sheight;
	static int screenWidth = Swidth * 32;
	static int screenHeight = Sheight * 32;

	public static void main(String[] args) throws java.io.IOException {

		Scanner fileScanner = new Scanner(new FileReader("MapMaker.txt"));

		Swidth = fileScanner.nextInt();
		Sheight = fileScanner.nextInt();
		String inputText = fileScanner.nextLine();

		EZ.initialize(Swidth * 32, Sheight * 32);
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
		fileScanner.close();
		HealthP1 = 100;
		String Health1 = "HP: " + HealthP1;
		HealthDisplay1 = EZ.addText(50, 50, Health1, Color.BLACK, 25);

		// draw my character
		Player1 = EZ.addImage("Tank.png", 500, 500);

		width = Player1.getWidth(); // store character image width
		height = Player1.getHeight(); // store character image height
		while (true) {

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
			EZ.refreshScreen();
		}
	}

}