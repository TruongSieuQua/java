package recursion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bai11 {

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

    static class Solution{

        private static Map<Integer, Long> xi = new HashMap<>();
        private static Map<Integer, Long> yi = new HashMap<>();

        static long xn(int n){
            if(xi.containsKey(n)){
                return xi.get(n);
            }else{
                long xn;
                if(n==0) {
                    xn=1;
                }else{
                    xn = xn(n-1) + yn(n-1);
                }
                xi.put(n, xn);
                return xn;
            }
        }

        static long yn(int n){
            if(yi.containsKey(n)){
                return yi.get(n);
            }else{
                long yn;
                if(n==0) {
                    yn=0;
                }else{
                    yn = 3*xn(n-1) + yn(n-1);
                }
                yi.put(n, yn);
                return yn;
            }
        }
    }

    public static void main(String[] args) throws IOException {
      int[] ins = input();
        for (int in: ins) {
            System.out.printf("%d %d\n", Solution.xn(in), Solution.yn(in));
        }
    }
}
