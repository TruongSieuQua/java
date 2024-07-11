package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Bai6_CaiTui01 {
    static class Item{
       int idx;
       int w;
       int v;
       float r;
       Item(int idx, int w, int v, float r){
           this.idx = idx;
           this.w = w;
           this.v = v;
           this.r = r;
       }
    }

    static void solution(Item[] items, int W){
        int n = items.length;
        Arrays.sort(items, (i1, i2)-> Float.compare(i2.r, i1.r));
        StringBuilder sb = new StringBuilder();
        int curWeight = 0;
        int curValue = 0;
        for (Item item: items){
            if(curWeight + item.w <= W){
                sb.append(String.format("%d(%d-%d-%.2f);", item.idx, item.w, item.v, item.r).replace(".", ","));
                curWeight+= item.w;
                curValue+= item.v;
            }
        }
        System.out.println("Tong trong luong =" + curWeight);
        System.out.println("Tong gia tri =" + curValue);
        System.out.print(sb);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int[] w = new int[n], v=new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
        }
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(i+1, w[i], v[i], (float)v[i]/w[i]);
        }

        solution(items, W);
    }
}
