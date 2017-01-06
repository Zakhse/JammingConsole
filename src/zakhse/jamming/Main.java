package zakhse.jamming;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger step = new AtomicInteger(0);

    public static void main(String[] args) {
        int repeats = 200;
        int numberOfThreads = 4;

        long time1 = System.currentTimeMillis();

        //region Running a series of experiments
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Double>> resultList = new LinkedList<>();
        for (int i = 0; i < repeats; i++) {
            resultList.add(executor.submit(new FieldGenerator(300, 10)));
        }
        double counter = 0.0;
        for (Future<Double> future : resultList) {
            try {counter += future.get();} catch (InterruptedException | ExecutionException e) {e.printStackTrace();}
        }
        executor.shutdown();
        System.out.printf("Average filled part = %.3f\n", counter / repeats);
        //endregion

        long time2 = System.currentTimeMillis();
        System.out.printf("Elapsed seconds: %.2f\n",(time2 - time1) / 1000.0);
    }
}

