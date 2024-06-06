package generation;

import java.util.Scanner;

public class QL_Bai9 {

    static class Range {
        boolean[] row;
        boolean[] col;
        boolean[] crs1;
        boolean[] crs2;

        Range(int n) {
            row = new boolean[n];
            col = new boolean[n];
            crs1 = new boolean[2 * n];
            crs2 = new boolean[2 * n];
        }
    }

    static int[] input() {
        Scanner sc = new Scanner(System.in);
        int d = sc.nextInt();
        int c = sc.nextInt();
        sc.close();
        return new int[]{d, c};
    }

    static void backtrack(int[] rs, int i, int n,Range used){
        if(i==n){
            print(rs);
            return;
        }
        if(rs[i]!=0){
            backtrack(rs, i+1, n, used);
            return;
        }
        for(int j=0; j<n; j++){
            if(used.col[j] || used.crs1[i-j+n-1] || used.crs2[i+j]){
                continue;
            }
            used.col[j] = used.crs1[i-j+n-1] = used.crs2[i+j] = true;
            rs[i]=j+1;
            backtrack(rs, i+1, n, used);
            rs[i]=0;
            used.col[j] = used.crs1[i-j+n-1] = used.crs2[i+j] = false;
        }
    }

    static void solution(int n, int d, int c) {
        int[] rs = new int[n];
        Range used = new Range(n);

        used.col[c] = used.crs1[d-c+n-1] = used.crs2[d+c] = true;
        rs[d] = c+1;

        backtrack(rs, 0, n, used);
    }

    static void print(int[] rs){
        for(int i: rs){
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] ins = input();
        solution(8, ins[0]-1, ins[1]-1);
    }
}
