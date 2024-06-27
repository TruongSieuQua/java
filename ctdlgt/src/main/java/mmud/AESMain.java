package mmud;

import static mmud.Utils.byteArrayToString;
import static mmud.Utils.hexStringToByteArray;

public class AESMain {

    public static void main(String[] args) {
        String key = "2b7e151628aed2a6abf7158809cf4f3c";
        String p = "6bc1bee22e409f96e93d7e117393172a";
        String c = "3ad77bb40d7a3660a89ecaf32466ef97";

        byte[] keyBytes = hexStringToByteArray(key);
        byte[] pBytes = hexStringToByteArray(p);

        AES aes = new AES(keyBytes);
        byte[] cBytes = aes.ECB_encrypt(pBytes);
        System.out.println("c = " + byteArrayToString(cBytes));

        byte[] pBytesDecrypt = aes.decrypt(cBytes);
        System.out.println("p = " + byteArrayToString(pBytesDecrypt));
    }

}
