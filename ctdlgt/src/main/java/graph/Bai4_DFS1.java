package graph;

import java.util.Scanner;

public class Bai4_DFS1 {

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
            boolean[] visited = new boolean[n];
            dfs(a, i, visited);

            System.out.printf("From%4d can visit: ", i);
            boolean isFirst = true;
            for (int j = 0; j < n; j++) {
                if(visited[j] && j!=i) {
                    if (!isFirst) {
                        System.out.printf(",%3d", j);
                    } else {
                        System.out.printf("%3d", j);
                        isFirst = false;
                    }
                }
            }
            if(isFirst){
                System.out.print("No vertex");
            }
            System.out.println();
        }
    }


    static void dfs(int[][] a, int i, boolean[] visited){
        visited[i] = true;
        int n = a.length;
        for (int j=0; j<n; j++){
            if(!visited[j] && a[i][j]==1){
                dfs(a, j, visited);
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
