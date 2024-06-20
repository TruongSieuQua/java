package mmud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static mmud.Main.readFileFromResources;
import static mmud.Main.hexStringToByteArray;
import static mmud.Main.byteArrayToString;

public class AESTest {
    static class TestCase {
        String keyFile;
        String plaintextFile;
        String ciphertextFile;

        TestCase(String keyFile, String plaintextFile, String ciphertextFile) {
            this.keyFile = keyFile;
            this.plaintextFile = plaintextFile;
            this.ciphertextFile = ciphertextFile;
        }
    }
    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("key1.txt", "p1.txt", "c1.txt"),
                new TestCase("key2.txt", "p2.txt", "c2.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void ECB_Encryption_Test(TestCase testCase) throws IOException {
        String key = readFileFromResources(testCase.keyFile);
        String p = readFileFromResources(testCase.plaintextFile);
        String c = readFileFromResources(testCase.ciphertextFile);

        byte[] keyBytes = hexStringToByteArray(key);
        byte[] pBytes = hexStringToByteArray(p);

        System.out.println(byteArrayToString(pBytes));
        AES_ cipher = new AES_(keyBytes);
        byte[] cBytes = cipher.ECB_encrypt(pBytes);

        System.out.println(byteArrayToString(cBytes));
        Assertions.assertEquals(c, byteArrayToString(cBytes));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void ECB_Decryption_Test(TestCase testCase) throws IOException {
        String key = readFileFromResources(testCase.keyFile);
        String p = readFileFromResources(testCase.plaintextFile);
        String c = readFileFromResources(testCase.ciphertextFile);

        byte[] keyBytes = hexStringToByteArray(key);
        byte[] cBytes = hexStringToByteArray(c);

        AES_ cipher = new AES_(keyBytes);
        byte[] pBytes = cipher.ECB_decrypt(cBytes);
        Assertions.assertEquals(p, byteArrayToString(pBytes));
    }
}
