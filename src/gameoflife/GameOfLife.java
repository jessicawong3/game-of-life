/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Jessica Wong
 */
public class GameOfLife {
    
    // pseudocode for searching for neighbours can be found as comments in the countNeighbours method
    
    public boolean[][] grid;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
    /**
     * Constructor that creates a game board as a boolean grid
     * @param gridSize The length/width of the grid
     */
    public GameOfLife(int gridSize) {
        // create the grid
        grid = new boolean[gridSize][gridSize];
        
        // set all cells to false
        for (int i = 0; i < grid.length; i++){
            for (int j=0;j<grid[0].length;j++){
                grid[i][j] = false;
            }
        }
    }
    
    
    /**
     * Scans all the neighboring cells around the given cell coordinates and returns the number of live cells.
     * @param x The column number in the grid
     * @param y The row number in the grid
     * @return Returns the number of true/"live" cells
     */
    public int countNeighbours(int x, int y) {
        // create a counter for the live cells
        int counter = 0;
        
        // for each cell around the indicated cell (for each row, each column)
        for (int r = (y - 1); r <= (y + 1); r++) {
            for (int c = (x - 1); c <= (x + 1); c++) {   
                try {
                    // check if the cell is live (the chosen cell does not count as one)
                    if (grid[r][c] == true && !(r == y && c == x)) {
                        // count goes up if cell is live
                        counter += 1;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // do nothing if the cell does not exist
                }
            }
        }
        // return the count of live neighbours
        return counter;
    }
    
    /**
     * Makes changes for one round of the simulation
     */
    public void oneRound() {
        // new grid for getting new living status without interfering with calculations
        boolean[][] newGrid = new boolean[grid.length][grid.length];
        
        // for each cell on the grid
        for (int r = 0; r <= (grid.length-1); r++) {
            for (int c = 0; c <= (grid.length-1); c++) {    
                // if the cell (dead or  alive) has three neighbours it lives
                if (countNeighbours(c, r) == 3) {
                    newGrid[r][c] = true;
                // else if the cell is alive and it has two neighbours it lives
                } else if (grid[r][c] == true && countNeighbours(c,r) == 2) {
                    newGrid[r][c] = true;
                } else {
                    newGrid[r][c] = false;
                }
            }
        }
        
        // each cell of the orginal grid becomes the adjacent cell of new grid with all the necessary changes
        for (int r = 0; r <= (grid.length-1); r++) {
            for (int c = 0; c <= (grid.length-1); c++) {    
                grid[r][c] = newGrid[r][c];
            }
        }        
    }
    
    /**
     * Represents the game grid in a String format
     * @return Returns the game grid in a String format
     */
    @Override
    public String toString() {
        // create a blank string
        String s = "";
        // for all the cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // add the cell's value to the string
                s += grid[i][j] + " ";                
            }
            // start a new line for each row
            s += '\n';
        }
        return s;
    }
        
}
