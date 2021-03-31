import java.util.*;

/**
 * The MagicalEnemy class is used to represent a Magical Enemy. It contains all the possible attacks that a Magical Enemy can execute.
 * @author Eric Lee 2018
 *
 */
public class MagicalEnemy extends Enemy implements Magical {

	/**
	 * The MagicalEnemy constructor calls the Enemy constructor with data that was passed into its parameters
	 * @param n  The Magical Enemy's name
	 * @param q  The Magical Enemy's quip
	 * @param l  The Magical Enemy's level
	 * @param m  The Magical Enemy's maxhp
	 * @param i  The Magical Enemy's item
	 */
	public MagicalEnemy(String n, String q, int l, int m, Item i) {
		super(n, q, l, m, i);
	}
	
	
	/**
	 * The attack method attacks an Entity for a random amount
	 * @param e The Entity to attack
	 */
	@Override
	public void attack(Entity e) {
		Random randomNumbers = new Random();
		int num = randomNumbers.nextInt(3);
		int daDmg = 0;
		
		if (num == 0)
		{
			daDmg = magicMissile();
			e.takeDamage(daDmg * e.getLevel());
			System.out.println(this.getName() + " hits " + e.getName() + " with a Magic Missile for " 
					+ (daDmg * e.getLevel()) + " damage.");
		}
		
		else if (num == 1)
		{
			daDmg = fireball();
			e.takeDamage(daDmg * e.getLevel());
			System.out.println(this.getName() + " hits " + e.getName() + " with a Fireball for " 
					+ (daDmg * e.getLevel()) + " damage.");
		}
		
		else if (num == 2)
		{
			daDmg = thunderclap();
			e.takeDamage(daDmg * e.getLevel());
			System.out.println(this.getName() + " hits " + e.getName() + " with a Thunderclap for " 
					+ (daDmg * e.getLevel()) + " damage.");
		}
		
		
		
	}
	
	
	/**
	 * The magicMissile method is called when a MagicalEnemy decides to attack with a Magic Missile
	 * @return The amount of damage done by Magic Missile
	 */
	@Override
	public int magicMissile() {

		Random randomNumbers = new Random();
		int rand = randomNumbers.nextInt(5) + 1;
		return rand;
	}
	
	
	/**
	 * The fireball method is called when a MagicalEnemy decides to attack with a Fireball
	 * @return The amount of damage done by Fireball
	 */
	@Override
	public int fireball() {
		
		Random randomNumbers = new Random();
		int rand = randomNumbers.nextInt(5) + 1;
		return rand;
	}
	
	
	/**
	 * The thunderclap method is called when a MagicalEnemy decides to attack with a Thunderclap
	 * @return The amount of damage done by Thunderclap
	 */
	@Override
	public int thunderclap() {
		Random randomNumbers = new Random();
		int rand = randomNumbers.nextInt(5) + 1;
		return rand;
	}
	
	
}
