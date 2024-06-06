package generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_Bai8 {

    static int input() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        return n;
    }

    static boolean isSaved(int[] rows, int n, int col, int row){
        // Goi i la chi so row
        for(int i=0; i < n; i++){
            if(rows[i] > 0){ // => i, rows[i] dat 1 con hau
                if(i==row || Math.abs(i-rows[i]+n) == Math.abs(row-col+n) || i+rows[i] == col+row){
                    return false;
                }
            }
        }
        return true;
    }

    // Thu dat col = 1 o khap moi rows
    // col bat dau tu 1 -> n
    static void backtrack(int[] rows, int col, int n, List<int[]> kq) {
        if(col-1==n){
            kq.add(rows.clone());
        }
        // Tim row de dat gia tri col
        for(int row=0; row<n; row++){
            if(isSaved(rows, n, col, row)){
                rows[row] = col;

                backtrack(rows, col+1, n, kq);

                rows[row] = 0;
            }
        }
    }

    static void solution(int n) {
        int[] rows = new int[n];
        List<int[]> kq = new ArrayList<>();
        backtrack(rows, 1, n, kq);
        print(kq);
    }

    static void print(List<int[]> kq){
        for(int i=0; i<kq.size(); i++){
            System.out.printf("Solution%3d: ", i+1);
            for(int j: kq.get(i)){
                System.out.printf("%3d", j);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = input();
        solution(n);
    }
}
