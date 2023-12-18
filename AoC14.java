import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC14 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC14.txt"))) {
            String line = br.readLine();
//            int[] lastFixed = new int[line.length()];
//            Arrays.fill(lastFixed, -1);
//            List<Integer> locs = new ArrayList<>();
//            int lines = 0;
//            while (line != null) {
//                for (int i = 0; i < line.length(); i++) {
//                    if (line.charAt(i) == '#') {
//                        lastFixed[i] = lines;
//                    } else if (line.charAt(i) == 'O') {
//                        lastFixed[i] ++;
//                        locs.add(lastFixed[i]);
//                    }
//                }
//                lines++;
//                line = br.readLine();
//            }
//            int sum = 0;
//            for (Integer loc: locs) {
//                sum += lines - loc;
//            }
//            System.out.println(sum);
            List<char[]> map = new ArrayList<>();
            while (line != null) {
                char[] arr = line.toCharArray();
                map.add(arr);
                line = br.readLine();
            }
            System.out.println(calculateLoad(map));
            Map<Integer, Set<Long>> mapping = new HashMap<>();
            Map<Integer, Long> prev = new HashMap<>();
            for (long i = 0; i < 1000000; i++) {
                if (i > 200000) {
                    int v = calculateLoad(map);
                    mapping.putIfAbsent(v, new HashSet<>());
                    if (prev.containsKey(v)) {
                        mapping.get(calculateLoad(map)).add(prev.get(v) - i);
                    }
                    prev.put(v, i);
                }
                cycle(map);

            }
            System.out.println(calculateLoad(map));
            for (char[] chars: map) {
                System.out.println(Arrays.toString(chars));
            }
        }
    }

    private static void cycle(List<char[]> map) {
        slideNorth(map);
        slideWest(map);
        slideSouth(map);
        slideEast(map);
    }

    private static int calculateLoad(List<char[]> map) {
        int total = 0;
        for (int i = 0; i < map.get(0).length; i++) {
            for (int j = 0; j < map.size(); j++) {
                if (map.get(j)[i] == 'O') {
                    total += map.size() - j;
                }
            }
        }
        return total;
    }

    private static void slideNorth(List<char[]> map) {
        int last;
        for (int i = 0; i < map.get(0).length; i++) {
            last = -1;
            for (int j = 0; j < map.size(); j++) {
                if (map.get(j)[i] == '#') {
                    last = j;
                } else if (map.get(j)[i] == 'O') {
                    last ++;
                    map.get(last)[i] = 'O';
                    if (j != last) {
                        map.get(j)[i] = '.';
                    }
                }
            }
        }
    }

    private static void slideSouth(List<char[]> map) {
        int last;
        for (int i = 0; i < map.get(0).length; i++) {
            last = map.size();
            for (int j = map.size() - 1; j >= 0; j--) {
                if (map.get(j)[i] == '#') {
                    last = j;
                } else if (map.get(j)[i] == 'O') {
                    last --;
                    map.get(last)[i] = 'O';
                    if (j != last) {
                        map.get(j)[i] = '.';
                    }
                }
            }
        }
    }

    private static void slideWest(List<char[]> map) {
        int last;
        for (int i = 0; i < map.size(); i++) {
            last = -1;
            for (int j = 0; j < map.get(0).length; j++) {
                if (map.get(i)[j] == '#') {
                    last = j;
                } else if (map.get(i)[j] == 'O') {
                    last ++;
                    map.get(i)[last] = 'O';
                    if (j != last) {
                        map.get(i)[j] = '.';
                    }
                }
            }
        }
    }

    private static void slideEast(List<char[]> map) {
        int last;
        for (int i = 0; i < map.size(); i++) {
            last = map.get(0).length;
            for (int j = map.get(0).length - 1; j >= 0; j--) {
                if (map.get(i)[j] == '#') {
                    last = j;
                } else if (map.get(i)[j] == 'O') {
                    last --;
                    map.get(i)[last] = 'O';
                    if (j != last) {
                        map.get(i)[j] = '.';
                    }
                }
            }
        }
    }
}
