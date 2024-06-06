package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai13 {

    static int[] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ins = new int[n];
        for(int i=0; i<n;i++){
            ins[i] = sc.nextInt();
        }
        sc.close();
        return ins;
    }

    static double solution(double n){
        if(n==0) return 0;
        else{
            return n/(n+1)+solution(n-1);
        }
    }

    public static void main(String[] args) throws IOException {
        int[] ins = input();
        for (int in: ins) {
            System.out.printf("%.10f\n", solution(in));
        }
    }

}
