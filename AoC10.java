import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AoC10 {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("AoC10.txt"))) {
            String line = br.readLine();
            List<char[]> map = new ArrayList<>();
            int line_count = 0;
            int[] loc = new int[2];
            while (line != null) {
                map.add(line.toCharArray());
                for (int i = 0; loc[0] == 0 && i < line.length(); i++) {
                    if (line.charAt(i) == 'S') {
                        loc[0] = line_count;
                        loc[1] = i;
                        break;
                    }
                }
                line = br.readLine();
                line_count++;
            }
            int[][] visited = new int[line_count][map.get(0).length];
            Queue<int[]> locations = new LinkedList<>();
            visited[loc[0]][loc[1]] = 1;
            locations.add(loc);
            int[] finalCoords = new int[2];
            while (!locations.isEmpty()) {
                int[] cur = locations.poll();
                char c = map.get(cur[0])[cur[1]];
                if (c == 'S') {
                    checkN(map, line_count, visited, locations, cur);
                    checkE(map, line_count, visited, locations, cur);
                } else if (c == '|') {
                    finalCoords = checkN(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                    finalCoords = checkS(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                } else if (c == '-') {
                    finalCoords = checkW(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                    finalCoords = checkE(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                } else if (c == 'L') {
                    finalCoords = checkN(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                    finalCoords = checkE(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                } else if (c == 'J') {
                    finalCoords = checkN(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                    finalCoords = checkW(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                } else if (c == '7') {
                    finalCoords = checkS(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                    finalCoords = checkW(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                } else if (c == 'F') {
                    finalCoords = checkE(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                    finalCoords = checkS(map, line_count, visited, locations, cur);
                    if (finalCoords[0] != 0 && finalCoords[1] != 0) {
                        break;
                    }
                }
            }
            System.out.println(visited[finalCoords[0]][finalCoords[1]]);
            locations.add(new int[]{0, 0});
            while (!locations.isEmpty()) {
                int[] cur = locations.poll();
                if (cur[0] > 0) {
                    if (visited[cur[0] - 1][cur[1]] == 0) {
                        visited[cur[0] - 1][cur[1]] = -1;
                        locations.add(new int[]{cur[0] - 1, cur[1]});
                    }
                }
                if (cur[1] > 0) {
                    if (visited[cur[0]][cur[1] - 1] == 0) {
                        visited[cur[0]][cur[1] - 1] = -1;
                        locations.add(new int[]{cur[0], cur[1] - 1});
                    }
                }
                if (cur[0] < line_count - 1) {
                    if (visited[cur[0] + 1][cur[1]] == 0) {
                        visited[cur[0] + 1][cur[1]] = -1;
                        locations.add(new int[]{cur[0] + 1, cur[1]});
                    }
                }
                if (cur[1] < map.get(0).length - 1) {
                    if (visited[cur[0]][cur[1] + 1] == 0) {
                        visited[cur[0]][cur[1] + 1] = -1;
                        locations.add(new int[]{cur[0], cur[1] + 1});
                    }
                }
            }
            int num = 0;
            for (int i = 0; i < line_count; i++) {
                for (int j = 0; j < map.get(0).length; j++) {
                    if (visited[i][j] == 0) {
                        int count = 0;
                        for (int k = j + 1; k < map.get(0).length; k++) {
                            if (visited[i][k] != 0 && visited[i][k] != -1 && (map.get(i)[k] == '|' || map.get(i)[k] == 'L' || map.get(i)[k] == 'J' || map.get(i)[k] == '7' || map.get(i)[k] == 'F')) {
                                if (map.get(i)[k] == '|' || map.get(i)[k] == 'L' || map.get(i)[k] == 'J') {
                                    count++;
                                }
                            }
                        }
                        if (count % 2 == 1) {
                            num++;
                        } else {
                            visited[i][j] = -2;
                        }
                    }
                }
            }
            System.out.println(num);

        }
    }

    private static int[] checkE(List<char[]> map, int line_count, int[][] visited, Queue<int[]> locations, int[] cur) {
        if (cur[1] + 1 < map.get(0).length && visited[cur[0]][cur[1] + 1] == 0) {
            visited[cur[0]][cur[1] + 1] = visited[cur[0]][cur[1]] + 1;
            locations.add(new int[]{cur[0], cur[1] + 1});
        } else if (cur[1] + 1 < line_count && visited[cur[0]][cur[1] + 1] > visited[cur[0]][cur[1]]) {
            return new int[]{cur[0], cur[1] + 1};
        }
        return new int[]{0, 0};
    }

    private static int[] checkW(List<char[]> map, int line_count, int[][] visited, Queue<int[]> locations, int[] cur) {
        if (cur[1] - 1 >= 0 && visited[cur[0]][cur[1] - 1] == 0) {
            visited[cur[0]][cur[1] - 1] = visited[cur[0]][cur[1]] + 1;
            locations.add(new int[]{cur[0], cur[1] - 1});
        } else if (cur[1] - 1 >= 0&& visited[cur[0]][cur[1] - 1] > visited[cur[0]][cur[1]]) {
            return new int[]{cur[0], cur[1] - 1};
        }
        return new int[]{0, 0};
    }

    private static int[] checkS(List<char[]> map, int line_count, int[][] visited, Queue<int[]> locations, int[] cur) {
        if (cur[0] + 1 < line_count && visited[cur[0] + 1][cur[1]] == 0 && map.get(cur[0] + 1)[cur[1]] != 'S') {
            visited[cur[0] + 1][cur[1]] = visited[cur[0]][cur[1]] + 1;
            locations.add(new int[]{cur[0] + 1, cur[1]});
        } else if (cur[0] + 1 < line_count && visited[cur[0] + 1][cur[1]] > visited[cur[0]][cur[1]]) {
            return new int[]{cur[0] + 1, cur[1]};
        }
        return new int[]{0, 0};
    }

    private static int[] checkN(List<char[]> map, int line_count, int[][] visited, Queue<int[]> locations, int[] cur) {
        if (cur[0] - 1 >= 0 && visited[cur[0] - 1][cur[1]] == 0) {
            visited[cur[0] - 1][cur[1]] = visited[cur[0]][cur[1]] + 1;
            locations.add(new int[]{cur[0] - 1, cur[1]});
        } else if (cur[0] - 1 >= 0 && visited[cur[0] - 1][cur[1]] > visited[cur[0]][cur[1]]) {
            return new int[]{cur[0] - 1, cur[1]};
        }
        return new int[]{0, 0};
    }

}
