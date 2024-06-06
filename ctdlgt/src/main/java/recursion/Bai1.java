package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bai1 {
    static int input() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        r.close();
        return n;
    }

    static int factorial(int i, String[] rs){
        int kq;

        if(i > 0) kq = i*factorial(i-1,rs);
        else kq = 1;

        rs[i] = String.format("%d! = %d", i, kq);
        return kq;
    }


    public static void main(String[] args) throws IOException {
        int n = input();
        String[] rs = new String[n+1];
        factorial(n, rs);
        System.out.println(String.join("\n", rs));
    }
}
