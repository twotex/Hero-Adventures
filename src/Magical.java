/**
 * The Magical interface is used to simulate a Magical Enemy in battle
 * @author Eric
 *
 */
public interface Magical {
	
	/**
	 * The MAGIC_MENU string is used when the hero has chosen a Magic attack and the Magic Menu needs to be displayed
	 */
	public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap";
	
	
	/**
	 * The magicMissile method is a method that must be implemented for any class that uses the Magical interface
	 * @return The Magic Missile damage
	 */
	public int magicMissile();
	
	
	/**
	 * The fireball method is a method that must be implemented for any class that uses the Magical interface
	 * @return The Fireball damage
	 */
	public int fireball();
	
	
	/**
	 * The thunderclap method is a method that must be implemented for any class that uses the Magical interface
	 * @return The Thunderclap damage
	 */
	public int thunderclap();

}
