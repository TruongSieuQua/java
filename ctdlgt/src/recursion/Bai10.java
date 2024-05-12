package recursion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bai10 {

    static class Input{
        double x;
        int n;
        Input(double a, int b){
            x=a;
            n=b;
        }
    }

    static Input[] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Input[] ins = new Input[n];
        for(int i=0; i<n;i++){
            ins[i] = new Input(sc.nextDouble(), sc.nextInt());
        }
        sc.close();
        return ins;
    }

    static class Solution{
        // cache yi = x^i/i!
        private static Map<Double, Double> cache = new HashMap<>();

        static double caculateYi(double x, int i){
            if(cache.containsKey(i)){
                return cache.get(i);
            }
            double yi;
            if(i <= 1){
                yi = x;
            }else{
                yi=(x/i)*caculateYi(x, i-1);
            }
            cache.put(x, yi);
            return yi;
        }

        static double solve(double x, int i){
            if(i == 0) return 0;
            else{
                return caculateYi(x, i) + solve(x, i-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Input[] ins = input();
        for (Input in: ins) {
            System.out.printf("%.8f%n", Solution.solve(in.x, in.n));
        }
    }
}
