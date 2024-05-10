package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Bai2 {
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

    static void solution(String s, Map<String, String> rs){
        if(!rs.containsKey(s)){
            char max = (char)s.chars().reduce(0, (t,e) -> {
                return Math.max(e, t);
            });
            rs.put(s, String.valueOf(max));
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Input in = input();
        String[] rs = new String[in.cases.length];
        Map<String, String> cache = new HashMap<>();

        Arrays.stream(in.cases).forEach(s -> solution(s, cache));
        for (int i = 0; i < rs.length; i++) {
            rs[i]=String.format("%s: %s", in.cases[i], cache.get(in.cases[i]));
        }
        System.out.println(String.join("\n", rs));
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));
    }
}
