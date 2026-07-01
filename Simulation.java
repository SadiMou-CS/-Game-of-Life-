// TO DO: add your implementation and JavaDocs.
// some javadocs are already done. if you add helper methods, be
// sure to write javadocs for those.

// big-O requirements are listed if they exist.
// if no requirement is listed, you may do as you wish.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *  The simulator. This tracks the cells in a grid
 *  and coordinates that with the display.
 *  
 *  @author Troy Acuff and [Your Name Here]
 */
public class Simulation {
    /**
	 *  The grid that holds the cell data.
     *  YOU MUST USE THIS. 
     *  Automated testing will be done on this variable directly.
	 */
    private DynamicArray<DynamicArray<Cell>> grid;
    /**
	 *  The number of rows the grid has.
	 */
    private int rows;
    
    /**
	 *  The number of cols the grid has.
	 */
    private int cols;
    
    /**
	 *  The  number of generations this sim has gone through.
	 */
    private int generations;

    /**
	 *  Main constructor.
     *  Initialize the instance variables.
	 *  
     *  @param rows the number of rows in the grid
     *  @param cols the number of columns in the grid
	 */
    public Simulation(int rows, int cols) {
        // set instance variables to default values (note rows and cols parameters to the constructor)

        // you may assume input parameters are valid

        // use this helper method to initialize the grid
        this.rows =rows;
        this.cols = cols;
        initializeGrid();
    }
    /**
	 *  Helper method to initialize the grid of Cells.
	 */
    private void initializeGrid() {
        // note that grid is DynamicArray OF DynamicArrays OF Cells
        // be sure to not only declare, but also INSTATIATE these objects

        // all cells created in this method should start DEAD.
        grid = new DynamicArray<>();
        for(int i =0; i< rows; i++)
        {
            DynamicArray<Cell> row = new DynamicArray<>();
            for(int j=0; j<cols; j++)
            {
                row.add(new Cell(false));
            }
            grid.add(row);

        }
    }
    /**
	 *  DO NOT CHANGE THIS, FOR GRADING PURPOSE ONLY.
     * 
     *  @return grid for automatic testing
	 */
    public DynamicArray<DynamicArray<Cell>> getGrid() {
        return grid;
    }
    /**
	 *  This is called when the user interacts with the grid.
     *  Sets the cell at row/col to Alive
     *  Does NOT change cell from Alive to Dead
	 *  
	 *  @param row the row where the action happened
	 *  @param col the column where the action happened
	 */
    public void toggleCell(int row, int col) {
        // be sure to do bounds checking on row and col to ensure validity
        // if row or col are invalid values (too small or too large), do not throw
        // any exception, simply do nothing
        // too small -> less than zero
        // too large -> outside the grid (ref the row/col fields of this class)

        // we are only setting cell to alive with this method since GUI has click and drag
        // functionality, and dragging too slow will toggle cell multiple times.

        // you may assume input parameters are valid
        if (row >= 0 && row < grid.size()) {
            DynamicArray<Cell> rowArray = grid.get(row); // Get the row

            // Check if the column is valid within the given row
            if (col >= 0 && col < rowArray.size()) {
                Cell cell = rowArray.get(col); // Get the cell

                // Toggle the cell's alive state
                cell.setAlive(!cell.isAlive()); // Set cell to the opposite of its current state
            }
        }

    }
    /**
	 *  Method to handle the evolution of the grid by ONE generation.
	 */
    public void evolve() {
        // you will need to create a new grid to populate.
        // if you start killing and birthing new cells to the active grid, 
        // then the current and next generation data will "cross-contaminate"
        // and you will get an invalid result.

        // countLiveNeighbors is provided as a helper method, you should use this.

        // Rules:
        // A live cell with fewer than 2 live neighbors dies, as if by underpopulation.
        // A live cell with 2 or 3 live neighbors survives to the next generation.
        // A live cell with more than 3 live neighbors dies, as if by overpopulation.
        // A dead cell with exactly 3 live neighbors becomes a live cell, as if by reproduction.

        // if a cell is on the border or corner of the grid, it will naturally have less total possible neighbors
        // e.g. a corner cell has only 3 total neighbors 

        // don't forget to advance the generation counter.

        // O(row*col) requirement
        DynamicArray<DynamicArray<Cell>> newGrid = new DynamicArray<>();

        // Step 2: Loop through each cell in the current grid
        for (int row = 0; row < grid.size(); row++) {
            DynamicArray<Cell> newRow = new DynamicArray<>(); // New row for the next generation

            for (int col = 0; col < grid.get(row).size(); col++) {
                Cell currentCell = grid.get(row).get(col);  // Get the current cell
                int liveNeighbors = countLiveNeighbors(row, col);  // Count the live neighbors

                // Step 3: Apply the rules of the Game of Life to each cell
                if (currentCell.isAlive()) {
                    // Rule 1: A live cell with fewer than 2 live neighbors dies (underpopulation)
                    // Rule 2: A live cell with 2 or 3 live neighbors survives
                    // Rule 3: A live cell with more than 3 live neighbors dies (overpopulation)
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        newRow.add(new Cell(false));  // Cell dies (becomes dead)
                    } else {
                        newRow.add(new Cell(true));  // Cell survives
                    }
                } else {
                    // Rule 4: A dead cell with exactly 3 live neighbors becomes alive (reproduction)
                    if (liveNeighbors == 3) {
                        newRow.add(new Cell(true));  // Cell becomes alive
                    } else {
                        newRow.add(new Cell(false));  // Cell remains dead
                    }
                }
            }

            // Add the new row to the new grid
            newGrid.add(newRow);
        }

        // Step 4: Update the grid to the new generation
        grid = newGrid;
        generations++;
    }
    /**
	 *  Helper method to count the live neighbors of a specific cell at row/col
     * 
     *  @param row the row of the cell in question
     *  @param col the col of the cell in question
	 */
    public int countLiveNeighbors(int row, int col) {
        // this method will be tested directly, so be sure to implement.

        // remember:
        // Neighbors include the eight surrounding cells:
        //   Directly adjacent cells (north, south, east, west).
        //   Diagonal cells (northeast, northwest, southeast, southwest).

        // you may assume input parameters are valid

        // O(1) requirement
        // this big-O requirement indicates that execution time should always be constant, 
        // not scaling with any factor.
        // e.g. if you iterate through the entire grid, you are not meeting O(1) requirement
        // since the execution time will scale with the size of the grid.
        int LiveNeighbors = 0;

        // Check each possible neighbor position manually
        if (row > 0 && col > 0 && grid.get(row - 1).get(col - 1).isAlive()) LiveNeighbors++; // Top-left
        if (row > 0 && grid.get(row - 1).get(col).isAlive()) LiveNeighbors++;               // Top
        if (row > 0 && col < grid.get(row).size() - 1 && grid.get(row - 1).get(col + 1).isAlive()) LiveNeighbors++; // Top-right
        if (col > 0 && grid.get(row).get(col - 1).isAlive()) LiveNeighbors++;               // Left
        if (col < grid.get(row).size() - 1 && grid.get(row).get(col + 1).isAlive()) LiveNeighbors++; // Right
        if (row < grid.size() - 1 && col > 0 && grid.get(row + 1).get(col - 1).isAlive()) LiveNeighbors++; // Bottom-left
        if (row < grid.size() - 1 && grid.get(row + 1).get(col).isAlive()) LiveNeighbors++; // Bottom
        if (row < grid.size() - 1 && col < grid.get(row).size() - 1 && grid.get(row + 1).get(col + 1).isAlive()) LiveNeighbors++; // Bottom-right

        return LiveNeighbors;
    }
    /**
	 *  Reset all cells in the grid. Generation count should also reset to zero.
	 */
    public void reset() {
        // note that Cell objects have a reset() method
        
        // O(row*col) requirement
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                grid.get(i).get(j).reset();  // Reset each cell
            }
        }
        generations =0;
    }
    /**
	 *  Returns the count of live cells in the grid.
	 */
    public int getAliveCells() {
        // O(row*col) requirement
        int LiveCell=0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j).isAlive()) {
                    LiveCell++;  // Increment count for every alive cell
                }
            }
        }


        return LiveCell;
    }

    /**
	 *  Returns the average age of all ALIVE cells in the grid.
	 */
    public double getAverageAge() {
        // dead cells DO NOT COUNT
        
        // O(row*col) requirement
        int totalAge = 0;   // Sum of ages of all alive cells
        int aliveCellCount = 0;   // Number of alive cells

        // Iterate through each cell in the grid
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                Cell cell = grid.get(i).get(j);
                if (cell.isAlive()) {
                    totalAge += cell.getAge();  // Add cell age to the total
                    aliveCellCount++;  // Count the number of alive cells
                }
            }
        }

        // Avoid division by zero, return 0.0 if no alive cells
        if (aliveCellCount == 0) {
            return 0.0;
        }

        double avgAge = (double) totalAge / aliveCellCount; // Calculate average age
        return avgAge;
    }
    /**
	 *  Returns the maximum age of all ALIVE cells in the grid.
	 */
    public int getMaxAge() {
        // you MAY use Math.max, it is part of java.lang and does not require
        // additional imports.

        // O(row*col) requirement
        int maxAge = 0; // Start with 0 since age can't be negative

        // Iterate through each cell in the grid
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                Cell cell = grid.get(i).get(j);
                if (cell.isAlive()) {
                    maxAge = Math.max(maxAge, cell.getAge());
                }
            }
        }

        return maxAge;
    }
    /**
	 *  Returns the number of generations the simulation has gone through.
	 */
    public int getGenerations() {
        // O(1) requirement
        
        return generations;
    }

    /**
     *  Parses an RLE file into a 2d array of Boolean variables.
     *  This method is key to loading template data from external files.
     *  see <a href="https://conwaylife.com/wiki/Run_Length_Encoded">...</a> for more info on this format.
     */
    public void parseRle(DynamicArray<String> lines) {
        // you MAY use parseInt method.
        // you may assume input parameters are valid
        
        StringBuilder rleData = new StringBuilder();
        int width = 10, height = 10; // default these to 10, update to correct value below based on file

        // Iterate through all lines to parse out RLE header info (width and height)
        // header line is of the form: x = 36, y = 9, rule = B3/S23
        // we want the x and y values, rule part is irrelevant to this project.
        for (String line : lines) {
            if (line.startsWith("x")) { // Header line
                String[] parts = line.split(",");
                for (String part : parts) {
                    part = part.trim();
                    if (part.startsWith("x")) {
                        // your code here
                        // extract and assign width value
                        width = Integer.parseInt(part.split("=")[1].trim());
                    } else if (part.startsWith("y")) {
                        // your code here
                        // extract and assign height value
                        height = Integer.parseInt(part.split("=")[1].trim());
                    }
                }
            } else {
                rleData.append(line);
            }
        }

        // declaring boolean array for the pattern data
        boolean[][] pattern = new boolean[height][width];

        // this string is the remainder of the file data
        String rle = rleData.toString();
        
        // you may find these useful
        int row = 0, col = 0;
        int count;

        // now, write the code to iterate through the rest of the data in rle variable
        // and apply correct boolean state (true=alive, false=dead) to the array elements

        // Apply the pattern to the grid
        StringBuilder numBuffer = new StringBuilder();
        for (char c : rle.toCharArray()) {
            if (Character.isDigit(c)) {
                numBuffer.append(c); // Build up number (if present)
            } else {
                count = !numBuffer.isEmpty() ? Integer.parseInt(numBuffer.toString()) : 1;
                numBuffer.setLength(0); // Clear buffer after use

                if (c == 'b') { // Dead cells
                    col += count;
                } else if (c == 'o') { // Alive cells
                    for (int i = 0; i < count; i++) {
                        if (col < width) {
                            pattern[row][col] = true;
                            col++;
                        }
                    }
                } else if (c == '$') { // New row
                    row++;
                    col = 0;
                }
            }
        }
        applyPatternToGrid(pattern);
    }

    /**
	 *  Translates boolean 2d array into actual cell data in our grid.
	 */
    public void applyPatternToGrid(boolean[][] pattern) {
        // you may assume input parameters are valid
 
        // we won't test any scenario where the pattern is larger than the grid, 
        // but your sim may crash if you try to do that.
        // for best results, make sure your grid size is large enough before loading big patterns.
        
        // grid gets reset first
        reset();

        // startRow and startCol are the offsets into the grid.
        // DO NOT CHANGE THESE
        // this is critical for accurate testing
        int startRow = rows / 2 - pattern.length / 2;
        int startCol = cols / 2 - pattern[0].length / 2;

        // use loops and setAlive method to apply pattern to grid
        // the top left of the pattern should appear at startRow and startCol
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                // Calculate actual row and column in the grid
                int gridRow = startRow + i;
                int gridCol = startCol + j;

                // Ensure we're within the grid bounds
                if (gridRow >= 0 && gridRow < rows && gridCol >= 0 && gridCol < cols) {
                    // If pattern cell is alive (true), set the corresponding grid cell alive
                    if (pattern[i][j]) {
                        grid.get(gridRow).get(gridCol).setAlive(true);
                    }
                }
            }
        }


    }

    /**
     * This is provided as an optional way to load RLE files if you want to do testing in main.
     * This is basically a copy of the loadRleFile method in GameOfLife class, but without any GUI stuff.
     * 
     * @param filename relative path to the file (/sample-rle-files/blah.rle)
     */
    private void loadRleFile(String filename) {
        File file = new File(filename);
        try {
            DynamicArray<String> lines = new DynamicArray<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith("#")) { // Ignore comments
                        lines.add(line);
                    }
                }
                parseRle(lines);
            }
        } catch (IOException e) {
            // do nothing
        }
    }

    /**
     * Gets the number of rows in the simulation grid.
     * 
     * @return the number of rows in the grid
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the number of rows in the simulation grid.
     * @param rows the new number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gets the number of columns in the simulation grid.
     * 
     * @return the number of columns in the grid
     */
    public int getCols() {
        return cols;
    }

    /**
     * Sets the number of columns in the simulation grid.
     * 
     * @param cols the new number of columns
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    public static void main(String[] args) {
        // create a new sim
        Simulation sim = new Simulation(50, 50);

        // make the cell at 1,1 to be alive
        sim.toggleCell(1,1);

        // if the cell at 1,1 is alive, you did good
        if(sim.grid.get(1).get(1).isAlive()) {
            System.out.println("Yay 1");
        }
        // the number of alive cells should be 1
        if(sim.getAliveCells() == 1) {
            System.out.println("Yay 2");
        }

        // progress the sim by one generation
        sim.evolve();

        // the cell at 1,1 should now be dead (starvation rule)
        if(!sim.grid.get(1).get(1).isAlive()) {
            System.out.println("Yay 3"); 
        }
        // the number of alive cells should be zero
        if(sim.getAliveCells() == 0) {
            System.out.println("Yay 4");
        }
        // generation counter should have advanced
        if(sim.getGenerations() == 1) {
            System.out.println("Yay 5");
        }

        // write more Yay tests on your own!
    }
}
