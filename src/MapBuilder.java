import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;
import java.util.*;

public class MapBuilder {

	static int x, y, HealthP1, width, height, Swidth, Sheight;
	static int screenWidth = 0;
	static int screenHeight = 0;
	public ArrayList<Integer> xs, ys;
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

		xs = new ArrayList<Integer>();
		ys = new ArrayList<Integer>();
		

		for (int line = 0; line < Sheight; line++) {

			inputText = fileScanner.nextLine();
			System.out.println(inputText);

			for (int i = 0; i < inputText.length(); i++) {

				char ch = inputText.charAt(i);

				switch (ch) {
				case 'D':
					EZ.addImage("dirt.png", i * 32 + 16, line * 32 + 16);
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

	/*public void obstacleRicochet() {
		for(int i = 0; i< xs.size(); i++) {
			int a = xs.get(i);
			int b = 64;
			int cx = 10;
			int cy = 10;
			if (((cx-64 <= a-32 && cx+64 >= a-32) || (cx-64 <= a+32 && cx+64 >= a+32)) && (((cY1 <= bY1 && cY2 >= bY1) || (cY1 <= bY2 && cY2 >= bY2)))) {
				
			}
			
		}
	}*/
}
