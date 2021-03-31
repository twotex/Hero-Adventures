import java.util.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * The Main class is used to encapsulate the main components and functionality within the game.
 * @author Eric Lee 2018
 *
 */
public class Main {

	/**
	 * The main method executes the commands and methods generated with the default package to deliver a complete working game.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		String heroName;
		String heroBattlecry;
		int levelCounter = 1;
		boolean characterAlive = true;
		Map daMap = new Map();
		
		System.out.print("What is your name, traveler? ");
		heroName = CheckInput.getString();
		System.out.print("What is your battlecry, " + heroName + "? ");
		heroBattlecry = CheckInput.getString();
		
		
		daMap.loadMap(levelCounter);
		Hero daHero = new Hero(heroName, heroBattlecry, daMap);
		
		int daDirection = 0;
		ItemGenerator iGenerator = new ItemGenerator();
		EnemyGenerator eGenerator = new EnemyGenerator(iGenerator);
		
		while (daDirection != 5 && characterAlive)
		{
			daHero.display();
			System.out.println("$: " + daHero.getGold());
			daHero.displayItems();
			daMap.displayMap(daHero.getLocation());
		
			System.out.println("1. Go North");
			System.out.println("2. Go South");
			System.out.println("3. Go East");
			System.out.println("4. Go West");
			System.out.println("5. Quit");
		
			daDirection = CheckInput.getIntRange(1, 5);
			char results = 'z';
			
			switch(daDirection)
			{
				case 1: results = daHero.goNorth();
				break;
				case 2: results = daHero.goSouth();
				break;
				case 3: results = daHero.goEast();
				break;
				case 4: results = daHero.goWest();
				break;
				case 5:
					break;
				default:
					break;	
			}
		
			if (results == 'm')
			{
				boolean fightComplete;
				
			
				fightComplete = monsterRoom(daHero, daMap, eGenerator);
			
				if (fightComplete) {
				
					//Your character killed the enemy
					if (daHero.getHP() > 0) {
					daMap.removeCharAtLoc(daHero.getLocation());
					}
					
					//The enemy killed your character
					else {
						characterAlive = false;
					}
				}
		   }
			
			else if (results == 'n')
			{
				System.out.println("There was nothing here.");
			}
			
			else if (results == 'i')
			{
				itemRoom(daHero, daMap, iGenerator);
			}
			
			else if (results == 'p')
			{
				System.out.println("You cannot go to that location.");
			}
			
			else if (results == 's')
			{
				store(daHero, iGenerator);
			}
			
			else if (results == 'f')
			{
				levelCounter++;
				daMap.loadMap(levelCounter);
				daHero.increaseLevel();
				daHero.increaseMaxHP(10);
			}
	  }
		
		keyboard.close();
	}
	
	
	/**
	 * The monsterRoom method executes the instructions needed when a hero enters a monster's room
	 * @param h The Hero
	 * @param m The Current Map
	 * @param eg The Enemy Generator
	 * @return Returns true if fight was completed until the end or false if the player ran
	 */
	public static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg) {
		// reveal point
		// m.getCharAtLoc(currentLocation) == 'm'
		//@SuppressWarnings("resource")
		//Scanner daKeyboard = new Scanner(System.in);
		
		Enemy daEnemy = eg.generateEnemy(h.getLevel());
		int userInput = 0;
		boolean bothAlive = true;
		boolean fightCompleted = false;
		Point currentLocation = h.getLocation();
		m.reveal(currentLocation);
		
		System.out.println("You've encountered a " + daEnemy.getName());
		
		
		while (userInput != 2 && bothAlive)
		{
			
			daEnemy.display();
			System.out.println("1. Fight");
			System.out.println("2. Run Away");
			
			userInput = CheckInput.getIntRange(1, 2);
			
			if (userInput == 1) 
			{
				bothAlive = fight(h, daEnemy);
				
				if (bothAlive == false) 
				{
					fightCompleted = true;
				}
			}
			
			
		}
			
			if (userInput == 2) 
			{
				Random rand = new Random();
				int daNum = rand.nextInt(4);
				char daLetter = 'p';
				
				while (daLetter == 'p')
				{
					switch (daNum)
					{
						case 0: daLetter = h.goNorth();
						break;
						case 1: daLetter = h.goSouth();
						break;
						case 2: daLetter = h.goEast();
						break;
						case 3: daLetter = h.goWest();
						break;
						default: daLetter = 'p';
						daNum = rand.nextInt(4); //for next cycle
					}
				}
				
				fightCompleted = false;
			}
			
			//CAUSES ERROR FOR SOME REASON
			//daKeyboard.close();
			return fightCompleted;	
			
			
	}
		
	
	/**
	 * The fight method simulates a fight between a hero and an enemy
	 * @param h The Player's Hero
	 * @param e The Current Enemy
	 * @return Returns true if both enemies are alive after both attacks or false if one has died
	 */
	public static boolean fight(Hero h, Enemy e) {
		
		int enemysPreviousHP = e.getHP();
		h.attack(e);
	
		// If Enemy is still alive
		if (e.getHP() > 0)
		{
			e.attack(h);
			if (h.getHP() > 0)
			{
				System.out.println(h.getName() + " terrifyingly exclaims \"" + h.getQuip() + "\"!");
				
				//If Enemy was attacked. Skip if user took a Health Potion instead of attacking.
				if (e.getHP() != enemysPreviousHP)
				{
					System.out.println(e.getName() + " frightfully shrieks \"" + e.getQuip() + "\"!");
				}
				
				return true;
			}
			
			else
			{
				System.out.println("You died");
				return false;
			}
		}
		
		else 
		{
			System.out.println("You defeated the " + e.getName() + "!");
			
			if (h.pickUpItem(e.getItem()))
			{
				System.out.println("You received a " + e.getItem().getName() + " from its corpse.");
			}
			
			//Item left at location
			else {
				System.out.println("Your inventory is full. Item deleted.");
			}
			
			return false;
		}
	}
	
	
	/**
	 * The store method simulates the store for when the hero travels back to the map's original starting location
	 * @param h The Player's Hero
	 * @param ig The Item Generator object
	 */
	public static void store(Hero h, ItemGenerator ig) {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		
		while (choice != 3)
		{
		System.out.println("Hello, " + h.getName());
		System.out.println("1. Buy Potions");
		System.out.println("2. Sell Items");
		System.out.println("3. Exit");
		
		choice = CheckInput.getIntRange(1, 3);
		
		if (choice == 1) {
			
			if (h.getGold() >= 10 && h.getNumItems() < 5)
			{
				System.out.println("Here's your potion, " + h.getName());
				h.pickUpItem(ig.getPotion());
				h.spendGold(10);
			}
			
			else if (h.getGold() < 10)
			{
				System.out.println("You don't have enough gold to purchase a Health Potion, " + h.getName());
			}
			
			else if (h.getNumItems() >= 5)
			{
				System.out.println("You don't have enough inventory room for a Health Potion," + h.getName());
			}
		}
		
		else if (choice == 2) {
			int userInput;
			
			System.out.println("Choose an item to sell: ");
			h.displayItems();
			System.out.println("0. Leave");
			userInput = keyboard.nextInt();
			
			if (userInput != 0)
			{
				h.removeItem(userInput - 1);
				h.collectGold(5);
			}
			
		}
		
		else if (choice == 3){
			
		}
			//Do Nothing
		}
		//causes error
		//keyboard.close();
	}
	
	
	/**
	 * The itemRoom method simulates the scenario when the hero enters a room that has an item
	 * @param h The Player's Hero
	 * @param m The Current Map
	 * @param ig The ItemGenerator Object
	 */
	public static void itemRoom(Hero h, Map m, ItemGenerator ig) {
		Point currentLocation = h.getLocation();
		m.reveal(currentLocation);
		Item daItem = ig.generateItem();
		boolean results = h.pickUpItem(daItem);
		
		if (results) {
			m.removeCharAtLoc(currentLocation);
			System.out.println("You found a " + daItem.getName());
		}
		
		else {
			System.out.println("Item found but Inventory is full. Item left at current location");
		}
		
	}
	
	

}
