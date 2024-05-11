package recursion;

import java.io.IOException;
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

    static double solution(double x, int n){
        double[] rs = caculateYi(x, n)
    }

    public static void main(String[] args) throws IOException {
        Input[] ins = input();
        for (Input in: ins) {
            System.out.printf("%.8f%n", solution(in.x, in.n));
        }
    }
}
