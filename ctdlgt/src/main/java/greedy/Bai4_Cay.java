package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Bai4_Cay {
    public static Integer[] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Integer[] days = new Integer[n];
        for (int i = 0; i < n; i++) {
            days[i] = sc.nextInt();
        }
        sc.close();
        return days;
    }

    static void solution(Integer[] days){
        Arrays.sort(days, (i1, i2)->  Integer.compare(i2, i1));
        int n = days.length;
        Integer day = 0;
        for (int i = 0; i < n; i++) {
            int t = i+1+days[i];
            day = t > day ? t: day;
        }
        System.out.println(day+1);
    }

    public static void main(String[] args) {
        Integer[] days = input();
        solution(days);

    }
}
