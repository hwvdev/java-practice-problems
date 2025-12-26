package topJavaQues;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.Supplier;

public class BrokenSynchronization {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnalbe no return");
            }
        };

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Callable with return");
                return "Testing return for callable";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future futureRunn = executorService.submit(runnable);
        Future futureCall = executorService.submit(callable);
        System.out.println(futureRunn.get());
        System.out.println(futureCall.get());
        executorService.shutdown();

        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                System.out.println("Testing supplier return");
                return "Supplier";
            }
        };
        System.out.println();

        Thread.sleep(2000);
        CompletableFuture completableFuture = CompletableFuture.runAsync(runnable);
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(supplier);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(6);
//        atomicIntegerArray.weakCompareAndSetAcquire()
        System.out.println(completableFuture.get());
   //     System.out.println(completableFuture1.get());
    }

}