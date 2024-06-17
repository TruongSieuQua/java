package greedy;

import java.util.Scanner;

public class NguoiDiDuLich {
    public static int[][] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] city = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                city[i][j] = sc.nextInt();
            }
        }
        return city;
    }

    public static void solution(int[][] city) {
        StringBuilder sb = new StringBuilder();
        int n = city.length;
        boolean[] visited = new boolean[n];

        sb.append("1");
        visited[0] = true;
        int currentCost = 0;
        int currentCity = 0;
        int nextCity = -1;
        for (int idx = 1; idx < n; idx++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < n; j++) {
                if(!visited[j] && min > city[currentCity][j]){
                    min = city[currentCity][j];
                    nextCity = j;
                }
            }
            sb.append(String.format("->%d(%d)", nextCity+1, min));
            currentCost += min;
            currentCity = nextCity;
            visited[nextCity] = true;
        }
        currentCost += city[currentCity][0];
        sb.append(String.format("->1(%d)", city[currentCity][0]));

        System.out.println("Tong chi phi=" + currentCost);
        System.out.print(sb);
    }

    public static void main(String[] args) {
        int[][] city = input();
        solution(city);
    }
}