import javax.swing.plaf.metal.MetalIconFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AoC11 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC11.txt"))) {
            String line = br.readLine();
            List<List<Character>> lines = new ArrayList<>();
            List<Integer> rowExpansion = new ArrayList<>();
            int multiplicator = 999999;
            int line_count = 0;
            while (line != null) {
                boolean hashFound = false;
                List<Character> listC = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    listC.add(line.charAt(i));
                    if (line.charAt(i) == '#') {
                        hashFound = true;
                    }
                }
                lines.add(listC);
                if (!hashFound) {
                    rowExpansion.add(line_count);
                }
                line = br.readLine();
                line_count++;
            }
            List<Integer> colExpansion = new ArrayList<>();
            for (int i = lines.get(0).size() - 1; i >= 0; i--) {
                boolean hashfound = false;
                for (List<Character> characterList : lines) {
                    if (characterList.get(i) == '#') {
                        hashfound = true;
                        break;
                    }
                }
                if (!hashfound) {
                    colExpansion.add(i);
                }
            }
            List<int[]> hashLocs = new ArrayList<>();
            for (int i = 0 ; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(0).size(); j++) {
                    if (lines.get(i).get(j) == '#') {
                        hashLocs.add(new int[]{i, j});
                    }
                }
            }
            long total = 0;
            for (int i = 0; i < hashLocs.size(); i++) {
                for (int j = i + 1; j < hashLocs.size(); j++) {
                    total += Math.abs(hashLocs.get(j)[1] - hashLocs.get(i)[1]) + Math.abs(hashLocs.get(j)[0] - hashLocs.get(i)[0]);
                    for (Integer col: colExpansion) {
                        if (col < Math.max(hashLocs.get(i)[1], hashLocs.get(j)[1]) && col > Math.min(hashLocs.get(i)[1], hashLocs.get(j)[1])) {
                            total += multiplicator;
                        }
                    }
                    for (Integer row: rowExpansion) {
                        if (row < Math.max(hashLocs.get(i)[0], hashLocs.get(j)[0]) && row > Math.min(hashLocs.get(i)[0], hashLocs.get(j)[0])) {
                            total += multiplicator;
                        }
                    }
                }
            }
            System.out.println(total);
        }
    }
}
