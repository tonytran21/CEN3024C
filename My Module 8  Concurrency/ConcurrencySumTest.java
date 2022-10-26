import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class ConcurrencySumTest {
    Random rand = new Random();
    
    @Test
    void testArrayLength5() {
        int[] arr = new int[5];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10) + 1;
            sum += arr[i];
        }
        int singleThreadSum = ConcurrencySum.Summation.sum(arr);
        int[] multiThreadSum = ConcurrencySum.Summation.parallelSum(arr);
        Assertions.assertTrue(sum == singleThreadSum && sum == multiThreadSum[0]);
    }

    @Test
    void testArrayLength10() {
        int[] arr = new int[10];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10) + 1;
            sum += arr[i];
        }
        int singleThreadSum = ConcurrencySum.Summation.sum(arr);
        int[] multiThreadSum = ConcurrencySum.Summation.parallelSum(arr);
        Assertions.assertTrue(sum == singleThreadSum && sum == multiThreadSum[0]);
    }

    @Test
    void testArrayLength100() {
        int[] arr = new int[100];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10) + 1;
            sum += arr[i];
        }
        int singleThreadSum = ConcurrencySum.Summation.sum(arr);
        int[] multiThreadSum = ConcurrencySum.Summation.parallelSum(arr);
        Assertions.assertTrue(sum == singleThreadSum && sum == multiThreadSum[0]);
    }

    @Test
    void testArrayLength0() {
        int[] arr = new int[0];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10) + 1;
            sum += arr[i];
        }
        int singleThreadSum = ConcurrencySum.Summation.sum(arr);
        int[] multiThreadSum = ConcurrencySum.Summation.parallelSum(arr);
        Assertions.assertTrue(sum == singleThreadSum && sum == multiThreadSum[0]);
    }
}