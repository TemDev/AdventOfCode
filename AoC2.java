import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AoC2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("AoC2.txt"))) {
            String line = br.readLine();
            int ans = 0;
            while (line != null) {
                System.out.println(line);
                String[] splitted = line.split(":");
                String[] splitted2 = splitted[1].split(";");
                int minimumRed = 0;
                int minimumGreen = 0;
                int minimumBlue = 0;
                for (String s : splitted2) {
                    String[] fullySplitted = s.split(" ");
                    System.out.println(Arrays.toString(fullySplitted));
                    for (int i = 2; i < fullySplitted.length; i += 2) {
                        if (fullySplitted[i].charAt(0) == 'r') {
                            minimumRed = Math.max(minimumRed, Integer.parseInt(fullySplitted[i - 1]));
                        } else if (fullySplitted[i].charAt(0) == 'g') {
                            minimumGreen = Math.max(minimumGreen, Integer.parseInt(fullySplitted[i - 1]));
                        } else if (fullySplitted[i].charAt(0) == 'b') {
                            minimumBlue = Math.max(minimumBlue, Integer.parseInt(fullySplitted[i - 1]));
                        }
                    }
                }
                System.out.println(minimumRed * minimumGreen * minimumBlue);
                ans += minimumRed * minimumGreen * minimumBlue;
                line = br.readLine();
            }
            System.out.println(ans);
        }
    }
}
