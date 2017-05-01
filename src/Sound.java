
public class Sound {
	EZSound weapon, speed, shield, theme, health, missile, tank1, tank2, spawn, bounce, titleScreen;

	public Sound() {
		weapon = EZ.addSound("upgrade1.wav");
		speed = EZ.addSound("speedUpgrade.wav");
		shield = EZ.addSound("upgrade2.wav");
		theme = EZ.addSound("theme2.wav");
		health = EZ.addSound("healthUpgrade.wav");
		missile = EZ.addSound("bazooka.wav");
		tank1 = EZ.addSound("tankAudio.wav");
		tank2 = EZ.addSound("tankAudio.wav");
		bounce = EZ.addSound("upgrade4.wav");
		spawn = EZ.addSound("upgrade3.wav");
		titleScreen = EZ.addSound("theme.wav");

	}

	

	public void fire() {
		missile.play();
	}

	public void tankFXplay() {
		tank1.loop();

	}

	public void tankFXpause() {
		tank1.pause();

	}
	public void themeLoop(){
		theme.loop();
	}

	public void weaponUpgrade() {
		weapon.play();
	}

	public void playMenuTheme() {
		titleScreen.loop();
	}

	public void stopMenuTheme() {
		titleScreen.stop();
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