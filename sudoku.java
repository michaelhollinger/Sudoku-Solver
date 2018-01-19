// Michael Hollinger
// COP 3505C - Spring 2018

import java.util.*;
import java.io.*;


public class sudoku {

	public static final int GRID_SIZE = 9;

	public static void main(String[] args)
	{

	//initialize scanner and set number of grids
	Scanner sc = new Scanner(System.in);	
	int numGrids = sc.nextInt();
	int caseNum = 1, i = 0;

	//create list of 2D arrays
	List<int[][]> gridList = new ArrayList<int[][]>(numGrids);

	while(numGrids > 0){

		//creates sudoku grid at specified index of i
		genGrid(gridList, sc);

		//initializes grid at index i of list to a temporary int[][]
		int[][] curGrid = gridList.get(i);

		//formatting!
		System.out.println("Test case " + caseNum + ":" + '\n');

		//if a solution exists for the current case, print it out.
		solveGrid(curGrid);
		if(solveGrid(curGrid))		
		{
			for(int j = 0; j < curGrid.length; j++)
			{
				for(int k = 0; k < curGrid.length; k++)
				{					
					System.out.print(" " + curGrid[j][k] + " ");

					if(k % 8 == 0 && k > 0)
					{
						System.out.println();
					}
				}
			}
			System.out.println('\n');
		}

		//if a solution does not exist.
		else
		{
			System.out.println("No solution possible." + '\n' + '\n');
		}
		
		//increment counters
		caseNum++;
		i++;
		numGrids--;

		}
	}

	//function to add a grid to the list
	public static void genGrid(List<int[][]> gridList, Scanner sc)
	{
		int[][] grid = new int[GRID_SIZE][GRID_SIZE];

		for(int i = 0; i < GRID_SIZE; i++)
		{
			for(int j = 0; j < GRID_SIZE; j++)
			{
				grid[i][j] = sc.nextInt();
			}
		}
		gridList.add(grid);
	}

	//Recursive, deterministic algorithm to solve the grid.
	public static boolean solveGrid(int[][] grid)
	{	

		for(int i = 0; i < GRID_SIZE; i++)
		{
			for(int j = 0; j < GRID_SIZE; j++)
			{
				if(grid[i][j] != 0)
				{
					continue;
				}	
						
				else
				{
					for(int num = 1; num <= 9; num++)
					{
						//check if num is valid in current grid
						if(valid(grid, i, j, num))
						{

						//if so, reassign num to current cell
						grid[i][j] = num;
					
						//recursively solve grid
						if(solveGrid(grid))
						{
							return true;
						}

						else
						{
							grid[i][j] = 0;
						}
					}
				}
					return false;
			}
		}
	}
		return true;
}

//checks each index of current 3x3 box, modulo 3
public static boolean box(int[][] grid, int row, int col, int n)
{
	row = row - row % 3;
	col = col - col % 3;

	for(int i = 0; i < 3; i++)
	{
		for(int j = 0; j < 3; j++)
		{
			if(grid[i + row][j + col] == n)
			{
				return true;
			}

		}
	}
		return false;
}

//checks each index of current row
public static boolean row(int[][] grid, int row, int n)
{	
	for(int j = 0; j < GRID_SIZE; j++)
	{
		if(grid[j][row] == n)
		{
			return true;
		}
	}
	return false;
}

//checks each index of current col
public static boolean col(int[][] grid, int col, int n)
{
	//column
	for(int i = 0; i < GRID_SIZE; i++)
	{
		if(grid[col][i] == n)
		{
			return true;
		}
	}	
	return false;
}

//if all 3 conditions are met, return true
public static boolean valid(int[][] grid, int row, int col, int n)
{
	if(!col(grid, row, n) && !row(grid, col, n) && !box(grid, row, col, n))
	{
		return true;
	}	
	return false;
}
}