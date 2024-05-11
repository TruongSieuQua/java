package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai4 {

    static class Input{
        long a;
        long b;
        Input(long l1, long l2){
            a=l1;
            b=l2;
        }
    }

    static Input[] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Input[] ins = new Input[n];
        for(int i=0; i<n;i++){
            ins[i] = new Input(sc.nextLong(), sc.nextLong());
        }
        sc.close();
        return ins;
    }

    static long solution(long a, long b, long uc){
        if(a%uc!=0 || b%uc!=0) return solution(a,b,uc-1);
        else {
            return Math.max(uc, 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Input[] ins = input();
        for (Input in : ins) {
            long a = in.a;
            long b = in.b;
            System.out.println(solution(a, b, Math.min(a, b)));
        }
    }
}
