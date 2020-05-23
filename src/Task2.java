import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Task2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("src//transactions.csv"));
        sc.nextLine();
        //храняться в основном мапе все продукты , а в втором мапе все id корзин
        HashMap<String, HashMap<String, Integer>> mapM = new HashMap<>();
        while (sc.hasNext()) {
            String x = sc.nextLine();
            String[] x1 = x.split(";");
            if (!mapM.containsKey(x1[0])) {
                HashMap<String, Integer> in = new HashMap<>();
                in.put(x1[1], 1);
                mapM.put(x1[0], in);
            } else {
                HashMap<String, Integer> in = mapM.get(x1[0]);
                if (!in.containsKey(x1[1])) {
                    in.put(x1[1], 1);
                } else {
                    in.put(x1[1], in.get(x1[1]) + 1);
                }
            }
        }

        HashMap<String, Integer> mapR = new HashMap<>();
        int max = 0;
        Stack<String> res = new Stack<>();
        //создаем новый map,но уже с значением не map, а его размером
        for (Map.Entry entry : mapM.entrySet()) {
            HashMap<String, Integer> n = (HashMap<String, Integer>) entry.getValue();
            int n1 = n.size();
            mapR.put((String)entry.getKey(),n1);
        }

        for (Map.Entry entry : mapR.entrySet()) {
            int n2 = (int) entry.getValue();
            if (max == n2) res.add((String) entry.getKey());
            if (max < n2) {
                res.clear();
                res.add((String) entry.getKey());
                max = n2;
            }
        }
        System.out.println("popular:" + res);

        //для графика
        PrintWriter wr = new PrintWriter(new File("src//trans.csv"));
        for (Map.Entry entry : mapR.entrySet()) {
            wr.println(entry.getKey() + ";" + entry.getValue());
        }
    }
}
