package CodeForTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileForTest {

    /**
     * Main method
     */
    public static void main(String[] args) {
        int firstNumber = 5;
        int secondNumber = 2;
        int maxNumber = max(firstNumber, secondNumber);
        System.out.println("The maximum between " + firstNumber
                + " and " + secondNumber + " is " + maxNumber);
    }

    /**
     * Return the max between two numbers
     */
    public static int max(int num1, int num2) {
        int result;
        if (num1 > num2) {
            result = num1;
        } else {
            result = num2;
        }

        return result;
    }
}