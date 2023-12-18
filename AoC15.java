import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AoC15 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC15.txt"))) {
            String line = br.readLine();
            int total = 0;
            int currentValue = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) !=  ',') {
                    currentValue += line.charAt(i);
                    currentValue *= 17;
                    currentValue %= 256;
                } else {
                    total += currentValue;
                    currentValue = 0;
                }
            }
            total += currentValue;
            System.out.println(total);
            String[] splitted = line.split(",");
            Map<String, Integer> focalLength = new HashMap<>();
            List<String>[] boxes = new ArrayList[256];
            for (int i = 0; i < 256; i++) {
                boxes[i] = new ArrayList<>();
            }

            for (String split: splitted) {
                String[] sides = split.split("=");
                if (sides.length == 1) {
                    sides = sides[0].split("-");
                    int val = 0;
                    for (int i = 0; i < sides[0].length(); i++) {
                        val += sides[0].charAt(i);
                        val *= 17;
                        val %= 256;
                    }
                    boxes[val].remove(sides[0]);
                    focalLength.remove(sides[0]);
                } else {
                    int val = 0;
                    for (int i = 0; i < sides[0].length(); i++) {
                        val += sides[0].charAt(i);
                        val *= 17;
                        val %= 256;
                    }
                    if (!focalLength.containsKey(sides[0])) {
                        boxes[val].add(sides[0]);
                    }
                    focalLength.put(sides[0], Integer.parseInt(sides[1]));
                }
            }
            total = 0;
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < boxes[i].size(); j++) {
                    total += (i + 1) * (j + 1) * focalLength.get(boxes[i].get(j));
                }
            }
            System.out.println(total);
        }
    }
}
