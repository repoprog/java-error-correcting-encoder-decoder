package correcter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] input = scanner.nextLine().toCharArray();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String full = alphabet + alphabet.toLowerCase() + " 123456789";
        System.out.println(createErrors(input, full));
    }

    public static String createErrors(char[] input, String full) {
        Random random = new Random();
        int errorIndex;
        for (int i = 0; i < input.length; i += 3) {
            errorIndex = i + random.nextInt(3);
            if (errorIndex < input.length) {
                char newChar = full.charAt(random.nextInt(full.length()));
                input[errorIndex] = input[errorIndex] == newChar ? (char) (newChar - 1) : newChar;
            }
        }
        return String.copyValueOf(input);
    }
}