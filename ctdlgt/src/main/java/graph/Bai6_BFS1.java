package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bai6_BFS1 {

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
            bfs(a, i, visited);
            System.out.printf("From%4d can visit: ", i);

            boolean isFirst = true;
            for (int j = 0; j < n; j++) {
                if(j!=i && visited[j]){
                    if(!isFirst){
                        System.out.printf(",%3d", j);
                    }else{
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

    static void bfs(int[][] a, int i, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        int n = a.length;

        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()){
            Integer j = queue.remove();

            for (int k = 0; k < n; k++) {
                if(!visited[k] && a[j][k]==1){
                    queue.add(k);
                    visited[k] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][][] inputs = input();
        for (int[][] a: inputs){
            solution(a);
        }
    }

}
