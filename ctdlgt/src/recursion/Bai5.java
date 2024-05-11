package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai5 {

    static long[] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] ins = new long[n];
        for(int i=0; i<n;i++){
            ins[i] = sc.nextLong();
        }
        sc.close();
        return ins;
    }



    static String solution(long a){
        if(a == 1){
            return "1";
        }
        return solution(a/2).concat(String.valueOf(a%2));
    }

    public static void main(String[] args) throws IOException {
        long[] ins = input();
        StringBuilder sb = new StringBuilder();
        for(long i: ins){
            System.out.println(solution(i));
        }
    }
}
