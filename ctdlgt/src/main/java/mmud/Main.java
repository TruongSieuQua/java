package mmud;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Main {

//    public static void main(String[] args) {
//        String key = "2b7e151628aed2a6abf7158809cf4f3c";
//        String p = "6bc1bee22e409f96e93d7e117393172a";
//        String c = "3ad77bb40d7a3660a89ecaf32466ef97";
//
//        byte[] keyBytes = hexStringToByteArray(key);
//        byte[] pBytes = hexStringToByteArray(p);
//
//        AES aes = new AES(keyBytes);
//        byte[] cBytes = aes.ECB_encrypt(pBytes);
//
//        byte[] pBytes1 = aes.ECB_decrypt(cBytes);
//
//        System.out.println("cipher = " + byteArrayToString(pBytes1));
//        System.out.println("actual = " + p);
//    }

    public static void main(String[] args) throws IOException {
        String key = readFileFromResources("key.txt");
        String p = readFileFromResources("p.txt");
        String expectedCipher = readFileFromResources("c.txt");;

        byte[] keyBytes = hexStringToByteArray(key);
        byte[] pBytes = hexStringToByteArray(p);

        AES aes = new AES(keyBytes);
        byte[] cBytes = aes.ECB_encrypt(pBytes);
        byte[] pBytes1 = aes.ECB_decrypt(cBytes);

        Files.writeString(Paths.get("./src/main/java/mmud/c_result.txt"), byteArrayToString(cBytes), StandardOpenOption.CREATE);
        Files.writeString(Paths.get("./src/main/java/mmud/p_result.txt"), byteArrayToString(pBytes1), StandardOpenOption.CREATE);
//        String actualCipher = byteArrayToString(cBytes);
//        if (actualCipher.equals(expectedCipher)) {
//            System.out.println("Encryption result matches with c.txt");
//        } else {
//            System.out.println("Encryption result does not match with c.txt");
//        }
    }

    // Hàm đọc file từ thư mục resources
    public static String readFileFromResources(String fileName) throws IOException {
        ClassLoader classLoader = AES.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
        }
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }

        return data;
    }

    public static String byteArrayToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b&0xFF));
        }
        return sb.toString();
    }

    public static String byteArrayToString1(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%d ", b&0xFF));
        }
        return sb.toString();
    }

}
