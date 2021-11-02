package task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {
        //...
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = bufferedReader.readLine();
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName));
            String[] cities = bufferedReader1.readLine().split("\\s");
            StringBuilder result = getLine(cities);
            System.out.println(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder stringBuilder = new StringBuilder();
        if (words == null) return stringBuilder;
        List<String> chain = new ArrayList<>();
        List<String> list = new LinkedList<>(Arrays.asList(words));
        arrange(chain, list);
        for (int i = 0; i < chain.size(); i++) {
            if (i == chain.size() - 1) stringBuilder.append(chain.get(i));
            else stringBuilder.append(chain.get(i) + " ");
        }
        return stringBuilder;
    }

    public static void arrange(List<String> chain, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (canBeAdded(s, chain)) {
                chain.add(s);
                System.out.println(chain);
                String deleted = list.remove(i);
                arrange(chain, list);
                if (list.size() == 0) return;
                list.add(i, deleted);
                chain.remove(chain.size() - 1);
            }
        }
    }

    public static boolean canBeAdded(String s, List<String> chain) {
        return chain.isEmpty() ||
                chain.get(chain.size() - 1).toLowerCase().endsWith(String.valueOf(s.toLowerCase().charAt(0)));
    }
}

