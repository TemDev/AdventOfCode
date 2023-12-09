import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC8 {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("AoC8.txt"))) {
            String line  = br.readLine();
            String navigations = line;
            br.readLine();
            line = br.readLine();
            Map<String, String> leftMap = new HashMap<>();
            Map<String, String> rightMap = new HashMap<>();
            List<String> starter = new ArrayList<>();
            List<Integer> totals = new ArrayList<>();
            while (line != null) {
                String[] parts = line.split("=");
                String[] values = parts[1].split(",");
                leftMap.put(parts[0].strip(), values[0].substring(2));
                rightMap.put(parts[0].strip(), values[1].substring(1, 4));
                if (parts[0].charAt(2) == 'A') {
                    starter.add(parts[0].substring(0, 3));
                    totals.add(0);
                }
                line = br.readLine();
            }
            int pointer = 0;
            int endWithZ = 0;
            while (endWithZ != starter.size()) {
                endWithZ = 0;

                for (int i = 0; i < starter.size(); i++) {
                    if (starter.get(i).charAt(2) == 'Z') {
                        endWithZ++;
                        continue;
                    }
                    if (navigations.charAt(pointer) == 'L') {
                        starter.set(i, leftMap.get(starter.get(i)));
                    } else {
                        starter.set(i, rightMap.get(starter.get(i)));
                    }
                    totals.set(i, totals.get(i) + 1);
                }
                pointer += 1;
                if (pointer == navigations.length()) {
                    pointer = 0;
                }
            }
            Map<Integer, Integer> factorMax = new HashMap<>();
            Map<Integer, Integer> factors = new HashMap<>();
            for (Integer x: totals) {
                for (int i = 2; i < 200; i++) {
                    factors.put(i, 0);
                    while (x % i == 0) {
                        factors.put(i, factors.get(i) + 1);
                        x /= i;
                    }
                    factorMax.put(i, Math.max(factors.get(i), factorMax.getOrDefault(i, 0)));
                }
                if (x != 1) {
                    factorMax.put(x, 1);
                }
            }
            long total = 1;
            for (Map.Entry<Integer, Integer> entry: factorMax.entrySet()) {
                total *= (long) Math.pow(entry.getKey(), entry.getValue());
            }
            System.out.println(total);
        }
    }
}
