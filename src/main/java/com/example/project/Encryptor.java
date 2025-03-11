package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen == 0) {
            return 1;
        }
        return (int) ((double) messageLen / rows + 0.999);
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] array = new String[rows][determineColumns(message.length(), rows)];
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (idx >= message.length()) {
                    array[i][j] = "=";
                } else {
                    array[i][j] = message.substring(idx, idx + 1);
                }
                idx++;
            }
        }
        return array;
    }

    public static String encryptMessage(String message, int rows){
        String str = "";
        String[][] array = generateEncryptArray(message, rows);
        for (int i = array[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                str += array[j][i];
            }
        }
        return str;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String str = "";
        String[][] array = new String[rows][determineColumns(encryptedMessage.length(), rows)];
        int idx = 0;
        for (int i = array[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (idx >= encryptedMessage.length()) {
                    array[j][i] = "=";
                } else {
                    array[j][i] = encryptedMessage.substring(idx, idx + 1);
                }
                idx++;
            }
        }
        for (String[] row : array) {
            for (String item : row) {
                if (!item.equals("=")) {
                    str += item;
                }
                System.out.print(item + " ");
            }
            System.out.println();
        }
        return str;

        // int cols = determineColumns(encryptedMessage.length(), rows);
        // int idx = encryptedMessage.length()
        // while (true) {
            
        // }
        // String[][] array = generateEncryptArray(encryptedMessage, rows);
        // for (int i = 0; i < array[0].length; i++) {
        //     for (int j = array.length - 1; j >= 0; j--) {
        //         if (!array[j][i].equals("=")) {
        //         str += array[j][i];
        //         }
        //     }
        // }
    }

    public static void main(String[] args) {
        String message = "ABCDEFGHIJK";
        int rows = 3;
        String[][] array = generateEncryptArray(message, rows);
        for (String[] row : array) {
            for (String item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        String encrypted = encryptMessage(message, rows);
        System.out.println(encrypted);
        String[][] array2 = generateEncryptArray(encrypted, rows);
        for (String[] row : array2) {
            for (String item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(decryptMessage(encrypted, rows));
    }
}