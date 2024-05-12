package recursion;

import java.io.IOException;
import java.util.Scanner;

public class Bai12 {

    static float[][] input() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        float[][] ins = new float[n][];
        for(int i=0; i<n;i++){
            int len = sc.nextInt();
            ins[i] = new float[len];
            for (int j=0; j < len;j++) {
                ins[i][j]= sc.nextFloat();
            }
        }
        sc.close();
        return ins;
    }

    static boolean solution(final float[] in, int len){
        if(len == 0){
            return true;
        }
        return !(in[len-1] >= 0) && solution(in, len - 1);
    }

    public static void main(String[] args) throws IOException {
        float[][] ins = input();
        for (float[] in: ins) {
            if(solution(in, in.length)){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }
}
