import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CSI2300HW5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a number for square matrices or 2 file names to be converted to matrices");
        
        int i;
        int j;
        int[][] matrix1;
        int[][] matrix2;
        String input = scanner.next();
        int size;

        if (Character.isDigit(input.charAt(0))) {
            size = Integer.parseInt(input);
            matrix1 = new int[size][size];
            matrix2 = new int[size][size];
            
            for (i = 0; i < size; ++i) {
                for (j = 0; j < size; ++j) {
                    matrix1[i][j] = (int)(Math.random() * 10);
                    matrix2[i][j] = (int)(Math.random() * 10);
                }
            }

            writeMatrixToFile(matrix1, "matrix1.txt");
            writeMatrixToFile(matrix2, "matrix2.txt");

            multiplyMatrices(matrix1, matrix2);
        } else {
            try {
                String fileName1 = input;
                String fileName2 = scanner.next();
                int [][] fileMatrix1 = readMatrixFromFile(fileName1);
                int [][] fileMatrix2 = readMatrixFromFile(fileName2);

                multiplyMatrices(fileMatrix1, fileMatrix2);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            
        }
        scanner.close();
    }
    
    private static void multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        int[][]matrix3 = new int[rows1][cols2];
        
        for (int i = 0; i < rows1; ++i) {
            for (int j = 0; j < cols2; ++j) {
                for (int k = 0; k < cols1; ++k) {
                    matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        writeMatrixToFile(matrix3, "matrix3.txt");
    }
    
    public static int[][] readMatrixFromFile(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);

        int [][] fileMatrix;
        int rows = 0;
        int cols = 0;
        
        
        while (fileScanner.hasNextLine()) {
            rows++;
        }
        while (fileScanner.hasNextInt()) {
            cols++;
        }
        //remove before submission
        System.out.println("Rows: " + rows + " Cols: " + cols);

        fileMatrix = new int[rows][cols];
        for (int i = 0; fileScanner.hasNextLine(); ++i) {
            for (int j = 0; fileScanner.hasNextInt(); ++j) {
                fileMatrix[i][j] = fileScanner.nextInt();
            }
        }
        fileScanner.close();
        
        return fileMatrix;
    }
    
    public static void writeMatrixToFile(int[][] matrix, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (int[] row : matrix) {
                for (int num : row) {
                    myWriter.write(num + " ");
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}