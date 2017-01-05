package zakhse.jamming;

import zakhse.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {
        int repeats = 10;

        Queue<FieldGenerator> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(new FieldGenerator(200, 10, queue));
        }

        long time1 = System.currentTimeMillis();

        synchronized (queue) {
            while (repeats != 0) {
                queue.poll().start();
                repeats--;
                if (queue.isEmpty()) try {queue.wait();} catch (InterruptedException ignored) {}
            }
        }

        long time2 = System.currentTimeMillis();
        System.out.println("Elapsed seconds: " + (time2 - time1) / 1000);
    }
}

class FieldGenerator extends Thread {

    private int size;
    private int kmerSize;
    //private int repeats;
    final private Queue<FieldGenerator> queue;

    FieldGenerator(int size, int kmerSize, /*int repeats,*/ Queue<FieldGenerator> queue) {
        this.size = size;
        this.kmerSize = kmerSize;
        //this.repeats = repeats;
        this.queue = queue;
    }

    @Override
    public void run() {
        KmerField field = new KmerField();
        //for (int i = 0; i < repeats; i++) {
        field.generateField(size, kmerSize);
        System.out.println('-');
        //}
        synchronized (queue) {
            queue.add(new FieldGenerator(size, kmerSize, queue));
            queue.notifyAll();
        }
    }
}
