import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC3 {
    public static void main(String[] args) throws IOException {
        Map<Key, Integer> stars = new HashMap<>();
        Map<Key, Integer> amount = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("AoC3.txt"))) {
            String line = br.readLine();
            List<String> lines = new ArrayList<>();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            Map<Key, Boolean> thisStars = new HashMap<>();
            boolean adjToSymbol = false;
            int num = 0;
            int sum = 0;
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    if (Character.isDigit(lines.get(i).charAt(j))) {
                        num *= 10;
                        num += Character.getNumericValue(lines.get(i).charAt(j));
                        for (int r = -1; r < 2; r++) {
                            for (int c = -1; c < 2; c++) {
                                if (0 <= r + i && r + i < lines.get(i).length() && 0 <= c + j && c + j < lines.get(i).length()) {
                                    if (lines.get(r + i).charAt(c + j) == '*') {
                                        Key arr = new Key(r + i, c + j);
                                        thisStars.put(arr, true);
                                    }
                                }
                            }
                        }
                        if (j == lines.get(i).length() - 1) {
                            for (Map.Entry<Key, Boolean> entry: thisStars.entrySet()) {
                                stars.putIfAbsent(entry.getKey(), 0);
                                amount.putIfAbsent(entry.getKey(), 1);
                                stars.put(entry.getKey(), stars.get(entry.getKey()) + 1);
                                amount.put(entry.getKey(), amount.get(entry.getKey()) * num);
                            }
                            thisStars.clear();
                            num = 0;
                        }
                    } else {
                        for (Map.Entry<Key, Boolean> entry: thisStars.entrySet()) {
                            stars.putIfAbsent(entry.getKey(), 0);
                            amount.putIfAbsent(entry.getKey(), 1);
                            stars.put(entry.getKey(), stars.get(entry.getKey()) + 1);
                            amount.put(entry.getKey(), amount.get(entry.getKey()) * num);
                        }
                        thisStars.clear();
                        num = 0;
                    }
                }
            }
            for (Map.Entry<Key, Integer> star: stars.entrySet()) {
                if (star.getValue() == 2) {
                    sum += amount.get(star.getKey());
                }
            }
            System.out.println(sum);
        }
    }
}

