package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Bai3 {
    static class Input{
        String[] cases;
        Input(){}
    }
    static class KetQua{
        char c;
        int freq;
        KetQua(char c, int freq){
            this.c=c;
            this.freq=freq;
        }
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

    static KetQua solution(String s, int i){
        if(i > 0){
            KetQua kq = solution(s, i-1);
          if(s.charAt(i-1) > kq.c){
              kq.c = s.charAt(i-1);
              kq.freq = 1;
          }else if(s.charAt(i-1) == kq.c){
              kq.freq+=1;
          }
          return kq;
        }else{
            return new KetQua(s.charAt(0), 1);
        }
    }
    public static void main(String[] args) throws IOException{
        Input in= input();
        int longestStrLen = Stream.of(in.cases).map(String::length).max(Integer::compareTo).get();
        int n = in.cases.length;
        String[] rs = new String[n];

        for (int i = 0; i < n; i++) {
            rs[i] = String.format("%"+ longestStrLen +"s: %d",
                    in.cases[i],
                    (solution(in.cases[i], in.cases[i].length())).freq
            );
        }
        System.out.println(String.join("\n", rs));
    }
}
