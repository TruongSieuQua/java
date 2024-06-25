package mmud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RSATest {

    @ParameterizedTest
    @CsvSource({
            "11, 13, 17, 88, 121"
    })
    public void Rsa_EncryptTests(long p, long q, long e, long m, long expectedResult){
        RSA rsa = new RSA(p, q, e);
        long c = rsa.encrypt(m);
        Assertions.assertEquals(c, expectedResult);
    }

}
