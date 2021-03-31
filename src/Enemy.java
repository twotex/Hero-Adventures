import java.util.*;

/**
 * The Enemy class represents is used to represent an Enemy. It inherits from Entity
 * @author Eric Lee 2018
 *
 */
public class Enemy extends Entity{

	/**
	 * The Item of the Enemy
	 */
	private Item item;
	
	
	/**
	 * The Enemy constructor calls the Entity constructor with the variables it has received as input and initializes item
	 * @param n   The Enemy's name
	 * @param q   The Enemy's quip
	 * @param l   The Enemy's level
	 * @param m   The Enemy's maxHP
	 * @param i   The Enemy's Item
	 */
	public Enemy(String n, String q, int l, int m, Item i) {
		super(n, q, l, m);
		item = i;
	}
	
	/**
	 * The getItem method returns the Enemy's Item
	 * @return The Enemy's Item
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * The attack method attacks an enemy for a random amount of damage
	 * @param e The Entity to attack
	 */
	@Override
	public void attack(Entity e){
		
		Random randomNumbers = new Random();
		int daDamage = randomNumbers.nextInt(5) + 1;
		int finalDamage = daDamage * e.getLevel();
		e.takeDamage(finalDamage);
		System.out.println(this.getName() + " attacks " + e.getName() + " for " + finalDamage
				+ " damage.");
		
		
	}
	
}
