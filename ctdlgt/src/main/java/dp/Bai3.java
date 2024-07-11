package dp;

import java.util.ArrayList;
import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[n], C=new int[n];
        // A la khoi luong
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        // C la value
        for (int i = 0; i < n; i++) {
            C[i] = sc.nextInt();
        }

        int[][] dp = new int[n+1][M+1];

        // dp bat dau tu 1, A[], C[] bat dau tu 0
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= M; j++) {
                if(j < A[i-1]){
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j-A[i-1]] + C[i-1]);
                }
            }
        }
        in(dp, n, M, A, C);
    }
    static void in(int[][] dp, int n, int M, int[] A, int[] C) {
        System.out.print("   C   A i/v");
        for (int i = 0; i <= M; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("           0");
            } else {
                System.out.printf("%4d%4d%4d", C[i - 1], A[i - 1], i);
            }
            for (int j = 0; j <= M; j++) {
                System.out.printf("%4d", dp[i][j]);
            }
            System.out.println();
        }
        System.out.println(dp[n][M]);

        ArrayList<Integer> trace = new ArrayList<>();
        int w = M;
        for (int i = n; i > 0; i--) {
            if(dp[i][w] != dp[i-1][w]){
                trace.add(i);
                w-=A[i-1];
            }
        }
        for (int i = trace.size()-1; i >=0 ; i--) {
            int idx = trace.get(i);
            System.out.printf("%d(%d,%d) ", idx, A[idx - 1], C[idx - 1]);
        }
    }
}
