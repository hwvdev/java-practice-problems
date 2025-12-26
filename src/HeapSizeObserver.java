public class HeapSizeObserver {

    public static void main(String[] args) throws Exception {

        Runtime runtime = Runtime.getRuntime();

        printHeap("Startup", runtime);

        // Create allocation pressure
        byte[][] allocations = new byte[1][];

        for (int i = 0; i < allocations.length; i++) {
            allocations[i] = new byte[10 * 1024 * 1024]; // 10 MB

            printHeap("After allocation " + (i + 1), runtime);

            Thread.sleep(500);
        }
    }

    private static void printHeap(String phase, Runtime runtime) {
        long used = runtime.totalMemory() - runtime.freeMemory();
        long total = runtime.totalMemory();
        long max = runtime.maxMemory();

        System.out.printf(
            "%s | Used: %d MB | Total(heap): %d MB | Max: %d MB%n",
            phase,
            used / (1024 * 1024),
            total / (1024 * 1024),
            max / (1024 * 1024)
        );
    }
}
