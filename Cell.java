// TO DO: add your implementation and JavaDocs.

public class Cell {
    private boolean alive;
    private  int age;

    // You will need to add at least TWO private instance variables to support the methods below.
    // the types of those variables should be inferred from the methods

    public Cell(boolean alive) {
        // initialize instance variables
        // when a cell is created with alive=true, it's age is immediately 1
        this.alive = alive;
        if (alive)
        {
            this.age =1;
        } else {
            this.age =0;
        }

    }

    public boolean isAlive() {
        // Return whether the cell is alive
        // O(1)
        
        return alive;
    }

    public void setAlive(boolean alive) {
        // Set the cell state to Alive
        // Reset age to 1 when a cell becomes alive
        // if cell is already alive, do NOT reset age
        // O(1)

        if(age==0)
        {
            this.age =1;
        }
        this.alive = true;
    }

    public int getAge() {
        // Return the age of the cell
        // O(1)
        
        return age;
    }

    public void setAge(int age) {
        // set the age of the cell
        // O(1)

        // age property of a Cell object must be >= 0.
        // if method parameter is negative, do nothing
        if( age>=0)
        {
            this.age= age;
        }

    }
    public void reset() {
        // set alive to false and age to 0
        // O(1)
        this.alive = false;
        this.age =0;

    }
}
