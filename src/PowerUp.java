import java.util.Random;

public class PowerUp {
	Random rg = new Random();
	private float x, y;
	private Name name;
	private static enum Name {
		weaponUpgrade,
		repairKit,
		speedUp,
		sheild,
	}
	public PowerUp(Name n, float posx, float posy){
		name = Name.values()[rg.nextInt(Name.values().length)];
		x = posx;
		y = posy;

	}
}
	

