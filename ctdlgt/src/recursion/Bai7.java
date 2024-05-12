package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai7 {
    static long[] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] ins = new long[n];
        for(int i=0; i<n;i++){
            ins[i]=sc.nextLong();
        }
        sc.close();
        return ins;
    }

    static double recursion(long i, long n){
        if(i < 0){
            return 0;
        }else{
            return Math.sqrt(n-i + recursion(i-1, n));
        }
    }

    static double solution( long n){
        return recursion(n-1, n);
    }

    public static void main(String[] args) throws IOException {
        long[] ins = input();
        for (long n : ins) {
            System.out.printf("%.10f%n", solution(n));
        }
    }
}
