import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AoC12 {
    static Map<CustomKey, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC12.txt"))) {
            String line = br.readLine();
            long total = 0;
            int count = 0;
            while (line != null) {
                String[] parts = line.split(" ");
                String g = (parts[1] + ',').repeat(5).substring(0, (parts[1].length() + 1) * 5 - 1);
                Integer[] segmentsSize = Arrays.stream(g.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
//                segmentsSize = Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toArray(Integer[]::new);
                String j = (parts[0] + "?").repeat(5).substring(0, (parts[0].length() + 1) * 5 - 1);
//                j = parts[0];
                Boolean[] b = new Boolean[segmentsSize.length];
                total += validateSeq(segmentsSize, j);
                line = br.readLine();
                count++;
                map.clear();
            }
            System.out.println(total);
        }
    }
    public static long validateSeq(Integer[] segmentsSize, String j) {
        CustomKey k = new CustomKey(segmentsSize, j);
        if (map.containsKey(k)) {
            return map.get(k);
        }
        if (segmentsSize.length == 0) {
            for (int i = 0; i < j.length(); i++){
                if (j.charAt(i) == '#') {
                    map.put(new CustomKey(segmentsSize, j), 0L);
                    return 0;
                }
            }
            map.put(new CustomKey(segmentsSize, j), 1L);
            return 1;
        }
        if (j.isEmpty()) {
            map.put(new CustomKey(segmentsSize, j), 0L);
            return 0;
        }
        if (segmentsSize.length > j.length()) {
            map.put(new CustomKey(segmentsSize, j), 1L);
            return 0;
        }
        if (j.charAt(0) == '.') {
            long val = validateSeq(segmentsSize, j.substring(1));
            map.put(new CustomKey(segmentsSize, j), val);
            return val;
        } else if (j.charAt(0) == '#') {
            Integer[] copy;
            String toAdd = "";
            if (segmentsSize[0] > 1) {
                copy = Arrays.copyOfRange(segmentsSize, 0, segmentsSize.length);
                if (j.length() > 1) {
                    if (j.charAt(1) == '.') {
                        map.put(new CustomKey(segmentsSize, j), 0L);
                        return 0;
                    } else if (j.charAt(1) == '?') {
                        toAdd = "#";
                    }
                }
                copy[0] -= 1;
            } else {
                if (j.length() > 1) {
                    if (j.charAt(1) == '#') {
                        map.put(new CustomKey(segmentsSize, j), 0L);
                        return 0;
                    } else if (j.charAt(1) == '?') {
                        toAdd = ".";
                    }
                }

                copy = Arrays.copyOfRange(segmentsSize, 1, segmentsSize.length);
            }
            long val;
            if (toAdd.isBlank()) {
                val = validateSeq(copy, j.substring(1));
            } else {
                val = validateSeq(copy, toAdd + j.substring(2));
            }
            map.put(new CustomKey(segmentsSize, j), val);
            return val;
        } else{
            long val = validateSeq(segmentsSize, "." + j.substring(1)) + validateSeq(segmentsSize, "#" + j.substring(1));
            map.put(new CustomKey(segmentsSize, j), val);
            return val;
        }
    }
}
