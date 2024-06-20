package mmud;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        String key = readFileFromResources("key1.txt");
        String p = readFileFromResources("p1.txt");
        String expectedCipher = readFileFromResources("c1.txt");

        byte[] keyBytes = hexStringToByteArray(key);
        byte[] pBytes = hexStringToByteArray(p);

        AES_ aes = new AES_(keyBytes);

        byte[] cBytesResult = aes.ECB_encrypt(pBytes);
        System.out.println(byteArrayToString(cBytesResult));

        byte[] pBytesResult = aes.ECB_decrypt(cBytesResult);
        System.out.println(byteArrayToString(pBytesResult));
//        byte[] cBytes = aes.ECB_encrypt(pBytes);
//        byte[] pBytes1 = aes.ECB_decrypt(cBytes);
//        Files.writeString(Paths.get("./src/main/java/mmud/c_result.txt"), byteArrayToString(cBytes), StandardOpenOption.CREATE);
//        Files.writeString(Paths.get("./src/main/java/mmud/p_result.txt"), byteArrayToString(pBytes1), StandardOpenOption.CREATE);
//        String actualCipher = byteArrayToString(cBytes);
//        if (actualCipher.equals(expectedCipher)) {
//            System.out.println("Encryption result matches with c1.txt");
//        } else {
//            System.out.println("Encryption result does not match with c1.txt");
//        }one
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
}
