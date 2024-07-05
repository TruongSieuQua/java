package graph;

import java.util.Scanner;

public class Bai2_DSCanh {

    static int[][][] input(){
        Scanner sc = new Scanner(System.in);
        int numsOfTest = sc.nextInt();
        int[][][] inputs = new int[numsOfTest][][];
        for (int t = 0; t < numsOfTest; t++) {
            int n = sc.nextInt();

            int[][] input = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    input[i][j] = sc.nextInt();
                }
            }
            inputs[t] = input;
        }
        return inputs;
    }

    static void solution(int[][] a){
        int n = a.length;
        boolean inDauPhay = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(a[i][j] == 1){
                    if(inDauPhay){
                        System.out.printf(", (%d, %d)", i, j);
                    }else {
                        System.out.printf("(%d, %d)", i, j);
                        inDauPhay = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][][] inputs = input();

        for (int[][] input : inputs) {
            solution(input);
            System.out.println();
        }
    }
}
