package mmud;

import java.math.BigInteger;

public class RSA {

    /*
        Private key: (n, e)
        Public key:  (n, d)
        c = (m^e)mod(n)
        m = (c^d)mod(n)
    */


    private BigInteger n;

    private BigInteger On;

    private BigInteger e;

    private BigInteger d;

    public RSA(long p, long q){
        init(p, q);
        this.e = calculateE(On);
        this.d = caculateD(On);
    }

    public RSA(long p, long q, long e){
        init(p, q);
        if(!isEValid(BigInteger.valueOf(e), this.On)){
            throw new ArithmeticException("e is not valid!");
        }
        this.e = BigInteger.valueOf(e);
        this.d = caculateD(On);
    }

    private void init(long p, long q){
        if(p < 2 || q < 2){
            throw new ArithmeticException("p or q is not prime number.");
        }
        this.n = BigInteger.valueOf(p*q);
        this.On = BigInteger.valueOf((p-1)*(q-1));
    }

    private boolean isEValid(BigInteger e, BigInteger phiN){
        if(e.longValue() < 2){
            throw new ArithmeticException("e is not a prime number!");
        }else if(!phiN.gcd(e).equals(BigInteger.ONE)){
            throw new ArithmeticException("e not satisfy gcd(e, O(n)) = 1");
        }
        return true;
    }

    private BigInteger calculateE(BigInteger phiN){
        BigInteger i;
        for(i= BigInteger.TWO; !phiN.equals(i); i=i.add(BigInteger.ONE)){
            if( phiN.gcd(i).equals(BigInteger.ONE)){
                break;
            }
        }
        if(phiN.equals(i)){
            throw new ArithmeticException("Not exist e so that gcd(e, phiN)=1");
        }
        return i;
    }

    private BigInteger caculateD(BigInteger phiN){
        BigInteger j;
        for(j=BigInteger.TWO; !phiN.equals(j); j=j.add(BigInteger.ONE)){
            BigInteger remainder = (e.multiply(j)).mod(phiN);
            if(remainder.equals(BigInteger.ONE)){
                break;
            }
        }
        if(phiN.equals(j)) {
            throw new ArithmeticException("Not exist d so that (e*d)mod(phiN)=1");
        }
        return j;
    }

    public long encrypt(long m){
        return BigInteger.valueOf(m).modPow(this.e, this.n).longValue();

    }

    public long decrypt(long c){
        return BigInteger.valueOf(c).modPow(this.d, this.n).longValue();
    }

    static long gcd(long a, long b)
    {
        // Lay a la phan du
        if (a == 0)
            return b;

        return gcd(b%a,a);
    }
}