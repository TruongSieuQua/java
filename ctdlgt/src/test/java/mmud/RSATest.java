package mmud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RSATest {

    @ParameterizedTest
    @CsvSource({
            "11, 13, 17, 88, 121",
            "47, 61, 7, 1511, 291",
            "47, 61, 7, 715, 92"
    })
    public void Rsa_EncryptTests(long p, long q, long e, long m, long expectedResult){
        RSA rsa = new RSA(p, q, e);
        long c = rsa.encrypt(m);
        Assertions.assertEquals(c, expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "11, 13, 17, 121, 88",
            "47, 61, 7, 291, 1511",
            "47, 61, 7, 92, 715"
    })
    public void Rsa_DecryptTests(long p, long q, long e, long c, long expectedResult){
        RSA rsa = new RSA(p, q, e);
        long m = rsa.decrypt(c);
        Assertions.assertEquals(m, expectedResult);
    }

}
