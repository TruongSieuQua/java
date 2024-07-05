package graph;

import java.util.Scanner;

public class Bai3_DSKe {

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
        for (int i = 0; i < n; i++) {
            System.out.printf("Vertex%4d:", i);
            boolean isFirst = true;
            for (int j = 0; j < n; j++) {
                if(a[i][j]==1){
                    if(!isFirst){
                        System.out.printf(",%3d", j);
                    }else{
                        System.out.printf("%3d", j);
                        isFirst = false;
                    }
                }
            }
            System.out.println();
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
