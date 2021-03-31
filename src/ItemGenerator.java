import java.util.*;

import java.io.*;

/**
 * The ItemGenerator class is used to generate an Item at random. It reads in all the possible items available from a txt file
 * @author Eric Lee 2018
 *
 */
public class ItemGenerator {
	
	/**
	 * The itemList ArrayList is used to store all the items possible within the game
	 */
	private ArrayList<Item> itemList;
	
	/**
	 * The ItemGenerator constructor opens the ItemList.txt file and reads data from it.
	 * The data is stored in the itemList ArrayList for future use
	 * @throws FileNotFoundException
	 */
	public ItemGenerator() throws FileNotFoundException
	{
		itemList = new ArrayList<Item>();
		File daFile = new File("ItemList.txt");
		Scanner inputFile = new Scanner(daFile);
		String dummyString;
		String daName;
		int daValue;
		String [] tokens;
		
		while (inputFile.hasNextLine())
		{
			dummyString = inputFile.nextLine();
			tokens = dummyString.split(",");
			daName = tokens[0];
			daValue = Integer.parseInt(tokens[1]);
			itemList.add(new Item(daName, daValue));
		}
		
		inputFile.close();
	}
	
	
	/**
	 * The generateItem method selects a random item from itemList and returns it
	 * @return The random item generated
	 */
	public Item generateItem()
	{
		Random randomNumbers = new Random();
		int daRandom = randomNumbers.nextInt(itemList.size());
		Item daItem = itemList.get(daRandom);
		return new Item(daItem.getName(), daItem.getValue());
	}
	
	
	/**
	 * The getPotion method gives the hero a Health Potion
	 * @return The Health Potion
	 */
	public Item getPotion()
	{
		return new Item(itemList.get(0).getName(), itemList.get(0).getValue());
		
	}

}
