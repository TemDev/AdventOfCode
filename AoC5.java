import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC5 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC5.txt"))) {
            String line = br.readLine();
            String[] seeds = line.split(":")[1].strip().split(" ");
            line = br.readLine();
            Map<Integer, List<List<Long>>> everyMap = new HashMap<>();
            int counter = -1;
            boolean skip = false;
            while (line != null) {
                if (skip) {
                    skip = false;
                    line = br.readLine();
                    continue;
                }
                if (line.isBlank()) {
                    counter++;
                    List<Long> start = new ArrayList<>();
                    List<Long> end = new ArrayList<>();
                    List<Long> map = new ArrayList<>();
                    everyMap.put(counter, new ArrayList<>());
                    everyMap.get(counter).add(start);
                    everyMap.get(counter).add(end);
                    everyMap.get(counter).add(map);
                    skip = true;
                } else {
                    String[] p = line.split(" ");

                    Long[] parsedLine = new Long[p.length];
                    for (int i = 0; i < p.length; i++) {
                        parsedLine[i] = Long.parseLong(p[i]);
                    }
                    everyMap.get(counter).get(0).add(parsedLine[1]);
                    everyMap.get(counter).get(1).add(parsedLine[1] + parsedLine[2]);
                    everyMap.get(counter).get(2).add(parsedLine[0]);
                }
                line = br.readLine();
            }
            long min = Long.MAX_VALUE;
            boolean first = true;
            long set = 0;
//            for (String seed: seeds) {
//                if (first) {
//                    first = false;
//                    set = Long.parseLong(seed);
//                    continue;
//                }
//                System.out.println(set);
//                long val = set + Long.parseLong(seed);
//                System.out.println(val);
//                for (long k = set; k < val; k++) {
//                    long cur = k;
//                    for (int i = 0; i < 7; i++) {
//                        for (int j = 0; j < everyMap.get(i).get(0).size(); j++) {
//                            if (cur >= everyMap.get(i).get(0).get(j) && cur < everyMap.get(i).get(1).get(j)) {
//                                cur = cur - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j);
//                                break;
//                            }
//                        }
//                    }
//                    min = Math.min(cur, min);
//                }
//                first = true;
//            }
            List<long[]> se = new ArrayList<>();
            for (int i = 0; i < seeds.length; i++) {
                if (first) {
                    se.add(new long[2]);
                    first = false;
                } else {
                    first = true;
                    se.get(i / 2)[0] = Long.parseLong(seeds[i - 1]);
                    se.get(i / 2)[1] = Long.parseLong(seeds[i - 1]) + Long.parseLong(seeds[i]);
                }
            }
            for (int i = 0; i < 7; i++) {
                List<long[]> range = new ArrayList<>();
                for (int k = 0; k < se.size(); k++) {
                    for (int j = 0; j < everyMap.get(i).get(0).size(); j++) {
                        if (se.get(k)[0] >= everyMap.get(i).get(0).get(j) && se.get(k)[0] < everyMap.get(i).get(1).get(j)) {
                            if (se.get(k)[1] <= everyMap.get(i).get(1).get(j)) {
                                range.add(new long[]{se.get(k)[0] - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j),
                                        se.get(k)[1] - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j)});
                                se.get(k)[0] = -1;
                                se.get(k)[1] = -1;
                                break;
                            } else {
                                range.add(new long[]{se.get(k)[0] - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j),
                                        everyMap.get(i).get(1).get(j) - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j)});
                                se.get(k)[0] = everyMap.get(i).get(1).get(j);

                            }
                        } else if (se.get(k)[0] < everyMap.get(i).get(0).get(j) && se.get(k)[1] > everyMap.get(i).get(0).get(j)) {
                            if (se.get(k)[1] <= everyMap.get(i).get(1).get(j)) {
                                range.add(new long[]{everyMap.get(i).get(2).get(j),
                                        se.get(k)[1] - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j)});
                                se.get(k)[1] = everyMap.get(i).get(0).get(j);
                            } else {
                                range.add(new long[]{everyMap.get(i).get(2).get(j),
                                        everyMap.get(i).get(1).get(j) - everyMap.get(i).get(0).get(j) + everyMap.get(i).get(2).get(j)});
                                se.add(new long[]{everyMap.get(i).get(1).get(j), se.get(k)[1]});
                                se.get(k)[1] = everyMap.get(i).get(0).get(j);
                            }
                        }
                    }
                    if (!Arrays.equals(se.get(k), new long[]{-1, -1})) {
                        range.add(se.get(k));
                    }
                }
                se = range;
            }
            for (int i = 0; i < se.size(); i++) {
                System.out.println(Arrays.toString(se.get(i)));
                min = Math.min(se.get(i)[0], min);
            }
            System.out.println(min);
            // 50716416

        }
    }
}
