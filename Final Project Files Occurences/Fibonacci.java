import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Fibonacci {
    
    private static int fibRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    private static int fibLoop(int n) {
        if (n == 0) {
            return 0;
        }
        int[] fibs = new int[n + 1];
        fibs[0] = 0;
        fibs[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibs[i] = fibs[i - 1] + fibs[i - 2];
        }
        return fibs[n];
    }
    
    public static void main(String[] args) {

        try {
            FileWriter writer = new FileWriter("output.csv");
            writer.write("n, Recursive Runtime, Loop Runtime\n");
            for (int i = 2; i < 35; i += 2) {
                long startTime1 = System.nanoTime();
                int res1 = fibRecursive(i);
                long endTime1 = System.nanoTime();
                long runTime1 = endTime1 - startTime1;

                long startTime2 = System.nanoTime();
                int res2 = fibLoop(i);
                long endTime2 = System.nanoTime();
                long runTime2 = endTime2 - startTime2;
                
                String output = i + "," + runTime1 + "," + runTime2 + "\n";
                writer.write(output);
            }
            writer.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
