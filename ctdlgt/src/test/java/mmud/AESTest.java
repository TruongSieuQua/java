package mmud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static mmud.Utils.readFileFromResources;
import static mmud.Utils.hexStringToByteArray;
import static mmud.Utils.byteArrayToString;

public class AESTest {
    @ParameterizedTest
    @CsvSource({
            "2b7e151628aed2a6abf7158809cf4f3c, 6bc1bee22e409f96e93d7e117393172a, 3ad77bb40d7a3660a89ecaf32466ef97"
    })
    void ECB_Encryption_Test(String key, String p, String c){
        byte[] keyBytes = hexStringToByteArray(key);
        byte[] pBytes = hexStringToByteArray(p);

        System.out.println("m = " +  byteArrayToString(pBytes));
        AES cipher = new AES(keyBytes);
        byte[] cBytes = cipher.ECB_encrypt(pBytes);

        System.out.println("c = " + byteArrayToString(cBytes));
        Assertions.assertEquals(c, byteArrayToString(cBytes));
    }

    @ParameterizedTest
    @CsvSource({
            "2b7e151628aed2a6abf7158809cf4f3c, 3ad77bb40d7a3660a89ecaf32466ef97, 6bc1bee22e409f96e93d7e117393172a"
    })
    void ECB_Decryption_Test(String key, String c, String p) throws IOException {
        byte[] keyBytes = hexStringToByteArray(key);
        byte[] cBytes = hexStringToByteArray(c);

        AES cipher = new AES(keyBytes);
        byte[] pBytes = cipher.ECB_decrypt(cBytes);
        Assertions.assertEquals(p, byteArrayToString(pBytes));
    }
}
