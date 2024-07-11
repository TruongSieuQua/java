package dp;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
            sum+=A[i];
        }
        if(sum%k == 0){
            System.out.println("Day da cho thoa man yeu cau.");
            System.out.println("Tong = " + sum);
            return;
        }

        int[][] dp = new int[n+1][k];
        dp[0][0] = 0;
        for (int i = 0; i < k; i++) {
            dp[0][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i <= n; i++) {
            for (int t = 1; t < k; t++) {

            }
        }
    }
}
