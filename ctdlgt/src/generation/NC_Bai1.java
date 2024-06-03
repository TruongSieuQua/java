package generation;

import java.util.Arrays;
import java.util.Scanner;

public class NC_Bai1 {
    static int minPath = Integer.MAX_VALUE;

    static class Route{
        int[] tour;
        int cost;
        Route(int[] tour, int cost){
            this.tour = tour;
            this.cost = cost;
        }
    }

    private static int[][] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int in = sc.nextInt();
                map[i][j] = in;
                if(in > 0){
                    minPath = Math.min(minPath, in);
                }
            }
        }
        return map;
    }

    private static void output(int rs){
        System.out.println(rs);
    }

    // i la hang. Bat dau tu 1 thi la hang 0
    private static void branchAndBound(int[][] map, int n, int i, boolean[] used, int currentPos, Route currentRoute,
             Route minRoute, int minPath){

        used[currentPos-1] = true;
        currentRoute.tour[i] = currentPos;

        // Di chuyen tu currentPos toi thanh pho j
        for(int j=0; j<n; j++){
            if(!used[j] && currentRoute.cost+minPath*(n-i) < minRoute.cost){
                currentRoute.cost+= map[currentPos-1][j];

                branchAndBound(map, n, i+1, used, j+1, currentRoute, minRoute, minPath);

                currentRoute.cost-= map[currentPos-1][j];
            }
        }
        if(i == n-1){
            currentRoute.tour[n] = 1;
            currentRoute.cost += map[currentPos-1][0];
            if(currentRoute.cost < minRoute.cost){
                minRoute.tour = Arrays.copyOf(currentRoute.tour, currentRoute.tour.length);
                minRoute.cost = currentRoute.cost;
            }
            currentRoute.cost -= map[currentPos-1][0];
        }
        used[currentPos-1] = false;
    }

    private static Route solution(int n, int[][] map){
        boolean[] used = new boolean[n];

        Route currentRoute = new Route(new int[n+1], 0);
        Route minRoute = new Route(new int[1], Integer.MAX_VALUE);

        branchAndBound(map, n, 0, used, 1, currentRoute, minRoute, minPath);
        return minRoute;
    }

    public static void main(String[] args) {
        int[][] map = input();
        int n = map.length;
        Route minRoute = solution(n , map);
        output(minRoute.cost);
    }
}
