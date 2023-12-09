
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC7 {
    public static void main(String[] args) throws IOException {
        List<Key> keys = new ArrayList<>();
        Map<Character, Integer> mapChar= new HashMap<>();
        mapChar.put('T', 10);
        mapChar.put('J', 1);
        mapChar.put('Q', 12);
        mapChar.put('K', 13);
        mapChar.put('A', 14);

        try(BufferedReader br = new BufferedReader(new FileReader("AoC7.txt"))) {
            String line = br.readLine();
            Map<Character, Integer> counts = new HashMap<>();
            int count2 = 0;
            while (line != null) {
                count2++;
                counts.clear();
                String[] parts = line.split(" ");
                int p2 = 0;
                int multiplier = (int) Math.pow(15, 4);
                for (int i = 0; i < 5; i++) {
                    counts.putIfAbsent(parts[0].charAt(i), 0);
                    counts.put(parts[0].charAt(i), counts.get(parts[0].charAt(i)) + 1);
                    if (parts[0].charAt(i) >= 'A') {
                        p2 += multiplier * mapChar.get(parts[0].charAt(i));
                    } else {
                        p2 += multiplier * Character.getNumericValue(parts[0].charAt(i));
                    }
                    multiplier /= 15;
                }
                Key k = getKey(counts, p2);
                k.rank = Integer.parseInt(parts[1]);
                k.additional = parts[0];
                keys.add(k);
                line = br.readLine();
            }
            Collections.sort(keys);
            int total = 0;
            for (Key k: keys) {
                System.out.println(k.getX());
                System.out.println(k.additional);
                total += count2 * k.rank;
                count2--;
            }
            System.out.println(total);
        }
    }

    private static Key getKey(Map<Character, Integer> counts, int p2) {
        int type;
        char maxChar = 'J';
        int curCount = 0;
        if (counts.containsKey('J')) {
            for (Map.Entry<Character, Integer> count: counts.entrySet()) {
                if (count.getKey() != 'J' && count.getValue() > curCount) {
                    curCount = count.getValue();
                    maxChar = count.getKey();
                }
            }
            counts.put(maxChar, curCount + counts.get('J'));
            if (counts.size() > 1) {
                counts.remove('J');
            }
        }
        if (counts.size() == 1) {
            type = 1;
        } else if (counts.size() == 2) {
            type = 3;
            for (Map.Entry<Character, Integer> count: counts.entrySet()) {
                if (count.getValue() == 4) {
                    type = 2;
                    break;
                }
            }
        } else if (counts.size() == 3){
            type = 5;
            for (Map.Entry<Character, Integer> count: counts.entrySet()) {
                if (count.getValue() == 3) {
                    type = 4;
                    break;
                }
            }
        } else if (counts.size() == 4) {
            type = 6;
        } else {
            type = 7;
        }
        return new Key(type, p2);
    }
}
