package greedy;

import java.util.*;
import java.io.*;

public class Bai4__Cay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int[] growthTimes = new int[n];
        for (int i = 0; i < n; i++) {
            growthTimes[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(growthTimes);
        int maxDays = 0;

        for (int i = 0; i < n; i++) {
            int daysNeeded = growthTimes[n - 1 - i] + i + 1;
            if (daysNeeded > maxDays) {
                maxDays = daysNeeded;
            }
        }

        System.out.println(maxDays + 1);
    }
}
