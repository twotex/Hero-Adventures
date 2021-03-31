
/**
 * The Item class is used to represent an item in the game. The item has a name and a value.
 * @author Eric Lee 2018
 *
 */
public class Item {

	/**
	 * name represents the name of the item
	 */
	private String name;
	
	/**
	 * value represents the value of the item
	 */
	private int value;
	
	/** 
	 * The Item constructor accepts a item's name and value in its parameters and initializes its fields 
	 * with that data
	 * @param n Item name
	 * @param v Item value
	 */
	public Item(String n, int v)
	{
		name = n;
		value = v;
	}
	
	/**
	 * The getName method returns the name of the item
	 * @return The name of the item
	 */
	public String getName()
	{
		return name;
	}
	
	
	/**
	 * The getValue method returns the value of the item
	 * @return The item's value
	 */
	public int getValue()
	{
		return value;
	}
}
