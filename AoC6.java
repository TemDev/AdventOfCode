import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AoC6  {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("AoC6.txt"))) {
            String line = br.readLine();
            String[] times = line.split(":")[1].strip().split("\\s+");
            times = new String[]{Arrays.stream(times).reduce(String::concat).get()};
            line = br.readLine();
            String[] record = line.split(":")[1].strip().split("\\s+");
            record = new String[]{Arrays.stream(record).reduce(String::concat).get()};
            int total = 1;
            for (int i = 0; i < times.length; i++) {
                System.out.println(times[i]);
                double time = Integer.parseInt(times[i].strip());
                double rec = Long.parseLong(record[i].strip());
                double v = Math.sqrt(Math.pow(time, 2) - 4 * rec);
                double biggerTime = (time + v) / 2;
                double smallerTime = (time - v) / 2;
                if (biggerTime == Math.round(biggerTime)) {
                    biggerTime--;
                }
                if (smallerTime == Math.round(smallerTime)) {
                    smallerTime++;
                }
                int bit1 = (int) Math.floor(biggerTime);
                int bit2 = (int) Math.ceil(smallerTime);
                if (bit2 > bit1) {
                    continue;
                }
                System.out.println(bit1);
                System.out.println(bit2);
                total *= (bit1 - bit2) + 1;
            }
            System.out.println(total);
        }
    }
}
