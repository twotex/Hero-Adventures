
/**
 * The Entity class is a superclass that is meant to represent both the hero and the enemy. Both Hero and Enemy classes inherit from it.
 * @author Eric Lee 2018
 *
 */
public abstract class Entity {
	
	/**
	 * The Entity's name
	 */
	private String name;
	
	/**
	 * The Entity's battlecry
	 */
	private String quip;
	
	/**
	 * The Entity's Level
	 */
	private int level;
	
	/**
	 * The Entity's maxHP
	 */
	private int maxHp;
	
	/**
	 * The Entity's current HP
	 */
	private int hp;
	
	/**
	 * The Entity Constructor uses the data passed into its arguments to initialize the given Entity
	 * @param n The Entity's name
	 * @param q The Entity's quip
	 * @param l The Entity's level
	 * @param m The Entity's maxHp
	 */
	public Entity(String n, String q, int l, int m) {
		name = n;
		quip = q;
		level = l;
		maxHp = m;
		hp = m;
		
		//Double CHECK ABOVE!!
	}
	
	/**
	 * The attack method is an abstract method that must be overridden in any class that inherits from Entity
	 * @param e The entity to attack
	 */
	public abstract void attack(Entity e);
	
	
	/**
	 * The getName method returns the Entity's name
	 * @return The Entity's name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * The getQuip method returns the Entity's battlecry
	 * @return The Entity's battlecry
	 */
	public String getQuip() {
		return quip;
	}
	
	
	/**
	 * The getLevel method returns the Entity's level
	 * @return the Entity's level
	 */
	public int getLevel() {
		return level;
	}
	
	
	/**
	 * The getHP method returns the Entity''s current HP
	 * @return The Entity's current HP.
	 */
	public int getHP() {
		return hp;
	}
	
	
	/**
	 * The getMaxHP method returns the Entity's MaxHP
	 * @return The Entity's MaxHP
	 */
	public int getMaxHP() {
		return maxHp;
	}
	
	
	/**
	 * The increaseLevel method increases the Entity's level
	 */
	public void increaseLevel() {
		level++;
	}
	
	
	/**
	 * The heal method heals the Entity for a given amount
	 * @param h The amount to heal for
	 */
	public void heal(int h) {
		hp += h;
	}
	
	
	/**
	 * The take damage method reduces an enemy hit points by a given amount
	 * @param h The amount of damage to subtract from hp
	 */
	public void takeDamage(int h) {
		hp -= h;
	}
	
	
	/**
	 * The increaseMaxHP method increases the Entity's MaxHP
	 * @param h The amount to increase the current HP by
	 */
	public void increaseMaxHP(int h) {
		maxHp += h;
	}
	
	
	/**
	 * The decreaseMaxHP method decreases the Entity's MaxHP
	 * @param h The amount to decrease the current HP by
	 */
	public void decreaseMaxHP(int h) {
		maxHp -= h;
	}
	
	
	/**
	 * The display method displays an Entity's name, level, current HP, and MaxHP
	 */
	public void display() {
		System.out.println(name + " Lvl:" + level);
		System.out.println("HP: " + hp + "/" + maxHp);
	}

}
