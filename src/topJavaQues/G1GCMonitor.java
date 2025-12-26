package topJavaQues;

import javax.management.*;
import java.lang.management.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class G1GCMonitor {
    private final GarbageCollectorMXBean g1YoungGC;
    private final GarbageCollectorMXBean g1OldGC;
    private final MemoryMXBean memoryBean;
    
    public G1GCMonitor() {

        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        this.g1YoungGC = gcBeans.stream()
            .filter(b -> b.getName().equals("G1 Young Generation"))
            .findFirst().orElse(null);
        this.g1OldGC = gcBeans.stream()
            .filter(b -> b.getName().equals("G1 Old Generation"))
            .findFirst().orElse(null);
        this.memoryBean = ManagementFactory.getMemoryMXBean();
    }
    
    public void printGCStats() {
        System.out.println("=== G1GC Monitoring ===");
        System.out.println("Young GC Count: " + g1YoungGC.getCollectionCount());
        System.out.println("Young GC Time: " + g1YoungGC.getCollectionTime() + "ms");
        System.out.println("Old/Mixed GC Count: " + g1OldGC.getCollectionCount());
        System.out.println("Old/Mixed GC Time: " + g1OldGC.getCollectionTime() + "ms");
        
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        double usagePercent = (double) heapUsage.getUsed() / heapUsage.getMax() * 100;
        System.out.println("Heap Usage: " + String.format("%.2f", usagePercent) + "%");
        
        if (usagePercent > 45) {
            System.out.println("⚠️  Close to IHOP threshold - Mixed GC may trigger soon");
        }
    }
    
    public void startContinuousMonitoring() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::printGCStats, 0, 30, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        G1GCMonitor g1GCMonitor = new G1GCMonitor();
        g1GCMonitor.startContinuousMonitoring();
    }
}