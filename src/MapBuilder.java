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
	static int bx, by;

	public MapBuilder(String TextMap) throws java.io.IOException {
		Scanner fileScanner = new Scanner(new FileReader(TextMap));

		Swidth = fileScanner.nextInt();
		Sheight = fileScanner.nextInt();
		String inputText = fileScanner.nextLine();
		screenWidth = Swidth * 32;
		screenHeight = Sheight * 32;

		EZ.initialize(Swidth * 32, Sheight * 32); // initialize the window
		// set to white background
		EZ.setBackgroundColor(new Color(255, 255, 255));
		//EZ.addImage("rockTexture.png", screenWidth / 2, screenHeight / 2);
		EZ.addImage("blueTexture.png", screenWidth / 2, screenHeight / 2);

		xs = new ArrayList<Integer>();
		ys = new ArrayList<Integer>();

		for (int line = 0; line < Sheight; line++) {

			inputText = fileScanner.nextLine();
			System.out.println(inputText);

			for (int i = 0; i < inputText.length(); i++) {

				char ch = inputText.charAt(i);

				switch (ch) {
				case 'D':
					EZImage brick = EZ.addImage("ground.png", i * 32 + 16, line * 32 + 16);
					//EZ.addImage("tankTrap_32.png", i * 32 + 16, line * 32 + 16);

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

	public ArrayList<Integer> getXList() {
		return xs;
	}

	public ArrayList<Integer> getYList() {
		return ys;
	}
}
