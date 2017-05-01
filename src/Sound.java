
public class Sound {

	/*
	 * =========================================================================
	 * Sound class, not the most nessisary of classes seeing how EZSound pretty
	 * much is that already but it seemed help full to organize the project and
	 * keep the main as clutter free as possible
	 * =========================================================================
	 */
	EZSound weapon, speed, shield, theme, health, missile, spawn, bounce;

	public Sound() {
		weapon = EZ.addSound("upgrade1.wav");
		speed = EZ.addSound("speedUpgrade.wav");
		shield = EZ.addSound("upgrade2.wav");
		theme = EZ.addSound("theme2.wav");
		health = EZ.addSound("healthUpgrade.wav");
		missile = EZ.addSound("bazooka.wav");
		bounce = EZ.addSound("upgrade4.wav");
		spawn = EZ.addSound("upgrade3.wav");

	}

	public void fire() {
		missile.play();
	}

	public void themeLoop() {
		theme.loop();
	}

	public void weaponUpgrade() {
		weapon.play();
	}

	public void speedUpgrade() {
		speed.play();
	}

	public void shieldUpgrade() {
		shield.play();
	}

	public void upgradeSpawn() {
		spawn.play();
	}

	public void heal() {
		health.play();
	}

	public void bounce() {
		bounce.play();
	}

}