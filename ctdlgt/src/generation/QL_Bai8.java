package generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QL_Bai8 {

    static class Range{
        boolean[] row;
        boolean[] col;
        boolean[] crs1;
        boolean[] crs2;

        Range(int n){
            row=new boolean[n];
            col=new boolean[n];
            crs1=new boolean[2*n];
            crs2=new boolean[2*n];
        }
    }

    static int input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.close();
        return n;
    }

    static void backtrack(int[] rs, int i, int n, Range used){
        if(i == n){
            System.out.println(Arrays.toString(rs));
            return;
        }
            for (int j=0; j<n;j++){
                if(used.row[i] || used.col[j] || used.crs1[i-j+n] || used.crs1[i+j]){
                    continue;
                }
                used.row[i] = used.col[j] = used.crs1[i-j+n] = used.crs2[i+j] = true;
                rs[i] = j+1;

                backtrack(rs, i+1, n, used);

                rs[i] = -1;
                used.row[i] = used.col[j] = used.crs1[i-j+n] = used.crs2[i+j] = false;
            }
    }

    static void solution(int n){
        int[] rs = new int[n];
        Range used = new Range(n);
        backtrack(rs, 0, n, used);
    }

    public static void main(String[] args) {
        int n = input();
        solution(n);
    }

}
