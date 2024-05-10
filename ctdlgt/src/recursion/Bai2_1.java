package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bai2_1 {
    static class Input{
        String[] cases;
        Input(){}
    }

    static Input input() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Input in = new Input();
        int n = Integer.parseInt(r.readLine());
        in.cases = new String[n];
        for(int i=0; i<n;i++){
            in.cases[i]=r.readLine();
        }
        return in;
    }

    static char largestChar(String s, int i){
        if(i > 0) {
            char max = largestChar(s, i-1);
            return s.charAt(i-1) > max ?s.charAt(i-1) :max;
        }else{
            return s.charAt(0);
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Input in = input();
        int n = in.cases.length;
        String[] rs = new String[n];
        for (int i = 0; i < n; i++) {
            String s = in.cases[i];
            rs[i]=String.format("%s: %c", s, largestChar(s, s.length()));
        }
        System.out.println(String.join("\n", rs));
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime-startTime));
    }
}
