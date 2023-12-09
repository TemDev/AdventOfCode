import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AoC9 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC9.txt"))) {
            String line = br.readLine();
            int total = 0;
            List<List<Integer>> differences = new ArrayList<>();
            while (line != null) {
                differences.clear();
                String[] sequence = line.split(" ");
                Integer[] pSeq = new Integer[sequence.length];
                for (int i = 0; i < sequence.length; i++) {
                    pSeq[i] = Integer.parseInt(sequence[i]);
                }
                differences.add(new ArrayList<>(List.of(pSeq)));
                while(true) {
                    differences.add(new ArrayList<>());
                    for (int i = 1; i < differences.get(differences.size() - 2).size(); i++) {
                        differences.get(differences.size() - 1).add(differences.get(differences.size() - 2).get(i) - differences.get(differences.size() - 2).get(i - 1));
                    }
                    boolean zeroed = true;
                    for (int i = 0; i < differences.get(differences.size() - 1).size(); i++) {
                        if (differences.get(differences.size() - 1).get(i) != 0) {
                            zeroed = false;
                            break;
                        }
                    }

                    if (zeroed) {
                        break;
                    }
                }
                System.out.println(Arrays.toString(differences.get(differences.size() - 1).toArray()));
                for (int i = differences.size() - 2; i >= 0; i--) {
                    System.out.println(Arrays.toString(differences.get(i).toArray()));
                    int lastNow = differences.get(i).get(0);
                    int lastBefore = differences.get(i + 1).get(0);
                    differences.get(i).add(0, lastNow - lastBefore);
                    System.out.println(Arrays.toString(differences.get(i).toArray()));
                }
                total += differences.get(0).get(0);
                line = br.readLine();
            }
            System.out.println(total);
        }
    }
}
