package twodarrays.lt.dsa;
/*
* The reason to create this class is to properly implement the
* principles of writing the clean code and "the clean architecture"
* The principles of clean code and clean architecture clearly dictates
* to create a new class for every group of values which are related to
* each other in any way. It helps us to organize things a lot easily and
* makes passing arguments to the methods and returning from them a lot
* easier.
* Since the row and column values are related to each other, so creating
* a new class "Dimensions" was an option.
* */
public class Dimensions {
    private final int ROWS;
    private final int COLUMNS;

    public Dimensions(int ROWS, int columns) {
        this.ROWS = ROWS;
        this.COLUMNS = columns;
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }
}
