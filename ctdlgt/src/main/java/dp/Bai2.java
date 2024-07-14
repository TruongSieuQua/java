package dp;

import java.util.Scanner;

public class Bai2 {
    static int sub(int x, int y, int k)
    {
        int tmp = (x - y) % k;
        return tmp >= 0 ? tmp : tmp + k;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[n];
        int sum = 0;
        // A bat dau tu 0
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
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int t = 1; t < k; t++) {
                dp[i][t] = Math.max(dp[i - 1][t], dp[i - 1][sub(t, A[i - 1], k)] + 1);
            }
        }
        in(dp, n, k, A, sum);
    }

    public static void in(int[][] dp, int n, int k, int[] A, int sum) {
        System.out.print(" n\t");
        for (int i = 0; i < k; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        for (int i = 0; i <= n; i++) {
            System.out.printf("%4d", i);
            for (int t = 0; t < k; t++) {
                if (t > i) {
                    System.out.print(" +00");
                } else {
                    System.out.printf("%4d", dp[i][t]);
                }
            }
            System.out.println();
        }
        System.out.println("Chieu dai day con: " + (n - dp[n][sum % k]));

        // truy vet (traceback)
        int t = sum % k;
        sum = 0;
        for (int i = n; i >= 1; i--) {
            if (dp[i][t] == dp[i - 1][t]) {
                System.out.printf("a[%d]=%d;", i, A[i - 1]);
                sum += A[i - 1];
            } else {
                t = sub(t, A[i - 1], k);
            }
        }
        System.out.println();
        System.out.println("Tong =" + sum);
    }
}
