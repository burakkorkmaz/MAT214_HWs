package tr.edu.gtu.bkorkmaz.Q1;

import java.util.Arrays;

/**
 * Created by Burak Kağan Korkmaz on 15.05.2017.
 */
public class Neighbour {
    private double[][] matrix = new double[DEFAULT_SIZE][DEFAULT_SIZE];

    private double[][] extendedMatrix;

    protected final static int DEFAULT_SIZE = 10;

    public void addElement(double element, int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex > DEFAULT_SIZE - 1) {
            throw new ArrayIndexOutOfBoundsException(rowIndex);
        }
        if (columnIndex < 0 || columnIndex > DEFAULT_SIZE - 1) {
            throw new ArrayIndexOutOfBoundsException(columnIndex);
        }
        if (element < 0) {
            System.err.println("Element should not be negative!");
            throw new IllegalArgumentException();
        }

        matrix[rowIndex][columnIndex] = element;

    }

    public void addElement(double element, int rowIndex, int columnIndex, int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }
        if (rowIndex < 0 || rowIndex > size - 1) {
            throw new ArrayIndexOutOfBoundsException(rowIndex);
        }
        if (columnIndex < 0 || columnIndex > size - 1) {
            throw new ArrayIndexOutOfBoundsException(columnIndex);
        }
        if (element < 0) {
            System.err.println("Element should not be negative!");
            throw new IllegalArgumentException();
        }

        matrix[rowIndex][columnIndex] = element;

    }

    public double[][] interpolate(int zoomFactor) {
        int size = DEFAULT_SIZE * zoomFactor;
        extendedMatrix = new double[size][size];

        for (int i = 0; i < DEFAULT_SIZE; i++) {
            for (int j = 0; j < DEFAULT_SIZE; j++) {
                double element = matrix[i][j];
                int k = zoomFactor * i;
                for (int m = 0; m < DEFAULT_SIZE && k < size; m++) {
                     int l = zoomFactor * j;
                    for (int n = 0; n < DEFAULT_SIZE && l < size; n++) {
                        extendedMatrix[k][l] = element;
                        ++l;
                    }
                    ++k;
                }
            }
        }
        return extendedMatrix;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Original Matrix\n");
        for (double [] x :
                matrix) {
            sb.append(Arrays.toString(x));
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("Extended Matrix\n");
        for (double [] x :
                extendedMatrix) {
            sb.append(Arrays.toString(x));
            sb.append("\n");
        }
        return sb.toString();
    }
}
