package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bai3 {
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


}
