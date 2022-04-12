package correcter;

import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] input = scanner.nextLine().toCharArray();
        final ValueRange[] ACCEPTABLE_SYMBOL_RANGES = {
                ValueRange.of('a', 'z'),
                ValueRange.of('A', 'Z'),
                ValueRange.of('0', '9'),
                ValueRange.of(' ', ' ')
        };
        String symbolRange = Arrays.toString(ACCEPTABLE_SYMBOL_RANGES);
        System.out.println(String.valueOf(input));
        char[] tripledInput = tripleInput(input);
        char[] errors = createErrors(tripledInput, symbolRange);
        System.out.println(deleteErrors(errors));

    }

    public static char[] tripleInput(char[] input) {
        char[] tripledInput = new char[input.length * 3];
        for (int i = 0; i < input.length; i++) {
            int repeatChar = 0;
            while (repeatChar < 3) {
                tripledInput[i * 3 + repeatChar] = input[i];
                repeatChar++;
            }
        }
        System.out.println(String.valueOf(tripledInput));
        return tripledInput;
    }

    public static char[] createErrors(char[] tripledInput, String symbolRange) {
        Random random = new Random();
        int errorIndex;
        for (int i = 0; i < tripledInput.length; i += 3) {
            errorIndex = i + random.nextInt(3);
            if (errorIndex < tripledInput.length) {
                char newChar = symbolRange.charAt(random.nextInt(symbolRange.length()));
                tripledInput[errorIndex] = tripledInput[errorIndex] == newChar ? (char) (newChar - 1) : newChar;
            }
        }
        System.out.println(String.valueOf(tripledInput));
        return tripledInput;
    }

    public static String deleteErrors(char[] errors) {
        char[] corrected = new char[errors.length / 3];
        for (int i = 0, j = 0; i < errors.length; i += 3) {
            if (errors[i] == errors[i + 1] || errors[i] == errors[i + 2]) {
                corrected[j] = errors[i];
            } else {
                corrected[j] = errors[i + 1];
            }
            j++;
        }
        return String.valueOf(corrected);
    }
}
