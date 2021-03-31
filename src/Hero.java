import java.util.*;
import java.awt.*;

/**
 * The hero class is used to represent the hero and all the possible commands the hero can execute. This class inherits from Entity
 * and implements Magical
 * @author Eric Lee 2018
 *
 */
public class Hero extends Entity implements Magical {

	/**
	 * The items Arraylist holds the items in the hero's inventory
	 */
	private ArrayList<Item> items;
	
	/**
	 * The map variable holds the current Map that the hero is on
	 */
	private Map map;
	
	/**
	 * The location variable holds the current Point that the hero is at
	 */
	private Point location;
	
	/**
	 * The gold variable holds the current amount of gold that the hero has
	 */
	private int gold;
	
	/**
	 * The hero constructor calls the Entity constructor with its given data and initializes its fields 
	 * so they are ready for use
	 * @param n The Hero's name
	 * @param q The Hero's quip
	 * @param m The starting map
	 */
	public Hero(String n, String q, Map m) {
		super(n, q, 1, 15);
		items = new ArrayList<Item>();
		map = m;
		location = map.findStart();
		gold = 10;
	}
	
	
	/** The attack method attacks a given entity, causing a decrease in the entity's hit points
	 * @param e The Entity to Attack
	 */
	@Override
	public void attack(Entity e) {
		
		//@SuppressWarnings("resource")
		//Scanner keyboard = new Scanner(System.in);
		int choice;
		Random randomNumbers = new Random();
		int physicalDamage = randomNumbers.nextInt(5) + 1;
		int magicalDamage;
		
		System.out.println("1. Physical Attack");
		System.out.println("2. Magical Attack");
		if (this.hasPotion())
		{
			System.out.println("3. Use Health Potion");
			choice = CheckInput.getIntRange(1, 3);
		}
		else {
			choice = CheckInput.getIntRange(1, 2);
		}
		
		if (choice == 1)
		{
			e.takeDamage(physicalDamage * this.getLevel());
			System.out.println(this.getName() + " attacks " + e.getName() + " for "
					+ (physicalDamage * this.getLevel()) + " damage.");
			
			
		}
		
		else if (choice == 2)
		{
			int secondChoice;
			System.out.println(Magical.MAGIC_MENU);
			
			secondChoice = CheckInput.getIntRange(1, 3);
			
			if (secondChoice == 1)
			{
				magicalDamage = this.magicMissile();
				e.takeDamage(magicalDamage * this.getLevel());
				System.out.println(this.getName() + " hits " + e.getName() + " with a Magic Missile for "
			+ (magicalDamage * this.getLevel()) + " damage.");
			}
			
			else if (secondChoice == 2)
			{
				magicalDamage = this.fireball();
				e.takeDamage(magicalDamage * this.getLevel());
				System.out.println(this.getName() + " hits " + e.getName() + " with a Fireball for "
			+ (magicalDamage * this.getLevel()) + " damage.");
			}
			
			else if (secondChoice == 3)
			{
				magicalDamage = this.thunderclap();
				e.takeDamage(magicalDamage * this.getLevel());
				System.out.println(this.getName() + " hits " + e.getName() + " with a Thunderclap for "
			+ (magicalDamage * this.getLevel()) + " damage.");
			}
			
		}
		
		else if (choice == 3 && this.hasPotion())
		{
			int amountToHeal;
			
			if (this.getMaxHP() - this.getHP() < 25)
			{
				amountToHeal = this.getMaxHP() - this.getHP();
			}
			
			else
			{
				amountToHeal = 25;
			}
			
			this.heal(amountToHeal);
			this.removeItem("Health Potion");
			
			System.out.println(this.getName() + " has used a health potion.");
			System.out.println("Current Health: " + this.getHP() + "/" + this.getMaxHP());
		}
		//CAUSES ERROR!
		//keyboard.close();
	}
	
	
	/**
	 * The display method calls the Entity's version of the display method to display the hero's info
	 */
	@Override
	public void display() {
		super.display();
	}
	
	
	/**
	 * The displayItems method displays the current items in the hero's inventory
	 */
	public void displayItems() {
		
		System.out.println("Inventory:");
		for (int count = 0; count < items.size(); count++)
		{
			System.out.println(count + 1 + ". " + items.get(count).getName());
		}
	}
	
	
	/**
	 * The getNumItems method returns the number of items in the Hero's inventory
	 * @return The number of items in the Hero's inventory
	 */
	public int getNumItems() {
		return items.size();
	}
	
	
	/**
	 * The pickUpItem method picks up an item at the current location or leaves it there if 
	 * the hero's inventory is full
	 * @param i The item to pickup
	 * @return Indicates whether or not the item was picked up
	 */
	public boolean pickUpItem(Item i) {
		if (items.size() < 5)
		{
			switch (i.getName())
			{
				case "Health Potion":
				items.add(i);
				return true;
				
				case "Shield": this.increaseMaxHP(15);
				items.add(i);
				return true;
			
				case "Helm": this.increaseMaxHP(10);
				items.add(i);
				return true;
			
				case "Belt": this.increaseMaxHP(2);
				items.add(i);
				return true;
			
				case "Ring": this.increaseMaxHP(5);
				items.add(i);
				return true;
			
				case "Gloves": this.increaseMaxHP(5);
				items.add(i);
				return true;
			
				case "Boots": this.increaseMaxHP(10);
				items.add(i);
				return true;
			
				case "Ringmail": this.increaseMaxHP(15);
				items.add(i);
				return true;
				
				case "Bag o' Gold": this.collectGold(20);
				return true;
				
				default:
				return false;
	        }
		}
		
		
		else
		{
			return false;
		}
	}
	
	
	/**
	 * The removeItem method removes a specific item from the Hero's inventory
	 * @param n The name of the item to remove
	 * @return The item that was removed
	 */
	public Item removeItem(String n) {
	
		// initializing variable
		Item daItem = items.get(0);
		boolean itemFound = false;
		
		for (int count = 0; count < items.size(); count++)
		{
			if (items.get(count).getName().equals(n) && !itemFound)
			{
				daItem = items.get(count);
				items.remove(count);
				itemFound = true;
				
				// return daItem;
			}
		}
		
		switch (n)
		{
			case "Health Potion": 
				break;
			case "Shield": this.decreaseMaxHP(15);
				break;
			case "Helm": this.decreaseMaxHP(10);
				break;
			case "Belt": this.decreaseMaxHP(2);
				break;
			case "Ring": this.decreaseMaxHP(5);
				break;
			case "Gloves": this.decreaseMaxHP(5);
				break;
			case "Boots": this.decreaseMaxHP(10);
				break;
			case "Ringmail": this.decreaseMaxHP(15);
				break;
		}
		
		return daItem;
	}
	
	
	/**
	 * The removeItem method removes a specific item from a specified index
	 * @param index The index of the item to remove
	 * @return The item that was removed
	 */
	public Item removeItem(int index) {
		
		Item daItem = items.get(index);
		items.remove(index);
		
		switch (daItem.getName())
		{
			case "Health Potion": 
				break;
			case "Bag o' Gold": this.spendGold(20);
				break;
			case "Shield": this.decreaseMaxHP(15);
				break;
			case "Helm": this.decreaseMaxHP(10);
				break;
			case "Belt": this.decreaseMaxHP(2);
				break;
			case "Ring": this.decreaseMaxHP(5);
				break;
			case "Gloves": this.decreaseMaxHP(5);
				break;
			case "Boots": this.decreaseMaxHP(10);
				break;
			case "Ringmail": this.decreaseMaxHP(15);
				break;
		}
		return daItem;
		
	}
	
	
	/**
	 * The hasPotion method indicates whether or not the Hero has a health potion 
	 * @return Returns true if hero has health potion. False otherwise.
	 */
	public boolean hasPotion() {
		for (int count = 0; count < items.size(); count++)
		{
			if (items.get(count).getName().equals("Health Potion"))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * The getLocation method returns the current location of the hero
	 * @return The Hero's current location
	 */
	public Point getLocation() {
		return new Point(location);
	}
	
	
	/**
	 * The goNorth method moves the hero one step north
	 * @return returns the character at the hero's new position. 'p' is returned for an invalid move
	 */
	public char goNorth() {
		int row = (int)location.getY();
		int col = (int)location.getX();
		if (row > 0)
		{
			location.setLocation(col, row - 1);
			map.reveal(location);
		}
		
		else {
			return 'p'; //p = error letter
		}
		
		return map.getCharAtLoc(location);
	}
	
	
	/**
	 * The goSouth method moves the hero one step south
	 * @return returns the character at the hero's new position. 'p' is returned for an invalid move
	 */
	public char goSouth() {
		int row = (int)location.getY();
		int col = (int)location.getX();
		if (row < 4)
		{
			location.setLocation(col, row + 1);
			map.reveal(location);
		}
		
		else {
			return 'p'; //p = error letter
		}
		
		return map.getCharAtLoc(location);
	}
	
	
	/**
	 * The goEast method moves the hero one step east
	 * @return returns the character at the hero's new position. 'p' is returned for an invalid move
	 */
	public char goEast() {
		int row = (int)location.getY();
		int col = (int)location.getX();
		if (col < 4)
		{
			location.setLocation(col + 1, row);
			map.reveal(location);
		}
		
		else {
			return 'p'; //p = error letter
		}
		
		return map.getCharAtLoc(location);
	}
	
	
	/**
	 * The goWest method moves the hero one step west
	 * @return returns the character at the hero's new position. 'p' is returned for an invalid move
	 */
	public char goWest() {
		int row = (int)location.getY();
		int col = (int)location.getX();
		if (col > 0)
		{
			location.setLocation(col - 1, row);
			map.reveal(location);
		}
		
		else {
			return 'p'; //p = error letter
		}
		
		return map.getCharAtLoc(location);
	}
	
	
	/**
	 * The getGold method returns the hero's current gold
	 * @return The hero's gold
	 */
	public int getGold() {
		return gold;
	}
	
	
	/**
	 * The collectGold method collects a certain amount of gold and adds it to the gold field
	 * @param g The amount of gold to collect
	 */
	public void collectGold(int g) {
		gold += g;
	}
	
	
	/**
	 * The spendGold method spends a certain amount of gold and subtracts it from the gold field
	 * @param g The amount of gold to spend
	 */
	public void spendGold(int g) {
		gold -= g;
	}

	
	/**
	 * The magicMissile method is called when the hero uses a Magic Missile attack.
	 * @return The amount of damage from the Magic Missile attack
	 */
	@Override
	public int magicMissile() {
		Random randomNumbers = new Random();
		int xDmg = randomNumbers.nextInt(5) + 1;
		return xDmg;
	}

	
	/**
	 * The fireball method is called when the hero uses a Fireball attack
	 * @return The amount of damage from the Fireball attack
	 */
	@Override
	public int fireball() {
		Random randomNumbers = new Random();
		int xDmg = randomNumbers.nextInt(5) + 1;
		return xDmg;
	}

	
	/**
	 * The thunderclap method is called when the hero uses a Thunderclap attack
	 * @return The amount of damage from the Thunderball attack
	 */
	@Override
	public int thunderclap() {
		Random randomNumbers = new Random();
		int xDmg = randomNumbers.nextInt(5) + 1;
		return xDmg;
	}
	

}
