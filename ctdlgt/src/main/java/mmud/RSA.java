package mmud;

import java.math.BigInteger;

public class RSA {

    /*
        Private key: (n, e)
        Public key:  (n, d)
        c = (m^e)mod(n)
        m = (c^d)mod(n)
    */


    private long n;

    private long On;

    private long e;

    private long d;

    public RSA(long p, long q){
        init(p, q);
        this.e = calculateE(On);
        this.d = caculateD(On);
    }

    public RSA(long p, long q, long e){
        init(p, q);
        if(!isEValid(e, this.On)){
            throw new ArithmeticException("e is not valid!");
        }
        this.e = e;
        this.d = caculateD(On);
    }

    private void init(long p, long q){
        if(p <= 0 || q <= 0 || p > Long.MAX_VALUE/q){
            throw new ArithmeticException("Invalid p and q or overflow occurred");
        }
        this.n = p*q;
        this.On = (p-1)*(q-1);
    }

    private boolean isEValid(long e, long phiN){
        if( e < 2){
          throw new ArithmeticException("e is lower than 2");
        } else if(e > Integer.MAX_VALUE){
            throw new ArithmeticException("e is larger than Interger.MAX_VALUE");
        }else if(gcd(e, phiN)!=1){
            throw new ArithmeticException("e not satisfy gcd(e, O(n)) = 1");

        }
        return true;
    }

    private long calculateE(long phiN){
        long i;
        for(i=2; i < phiN; i++){
            if(gcd(i, phiN)==1){
                break;
            }
        }
        if(i == phiN || i > Integer.MAX_VALUE){
            throw new ArithmeticException("Not exist e so that gcd(e, phiN)=1");
        }
       return i;
    }

    private long caculateD(long phiN){
        long j;
        for(j=2; j<phiN; j++){

            BigInteger e_times_d = BigInteger.valueOf(e*j);
            BigInteger remainder = e_times_d.mod(BigInteger.valueOf(phiN));

            if(remainder.equals(BigInteger.ONE)){
                break;
            }
        }
        if(j == phiN || j > Integer.MAX_VALUE) {
            throw new ArithmeticException("Not exist d so that (e*d)mod(phiN)=1");
        }
        return j;
    }

    public long encrypt(long m){
        return BigInteger.valueOf(m).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n)).longValue();

    }

    public long decrypt(long c){
        return BigInteger.valueOf(c).modPow(BigInteger.valueOf(d), BigInteger.valueOf(n)).longValue();
    }

//    static long gcd1(long a, long b)
//    {
//        // Lay b lam phan du
//        if (b == 0)
//            return a;
//
//        return gcd(b,a%b);
//    }

    static long gcd(long a, long b)
    {
        // Lay a la phan du
        if (a == 0)
            return b;

        return gcd(b%a,a);
    }
}
