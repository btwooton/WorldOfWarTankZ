import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;
import java.util.*;

public class MapBuilder {

	static int x, y, HealthP1, width, height, Swidth, Sheight;
	static int screenWidth = 0;
	static int screenHeight = 0;
	private ArrayList<Integer> xs, ys;

	public MapBuilder(String TextMap) throws java.io.IOException {
		// set up scanner
		Scanner fileScanner = new Scanner(new FileReader(TextMap));

		// scan map dimensions
		Swidth = fileScanner.nextInt();
		Sheight = fileScanner.nextInt();
		String inputText = fileScanner.nextLine();
		// set up resolution
		screenWidth = Swidth * 32;
		screenHeight = Sheight * 32;

		// initialize window
		EZ.initialize(Swidth * 32, Sheight * 32); // initialize the window
		// set to white background
		EZ.setBackgroundColor(new Color(255, 255, 255));
		//EZ.addImage("rockTexture.png", screenWidth / 2, screenHeight / 2);
		EZ.addImage("blueTexture.png", screenWidth / 2, screenHeight / 2);

		// set x and y array lists
		xs = new ArrayList<Integer>();
		ys = new ArrayList<Integer>();

		// for loop to build map
		for (int line = 0; line < Sheight; line++) {

			// read next line
			inputText = fileScanner.nextLine();
			System.out.println(inputText);

			for (int i = 0; i < inputText.length(); i++) {

				// read next character in line
				char ch = inputText.charAt(i);

				// object placement case
				switch (ch) {
				case 'D':
					EZImage brick = EZ.addImage("ground.png", i * 32 + 16, line * 32 + 16);
					//EZ.addImage("tankTrap_32.png", i * 32 + 16, line * 32 + 16);

					// add image
					EZ.addImage("dirt.png", i * 32 + 16, line * 32 + 16);
					// add position to arrays
					xs.add(i * 32 + 16);
					ys.add(line * 32 + 16);
					brick.getXCenter();
					brick.getYCenter();
					break;
				default:
					// Do nothing
					break;

				}
			}
		}
	}


	// array pulling methods

	public ArrayList<Integer> getXList() {
		return xs;
	}

	public ArrayList<Integer> getYList() {
		return ys;
	}
}
