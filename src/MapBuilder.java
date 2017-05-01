import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.*;

public class MapBuilder {

	static int x, y, HealthP1, width, height, Swidth, Sheight;
	static int screenWidth = 0;
	static int screenHeight = 0;
	private ArrayList<Integer> xs, ys;

	public MapBuilder(String TextMap) throws java.io.IOException {
		// set up scanner
		Scanner fileScanner = new Scanner(new FileReader(TextMap));
		Random rg = new Random();

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
		
		while(true) {
			EZImage start = EZ.addImage("Title.jpg", screenWidth / 2, screenHeight / 2);
			EZImage title = EZ.addImage("WWTZLogo.png", screenWidth / 2, screenHeight / 4);
			EZText message = EZ.addText("8-BIT WONDER.TTF",
					screenWidth / 2, screenHeight *3 / 4, "Press Space to Start", new Color(17,51,104), 50);
			if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
				EZ.removeEZElement(message);
				EZ.removeEZElement(start);
				EZ.removeEZElement(message);
				break;
			}
		}
		
		EZ.addImage("MetalIron.jpg", screenWidth / 2, screenHeight / 2);


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
					// add image
					EZ.addImage("MCSilverBlock32.jpg", i * 32 + 16, line * 32 + 16);

					// add position to arrays
					xs.add(i * 32 + 16);
					ys.add(line * 32 + 16);
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
