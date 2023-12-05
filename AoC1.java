import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AoC1 {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("zero", 0);
        stringIntegerMap.put("one", 1);
        stringIntegerMap.put("two", 2);
        stringIntegerMap.put("three", 3);
        stringIntegerMap.put("four", 4);
        stringIntegerMap.put("five", 5);
        stringIntegerMap.put("six", 6);
        stringIntegerMap.put("seven", 7);
        stringIntegerMap.put("eight", 8);
        stringIntegerMap.put("nine", 9);
        try (BufferedReader br = new BufferedReader(new FileReader("AoC1.txt"))) {
            String line = br.readLine();
            while (line != null) {
                int first = -1;
                int last = -1;
                for (int i = 0; i < line.length(); i++) {
                    if ('0' <= line.charAt(i) && line.charAt(i) <= '9') {
                        if (first == -1) {
                            first = Character.getNumericValue(line.charAt(i));
                        }
                        last = Character.getNumericValue(line.charAt(i));
                    } else {
                        if (i >= 2) {
                            String sub = line.substring(i - 2, i + 1);
                            if (stringIntegerMap.containsKey(sub)) {
                                if (first == -1) {
                                    first = stringIntegerMap.get(sub);
                                }
                                last = stringIntegerMap.get(sub);
                                continue;
                            }

                        }
                        if (i >= 3) {
                            String sub = line.substring(i - 3, i + 1);
                            if (stringIntegerMap.containsKey(sub)) {
                                if (first == -1) {
                                    first = stringIntegerMap.get(sub);
                                }
                                last = stringIntegerMap.get(sub);
                                continue;
                            }
                        }
                        if (i >= 4) {
                            String sub = line.substring(i - 4, i + 1);
                            if (stringIntegerMap.containsKey(sub)) {
                                if (first == -1) {
                                    first = stringIntegerMap.get(sub);
                                }
                                last = stringIntegerMap.get(sub);
                            }
                        }
                    }
                }
                sum += first * 10 + last;
                line = br.readLine();
            }
        }
        System.out.println(sum);
    }
}
