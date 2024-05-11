package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai9 {
    static float[] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        float[] ins = new float[n];
        for(int i=0; i<n;i++){
            ins[i] = sc.nextFloat();
        }
        sc.close();
        return ins;
    }

    static int solution(float[] ins, int i){
        if(i==0){
            return 0;
        }else{
            return ins[i-1] > 0
                    ? solution(ins, i-1) + 1
                    : solution(ins, i-1);
        }
    }

    public static void main(String[] args) throws IOException {
        float[] ins = input();
        System.out.println(solution(ins, ins.length));
    }
}
