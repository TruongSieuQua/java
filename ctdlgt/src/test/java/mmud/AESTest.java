package mmud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AESTest {
    @Test
    void givenString_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        byte[] key = "2b7e151628aed2a6abf7158809cf4f3c".getBytes();
        byte[] p = fillBlock("6bc1bee22e409f96e93d7e117393172a").getBytes();
        byte[] c = "3ad77bb40d7a3660a89ecaf32466ef97".getBytes();

        AES cipher = new AES(key);
        var r = cipher.ECB_encrypt(p);
        System.out.println("c = " + Arrays.toString(c));
        System.out.println("r = " + Arrays.toString(r));
        Assertions.assertArrayEquals(c, r);
    }

    private static String fillBlock(String text) {
        int spaceNum = text.getBytes().length%16==0?0:16-text.getBytes().length%16;
        for (int i = 0; i<spaceNum; i++) text += " ";
        return text;
    }
}
