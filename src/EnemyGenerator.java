import java.util.*;
import java.io.*;

/**
 * The EnemyGenerator class is used to generate an Enemy at Random. The enemy generated will either be 
 * magical or physical
 * @author Eric Lee 2018
 *
 */
public class EnemyGenerator {

	/**
	 * The enemyList ArrayList is used to store the list of possible Enemies
	 */
	private ArrayList<Enemy> enemyList;
	
	/**
	 * The ig ItemGenerator is used to generate an item at random
	 */
	private ItemGenerator ig;
	
	/**
	 * The EnemyGenerator constructor reads in input from the EnemyList.txt file and saves it to the enemyList array
	 * @param x The ItemGenerator Object
	 * @throws FileNotFoundException
	 */
	public EnemyGenerator(ItemGenerator x) throws FileNotFoundException
	{
		enemyList = new ArrayList<Enemy>();
		ig = new ItemGenerator();
		File daFile = new File("EnemyList.txt");
		Scanner inputFile = new Scanner(daFile);
		String dummyString;
		String daName;
		String daQuip;
		String daHp;
		int daHpConverted;
		String [] tokens;
		
		//String daType;
		//char daTypeConverted;
		
		while (inputFile.hasNextLine())
		{
			dummyString = inputFile.nextLine();
			tokens = dummyString.split(",");
			daName = tokens[0];
			daQuip = tokens[1];
			daHp = tokens[2];
			daHpConverted = Integer.parseInt(daHp);
			
			// daType = tokens[3];
			//daTypeConverted = daType.charAt(0);
			
			enemyList.add(new Enemy(daName, daQuip, 1, daHpConverted, x.generateItem()));
		}
		
		inputFile.close();
	}
	
	/**
	 * The generateEnemy method generates a random Magical or Physical Enemy
	 * @param level The hero's level
	 * @return The enemy that has been generated
	 */
	public Enemy generateEnemy(int level)
	{
		Random randomNumbers = new Random();
		int daRandom = (randomNumbers.nextInt(enemyList.size()));
		Enemy daEnemy = enemyList.get(daRandom);
		
		
		if (daEnemy.getName().equals("Froglok") || daEnemy.getName().equals("Kobold")
				|| daEnemy.getName().equals("Goblin"))
		{
			return new MagicalEnemy(daEnemy.getName(), daEnemy.getQuip(), level, daEnemy.getHP() * level, ig.generateItem());
			
		}
		
		else
		{
			return new Enemy(daEnemy.getName(), daEnemy.getQuip(), level, daEnemy.getHP() * level, ig.generateItem());
		}
	}
	
	
	
}
