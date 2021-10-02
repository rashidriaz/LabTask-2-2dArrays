package twodarrays.lt.dsa;

import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class LabTask2 {
    /*
     * Declaring my 2D array as the members of the class because
     * it would be used by more than 80 percent of the methods in
     * this class and this class - "LabTask2" is all about this array
     * and according to the OOP principles, it justifies the definition
     * of this array in the class rather than in the main method.
     * */
    private static int[][] integerArray;

    public static void main(String[] args) {
        System.out.println("----------------------\tWelcome\t----------------------\n\n");
        initializeTheArray();
        insertIntoIntegerArray();
        print2DArray();
        System.out.println("\n----------------------------------------------------------------------\n");
        evaluateAndPrintSumsOfValuesInEachRow();
        System.out.println("\n----------------------------------------------------------------------\n");
        evaluateAndPrintGreaterValueFromEveryRowAndColumn();
    }

    private static void initializeTheArray() {
        Dimensions dimensions = getDimensions();
        integerArray = new int[dimensions.getRows()][dimensions.getColumns()];
    }

    private static void insertIntoIntegerArray() {
        travers2DArray(dimensions -> {
            Random random = new Random();
            integerArray[dimensions.getRows()][dimensions.getColumns()] = random.nextInt(100);
        });
    }

    private static void print2DArray() {
        System.out.println("\n\n-----------------\tOur Array is:\t-----------------");
        var prevRow = new Object() {
            int value = 0;
        };
        travers2DArray(dimensions -> {
            if (prevRow.value != dimensions.getRows()) {
                System.out.println();
                prevRow.value = dimensions.getRows();
            }
            System.out.print(integerArray[dimensions.getRows()][dimensions.getColumns()] + "\t");
        });
    }

    private static void evaluateAndPrintSumsOfValuesInEachRow() {
        System.out.println("\n\n-----------------\tFollowing are the sums of values from each row:\t-----------------");
        var values = new Object() {
            int row = 0;
            int sum = 0;
        };
        travers2DArray(dimensions -> {
            if (values.row != dimensions.getRows()) {
                System.out.println("Sum of values in Row: " + values.row + " is: " + values.sum);
                values.sum = 0;
                values.row = dimensions.getRows();
            }
            values.sum += integerArray[dimensions.getRows()][dimensions.getColumns()];
        });
        System.out.println("Sum of values in Row: " + values.row + " is: " + values.sum);
    }

    private static void evaluateAndPrintGreaterValueFromEveryRowAndColumn() {
        int[] greaterValuesFromRows = new int[integerArray.length];
        int[] greaterValuesFromColumns = new int[integerArray[0].length];
        evaluateGreaterValueFromEveryRowAndColumn(greaterValuesFromRows, greaterValuesFromColumns);
        printGreaterValueFromEveryRowAndColumn(greaterValuesFromRows, greaterValuesFromColumns);
    }

    private static void evaluateGreaterValueFromEveryRowAndColumn(int[] rowValues, int[] columnValues) {
        travers2DArray(dimensions -> {
            if (rowValues[dimensions.getRows()] < integerArray[dimensions.getRows()][dimensions.getColumns()]) {
                rowValues[dimensions.getRows()] = integerArray[dimensions.getRows()][dimensions.getColumns()];
            }
            if (columnValues[dimensions.getColumns()] < integerArray[dimensions.getRows()][dimensions.getColumns()]) {
                columnValues[dimensions.getColumns()] = integerArray[dimensions.getRows()][dimensions.getColumns()];
            }
        });
    }

    private static void printGreaterValueFromEveryRowAndColumn(int[] rowValue, int[] columnValue) {
        traverse1DArray(integer ->
                        System.out.println("Largest Value from row " + integer + " is: " + rowValue[integer]),
                rowValue.length);
        System.out.println("\n----------------------------------------------------------------------\n");
        traverse1DArray(integer ->
                        System.out.println("Largest Value from column " + integer + " is: " + columnValue[integer]),
                columnValue.length);
    }

    private static void travers2DArray(Consumer<Dimensions> function) {
        for (int row = 0; row < integerArray.length; row++) {
            for (int column = 0; column < integerArray[row].length; column++) {
                function.accept(new Dimensions(row, column));
            }
        }
    }

    private static void traverse1DArray(Consumer<Integer> function, int length) {
        for (int i = 0; i < length; i++) {
            function.accept(i);
        }
    }

    private static Dimensions getDimensions() {
        System.out.println("\n\n-----------------------\tPlease Enter Dimensions for the array\t-----------------------");
        return new Dimensions(getSingleDimension("rows"), getSingleDimension("columns"));
    }

    private static int getSingleDimension(String dimensionType) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter " + dimensionType + ":\t");
        int dimension = input.nextInt();
        if (!isDimensionValid(dimension)) {
            System.out.println("Error! you have entered invalid dimension for the array. please try again!");
            return getSingleDimension(dimensionType);
        }
        return dimension;
    }

    private static boolean isDimensionValid(int dimension) {
        return dimension > 0;
    }
}
