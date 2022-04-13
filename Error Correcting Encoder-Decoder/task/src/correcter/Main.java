package correcter;

import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Random;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String... args) {

        Correcter msg = new Correcter();
        String fileNameIn = "send.txt";
        String fileNameOut = "received.txt";

        msg.setBytes(fileNameIn);
        msg.corruptBytes();
        msg.writeBytes(fileNameOut);

    }
}

class Correcter {

    private static byte[] bytes;

    void setBytes(String fileNameIn) {
        try (InputStream inputStream = new FileInputStream(fileNameIn)) {
            bytes = inputStream.readAllBytes();
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found!");
        } catch (IOException ioe) {
            System.out.println(ioe.getCause());
        }
    }

    void corruptBytes() {
        Random generate = new Random();

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= 1 << generate.nextInt(8);
        }
    }

    void writeBytes(String out) {
        try (OutputStream outputStream = new FileOutputStream(out, false)) {
            outputStream.write(bytes);
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found!");
        } catch (IOException ioe) {
            System.out.println(ioe.getCause());
        }
    }
}