package dp;

import java.util.Scanner;

public class Bai1 {

    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n+2];
        arr[0] = -2147483648;
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        arr[n+1] = 2147483647;
        return arr;
    }

    static void solution(int[] a){
        int n = a.length-2;
        int[] L = new int[n+2];
        int[] T = new int[n+2];
        int jmax;
        L[n+1] = 1;
        System.out.printf("L[%d]=1\n", n + 1);
        for (int i = n; i >= 0; i--) {
            jmax = n+1;
            System.out.printf("jmax=n+1=%d+1=%d\n", n, n + 1);
            for (int j = i + 1; j <= n + 1; j++){
                if( ((i == 0 || j == n + 1) || a[j] > a[i]) && L[j] > L[jmax]) {
                    System.out.printf("i=%d,j=%d,jmax=%d,a[%d]>a[%d] &&L[%d]>L[%d]:\n", i, j, jmax, j, i, j, jmax);
                    jmax = j;
                    System.out.printf("jmax=j=%d\n", j);
                }
            }

            L[i] = L[jmax] + 1;

            System.out.printf("L[%d]=L[%d]+1=%d\n", i, jmax, L[jmax] + 1);

            T[i] = jmax;

            System.out.printf("T[%d]=jmax=%d\n", i, jmax);
        }

        System.out.println(L[0] - 2);

        int k = T[0];

        while (k != n + 1) {
            System.out.printf("a[%d]=%d;", k, a[k]);
            k = T[k];
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = input();
        solution(arr);
    }
}
