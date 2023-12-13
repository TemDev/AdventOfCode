import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AoC13 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC13.txt"))) {
            String line = br.readLine();
            int total = 0;
            List<Integer> rowCount = new ArrayList<>();
            while (line != null) {
                rowCount.clear();
                Integer[] colCount = new Integer[line.length()];
                Arrays.fill(colCount, 0);
                int rowNum = 0;
                while (line != null && !line.isEmpty()) {
                    int sum = 0;
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '#') {
                            sum += (int) Math.pow(2, i);
                            colCount[i] += (int) Math.pow(2, rowNum);
                        }
                    }
                    rowCount.add(sum);
                    line = br.readLine();
                    rowNum++;
                }
                boolean toAdd = false;
                boolean changed;
                for (int i = 0; i < rowCount.size() - 1; i++) {
                    toAdd = true;
                    changed = false;
                    int n = Math.max(rowCount.get(i), rowCount.get(i + 1)) ^ Math.min(rowCount.get(i), rowCount.get(i + 1));
                    int v = Math.max(rowCount.get(i), rowCount.get(i + 1)) - Math.min(rowCount.get(i), rowCount.get(i + 1));
                    while (n > 1) {
                        if (n % 2 == 1) {
                            toAdd = false;
                            break;
                        }
                        n /= 2;
                    }
                    if (v > 0 && toAdd) {
                        changed = true;
                    }
                    if (changed || v == 0) {
                        for (int j = i + 2; j < rowCount.size(); j++) {
                             int k = 2 * i - j + 1;
                             if (k < 0) {
                                 break;
                             }
                             if (!rowCount.get(j).equals(rowCount.get(k))) {
                                 if (changed) {
                                     toAdd = false;
                                     break;
                                 }
                                 n = Math.max(rowCount.get(j), rowCount.get(k)) ^ Math.min(rowCount.get(j), rowCount.get(k));
                                 while (n > 1) {
                                     if (n % 2 == 1) {
                                         toAdd = false;
                                         break;
                                     }
                                     n /= 2;
                                 }
                                 changed = true;
                             }
                        }
                        if (toAdd && changed) {
                            total += 100 * (i + 1);
                            break;
                        } else {
                            toAdd = false;
                        }
                    } else {
                        toAdd = false;
                    }
                }
                if (!toAdd) {
                    for (int i = 0; i < colCount.length - 1; i++) {
                        toAdd = true;
                        changed = false;
                        int n = Math.max(colCount[i], colCount[i + 1]) ^ Math.min(colCount[i], colCount[i + 1]);
                        int v = Math.max(colCount[i], colCount[i + 1]) - Math.min(colCount[i], colCount[i + 1]);
                        int mismatches = 0;
                        while (n > 1) {
                            if (n % 2 == 1) {
                                toAdd = false;
                                break;
                            }
                            n /= 2;
                        }
                        if (v > 0 && toAdd) {
                            changed = true;
                        }
                        if (changed || v == 0) {
                            for (int j = i + 2; j < colCount.length; j++) {
                                int k = 2 * i - j + 1;
                                if (k < 0) {
                                    break;
                                }
                                if (!colCount[j].equals(colCount[k])) {
                                    if (changed) {
                                        toAdd = false;
                                        break;
                                    }
                                    n = Math.max(colCount[j], colCount[k]) ^ Math.min(colCount[j], colCount[k]);
                                    while (n > 1) {
                                        if (n % 2 == 1) {
                                            toAdd = false;
                                            break;
                                        }
                                        n /= 2;
                                    }
                                    changed = true;
                                }
                            }
                            if (toAdd && changed) {
                                total += i + 1;
                                break;
                            }
                        }
                    }
                }
                line = br.readLine();
            }
            System.out.println(total);
        }
    }
}
