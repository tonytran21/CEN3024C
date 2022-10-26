import java.util.Random;

public class ConcurrencySum {
    static class Summation extends Thread {

        private final int[] arr;

        private final int low;
        private final int high;
        private int partialSum;

        public Summation(int[] arr, int low, int high) {

            this.arr = arr;

            this.low = low;

            this.high = Math.min(high, arr.length);

        }

        public int getPartialSum() {

            return this.partialSum;

        }

        public void run() {

            this.partialSum = sum(arr, low, high);

        }

        public static int sum(int[] arr) {

            return sum(arr, 0, arr.length);

        }

        public static int sum(int[] arr, int low, int high) {

            int total = 0;

            for (int i = low; i < high; i++) {

                total += arr[i];

            }

            return total;

        }

        public static int[] parallelSum(int[] arr) {

            return parallelSum(arr, Runtime.getRuntime().availableProcessors());

        }

        public static int[] parallelSum(int[] arr, int threads) {

            int size = (int) Math.ceil(arr.length * 1.0 / threads);

            Summation[] sums = new Summation[threads];

            for (int i = 0; i < threads; i++) {

                sums[i] = new Summation(arr, i * size, (i + 1) * size);

                sums[i].start();

            }

            try {

                for (Summation sum : sums) {

                    sum.join();

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int total = 0;

            for (Summation sum : sums) {

                total += sum.getPartialSum();

            }

            return new int[]{total, threads};

        }

    }


    public static void main(String[] args) {

        Random rand = new Random();

        int[] arr = new int[200000000];

        for (int i = 0; i < arr.length; i++) {

            arr[i] = rand.nextInt(10) + 1;

        }

        long start = System.currentTimeMillis();

        int singleThreadSum = Summation.sum(arr);

        long singleThreadDuration = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();

        int[] res = Summation.parallelSum(arr);

        long multiThreadDuration = System.currentTimeMillis() - start;

        System.out.println("Single thread sum: " + singleThreadSum);
        System.out.println("Single thread run time (ms): " + singleThreadDuration);
        System.out.println("Multi threads (" + res[1] + " threads) sum: " + res[0]);
        System.out.println("Multi threads run time (ms): " + multiThreadDuration);

    }

}
