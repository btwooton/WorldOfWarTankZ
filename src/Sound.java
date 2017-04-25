
public class Sound {
	EZSound weapon, speed, shield, theme, health, missile, tank1, tank2, spawn, bounce;

	public Sound() {
		weapon = EZ.addSound("upgrade1.wav");
		speed = EZ.addSound("speedUpgrade.wav");
		shield = EZ.addSound("upgrade2.wav");
		theme = EZ.addSound("theme.wav");
		health = EZ.addSound("healthUpgrade.wav");
		missile = EZ.addSound("bazooka.wav");
		tank1 = EZ.addSound("tankAudio.wav");
		tank2 = EZ.addSound("tankAudio.wav");
		bounce = EZ.addSound("upgrade4.wav");
		spawn = EZ.addSound("upgrade3.wav");
		theme.loop();

		pause();

	}

	void pause() {
		weapon.stop();
		speed.stop();
		shield.stop();
		health.stop();
		missile.stop();
	}

	public void fire() {
		missile.play();
	}

	public  void  tankFX() {
		if (EZInteraction.isKeyDown('w') || EZInteraction.isKeyDown('s') || EZInteraction.isKeyDown('a')
				|| EZInteraction.isKeyDown('d') || EZInteraction.isKeyDown('i') || EZInteraction.isKeyDown('j')
				|| EZInteraction.isKeyDown('k') || EZInteraction.isKeyDown('l')) {
			tank1.loop();
		}else{
			tank1.pause();
		}
			

	}

	/*public void tankSound() {
		if (tankFX()) {
			tank1.play();
		} else {
			tank1.pause();
		}
	}*/

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