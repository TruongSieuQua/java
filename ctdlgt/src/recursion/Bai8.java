package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai8 {

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

    static long solution(long[] ins, int i){
        if(i==0){
            return 0;
        }else{
            return ins[i-1]%2==0
                    ? ins[i-1]+solution(ins, i-1)
                    : solution(ins, i-1);
        }
    }

    public static void main(String[] args) throws IOException {
        long[] ins = input();
        System.out.println(solution(ins, ins.length));
    }
}