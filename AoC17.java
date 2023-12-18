import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC17 {
    public static void main(String[] args) throws IOException {
        List<String[]> map = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("AoC17.txt"))) {
            String line = br.readLine();
            while (line != null) {
                map.add(line.split(""));
                line = br.readLine();
            }
        }

        // direction 0 = North, 1 = West, 2 = South, 3 = East
        Map<Pair17, Integer> visited = new HashMap<>();
        Queue<Pair17> queue = new LinkedList<>();
        queue.add(new Pair17(0, 0, 0, -1, 0));
        // To get part 1, change 11 to 4
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 4; j++) {
                visited.put(new Pair17(0, 0, i, j, 0), 0);
            }
        }

        while (!queue.isEmpty()) {
            Pair17 p = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                if (p.getDirection() == 0 && dir == 2) {
                    continue;
                }
                if (p.getDirection() == 2 && dir == 0) {
                    continue;
                }
                if (p.getDirection() == 1 && dir == 3) {
                    continue;
                }
                if (p.getDirection() == 3 && dir == 1) {
                    continue;
                }
                // Remove condition to get part 1
                if (p.getDirection() != -1 && p.getDirection() != dir && p.getMovesMade() < 4) {
                    continue;
                }
                // Change 10 to 3 to get part 1
                if (p.getMovesMade() != 10 || dir != p.getDirection()) {
                    switch (dir) {
                        case 0:
                            if (p.getY() > 0) {
                                Pair17 p2;
                                if (p.getDirection() == dir) {
                                    p2 = new Pair17(p.getX(), p.getY() - 1, p.getMovesMade() + 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY() - 1)[p.getX()]));
                                } else {
                                    p2 = new Pair17(p.getX(), p.getY() - 1, 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY() - 1)[p.getX()]));
                                }
                                if (!visited.containsKey(p2) || (visited.containsKey(p2) && p2.minimumCost < visited.get(p2))) {
                                    visited.put(p2, p2.minimumCost);
                                    queue.add(p2);
                                }
                            }
                            break;
                        case 1:
                            if (p.getX() < map.get(0).length - 1) {
                                Pair17 p2;
                                if (p.getDirection() == dir) {
                                    p2 = new Pair17(p.getX() + 1, p.getY(), p.getMovesMade() + 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY())[p.getX() + 1]));
                                } else {
                                    p2 = new Pair17(p.getX() + 1, p.getY(), 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY())[p.getX() + 1]));
                                }
                                if (!visited.containsKey(p2) || (visited.containsKey(p2) && p2.minimumCost < visited.get(p2))) {
                                    visited.put(p2, p2.minimumCost);
                                    queue.add(p2);
                                }
                            }
                            break;
                        case 2:
                            if (p.getY() < map.size() - 1) {
                                Pair17 p2;
                                if (p.getDirection() == dir) {
                                    p2 = new Pair17(p.getX(), p.getY() + 1, p.getMovesMade() + 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY() + 1)[p.getX()]));
                                } else {
                                    p2 = new Pair17(p.getX(), p.getY() + 1, 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY() + 1)[p.getX()]));
                                }
                                if (!visited.containsKey(p2) || (visited.containsKey(p2) && p2.minimumCost < visited.get(p2))) {
                                    visited.put(p2, p2.minimumCost);
                                    queue.add(p2);
                                }
                            }
                            break;
                        case 3:
                            if (p.getX() > 0) {
                                Pair17 p2;
                                if (p.getDirection() == dir) {
                                    p2 = new Pair17(p.getX() - 1, p.getY(), p.getMovesMade() + 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY())[p.getX() - 1]));
                                } else {
                                    p2 = new Pair17(p.getX() - 1, p.getY(), 1, dir, p.minimumCost + Integer.parseInt(map.get(p.getY())[p.getX() - 1]));
                                }
                                if (!visited.containsKey(p2) || (visited.containsKey(p2) && p2.minimumCost < visited.get(p2))) {
                                    visited.put(p2, p2.minimumCost);
                                    queue.add(p2);
                                }
                            }
                            break;
                    }
                }
            }
        }
        // change 11 to 4 to get part 1
        for (int i = 0; i < 11; i ++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(visited.get(new Pair17(map.get(0).length - 1, map.size() - 1, i, j, 0)));
            }
        }
    }
}
