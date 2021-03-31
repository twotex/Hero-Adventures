import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * The map class is used to simulate a game map and possible positioning within it
 * @author Eric Lee 2018
 *
 */
public class Map {

	/**
	 * The two dimensional map array is used to store the current map
	 */
	private char [][] map;
	
	/**
	 * The two dimensional revealed array is used to store the hidden, revealed, and character positions within a map
	 */
	private boolean [][] revealed;
	
	
	/**
	 * The Map constructor allocates space for the map and revealed two dimensional arrays
	 */
	public Map() {
		map = new char [5][5];
		revealed = new boolean [5][5];
	}
	
	
	/**
	 * The loadMap method loads the correct map from a file and stores the results in the 2d array called map
	 * @param mapNum The current level
	 * @throws FileNotFoundException
	 */
	public void loadMap(int mapNum) throws FileNotFoundException {
		
		//Set all elements of revealed array to false
		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
			{
				revealed[row][col] = false;
			}
		}
		
		
		File map1 = new File("Map1.txt");
		File map2 = new File("Map2.txt");
		File map3 = new File("Map3.txt");
		Scanner inputFile;
		
		if (mapNum % 3 == 0)
		{
			// load map 3
			inputFile = new Scanner(map3);
		}
		
		else if (mapNum % 3 == 1)
		{
			//load map 1
			inputFile = new Scanner(map1);
		}
		
		else 
		{
			//load map 2
			inputFile = new Scanner(map2);
		}
		
		String dummyString;
		int daRow = 0;
		int daColumn = 0;
		while (inputFile.hasNextLine()) {
		
			dummyString = inputFile.nextLine();
			String [] tokens = dummyString.split(" ");
			
			for (String s: tokens)
			{
				map[daRow][daColumn] = s.charAt(0);
				daColumn++;
			}
			daColumn = 0;
			daRow++;
		}
		
		inputFile.close();
		
	}
	
	
	/**
	 * The getCharAtLoc method returns the character that is at the test point
	 * @param p The Point to be tested
	 * @return The character at the test point
	 */
	public char getCharAtLoc(Point p) {
		int col = (int)p.getX();
		int row = (int)p.getY();
		
		char daChar = map[row][col];
		return daChar;
		
	}
	
	
	/**
	 * The displayMap method displays the current map to the console with an indicator showing the player's position
	 * @param p The player's position
	 */
	public void displayMap(Point p) {
		
		int pRow = (int)p.getY();
		int pCol = (int)p.getX();
		revealed[pRow][pCol] = true;
		
		for (int daRow = 0; daRow < map.length; daRow++)
		{
			for (int daCol = 0; daCol < map[daRow].length; daCol++)
			{
				if (revealed[daRow][daCol] == false)
				{
					System.out.print('x');
					System.out.print(' ');
				}
				
				else if (daRow == pRow && daCol == pCol)
				{
					System.out.print('*');
					System.out.print(' ');
				}
				
				else
				{
					System.out.print(map[daRow][daCol]);
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}
	
	
	/**
	 * The findStart method finds the starting point for a map and returns it.
	 * @return The starting point for a given map
	 */
	public Point findStart() {
		
		int col = 0;
		int row = 0;
		for (int daRow = 0; daRow < map.length; daRow++)
		{
			for (int daCol = 0; daCol < map[daRow].length; daCol++)
			{
				if (map[daRow][daCol] == 's')
				{
					row = daRow;
					col = daCol;
				}
			}
		}
		
		Point daPoint = new Point(col, row);
		return daPoint;
		
	}
	
	
	/**
	 * The reveal method reveals a point that has been explored
	 * @param p The point to reveal
	 */
	public void reveal(Point p) {
		int col = (int)p.getX();
		int row = (int)p.getY();
		
		revealed[row][col] = true;
	}
	
	
	/**
	 * The removeCharAtLoc method replaces the current character at a point with an 'n'
	 * @param p The point that will have its character replaced
	 */
	public void removeCharAtLoc(Point p) {
		int col = (int)p.getX();
		int row = (int)p.getY();
		
		map[row][col] = 'n';
		
	}
	
}
