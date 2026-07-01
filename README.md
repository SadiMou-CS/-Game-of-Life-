# -Game-of-Life-
The Game of Life Project is an interactive and dynamic simulation of Conway's Game of Life, a popular example of cellular  automata that models the evolution of a grid of cells based on simple rules
implemented and extended 
the classic Game of Life in Java. Also developed `DynamicArray` class as a custom implementation to 
replace the `ArrayList` class, gaining critical experience with the nuts and bolts of the data structure. The final application 
will allow users to customize the grid size, load patterns in RLE format, and control the simulation through interface 
controls. By integrating additional features such as statistics tracking, preset grid sizes, and pattern loading, this project 
serves as a practical exercise in software design, algorithm development, and file compression. 
How the Game of Life Works 
The Game of Life, devised by British mathematician John Horton Conway in 1970, is a cellular automaton that simulates 
the evolution of a grid of cells based on a set of simple rules. Each cell in the grid can be either alive or dead and interacts 
with its neighboring cells to determine its state in the next generation. The game progresses in discrete time steps, known as 
generations, with the state of the grid evolving based on predefined rules.


Rules of the Game of Life 
1. Underpopulation: 
o A live cell with fewer than 2 live neighbors dies, as if by underpopulation. 
2. Survival: 
o A live cell with 2 or 3 live neighbors survives to the next generation. 
3. Overpopulation: 
o A live cell with more than 3 live neighbors dies, as if by overpopulation. 
4. Reproduction: 
o A dead cell with exactly 3 live neighbors becomes a live cell, as if by reproduction. 
Neighbor Definition 
Neighbors include the eight surrounding cells: 
• Directly adjacent cells (north, south, east, west). 
• Diagonal cells (northeast, northwest, southeast, southwest).
