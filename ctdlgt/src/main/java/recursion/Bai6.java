package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai6 {
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

    static double solution(long n){
        if(n==1){
            return 1;
        }else{
            return Math.sqrt(n + solution(n-1));
        }
    }

    public static void main(String[] args) throws IOException {
        long[] ins = input();
        for (long i : ins) {
            System.out.printf("%.10f%n", solution(i));
        }
    }
}
