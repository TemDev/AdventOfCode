import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC16 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC16.txt"))) {
            String line = br.readLine();
            List<char[]> map = new ArrayList<>();
            while (line != null) {
                map.add(line.toCharArray());
                line = br.readLine();
            }
            Set<Integer>[][] visited = new HashSet[map.size()][map.get(0).length];
            List<LocationDirection> threads = new ArrayList<>();
            int most = 0;
            for (int y = 0; y < map.size(); y++) {
                for (int i = 0; i < map.size(); i++) {
                    for (int j = 0; j < map.get(0).length; j++) {
                        visited[i][j] = new HashSet<>();
                    }
                }
                most = getMost(threads, y, 0, 0, map, visited, most);
                for (int i = 0; i < map.size(); i++) {
                    for (int j = 0; j < map.get(0).length; j++) {
                        visited[i][j] = new HashSet<>();
                    }
                }
                most = getMost(threads, y, map.get(0).length - 1, 2, map, visited, most);
            }
            for (int x = 0; x < map.get(0).length; x++) {
                for (int i = 0; i < map.size(); i++) {
                    for (int j = 0; j < map.get(0).length; j++) {
                        visited[i][j] = new HashSet<>();
                    }
                }
                most = getMost(threads, 0, x, 1, map, visited, most);
                for (int i = 0; i < map.size(); i++) {
                    for (int j = 0; j < map.get(0).length; j++) {
                        visited[i][j] = new HashSet<>();
                    }
                }
                most = getMost(threads, map.size() - 1, x, 3, map, visited, most);
            }
            System.out.println(most);


        }
    }

    private static int getMost(List<LocationDirection> threads, int y, int x, int direction, List<char[]> map, Set<Integer>[][] visited, int most) {
        threads.add(new LocationDirection(x, y, direction));
        while (!threads.isEmpty()) {
            for (int i = threads.size() - 1; i >= 0; i--) {
                if (threads.get(i).getX() < 0 || threads.get(i).getX() >= map.get(0).length || threads.get(i).getY() < 0 || threads.get(i).getY() >= map.size()) {
                    threads.remove(i);
                    continue;
                }
                if (visited[threads.get(i).getY()][threads.get(i).getX()].contains(threads.get(i).getDirection())) {
                    threads.remove(i);
                    continue;
                } else {
                    visited[threads.get(i).getY()][threads.get(i).getX()].add(threads.get(i).getDirection());
                }
                char c = map.get(threads.get(i).getY())[threads.get(i).getX()];
                if (c == '.') {
                    threads.get(i).moveForward();
                } else if (c == '/') {
                    threads.get(i).forwardSlash();
                    threads.get(i).moveForward();
                } else if (c == '\\') {
                    threads.get(i).backSlash();
                    threads.get(i).moveForward();
                } else if (c == '|') {
                    if (threads.get(i).getDirection() == 1 || threads.get(i).getDirection() == 3) {
                        threads.get(i).moveForward();
                    } else {
                        threads.add(new LocationDirection(threads.get(i).getX(), threads.get(i).getY() - 1, 3));
                        threads.add(new LocationDirection(threads.get(i).getX(), threads.get(i).getY() + 1, 1));
                        threads.remove(i);
                    }
                } else if (c == '-') {
                    if (threads.get(i).getDirection() == 0 || threads.get(i).getDirection() == 2) {
                        threads.get(i).moveForward();
                    } else {
                        threads.add(new LocationDirection(threads.get(i).getX() - 1, threads.get(i).getY(), 2));
                        threads.add(new LocationDirection(threads.get(i).getX() + 1, threads.get(i).getY(), 0));
                        threads.remove(i);
                    }
                }
            }
        }
        int total = 0;
        for (Set[] visit : visited) {
            for (Set v : visit) {
                if (v.size() > 0) {
                    total++;
                }
            }
        }
        most = Math.max(most, total);
        return most;
    }
}
