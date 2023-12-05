import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC4.txt"))) {
            String line = br.readLine();
            Set<Integer> winners = new HashSet<>();
            int total = 0;
            Set<Integer> all = new HashSet<>();
            Map<Integer, Integer> cards = new HashMap<>();
            cards.put(1, 1);
            all.add(1);
            while (line != null) {
                int count = 0;
                winners.clear();
                String[] part1 = line.split(":");
                String[] part2 = part1[1].strip().split("\\|");
                String[] part1_1 = part1[0].split(" ");
                int gameNumber = Integer.parseInt(part1_1[part1_1.length - 1]);
                String[] winningNumbers = part2[0].split(" ");
                String[] myNumbers = part2[1].strip().split(" ");
                for (String winner: winningNumbers) {
                    if (winner.isEmpty()) {
                        continue;
                    }
                    winners.add(Integer.parseInt(winner));
                }
                for (String myNumber: myNumbers) {
                    if (myNumber.isEmpty()) {
                        continue;
                    }
                    if (winners.contains(Integer.parseInt(myNumber))) {
                        count++;
                    }
                }
                for (int i = gameNumber + 1; i <= gameNumber + count; i++) {
                    cards.putIfAbsent(i, 1);
                    cards.putIfAbsent(gameNumber, 1);
                    cards.put(i, cards.get(gameNumber) + cards.get(i));
                }
                line = br.readLine();
            }
            total = 0;
            for (Map.Entry<Integer, Integer> entry: cards.entrySet()) {
                total += entry.getValue();
            }
            System.out.println(total);
        }
    }
}
