import java.util.ArrayList;
import java.util.List;

public class GCDemo2_SurvivorJourney {
    public static void main(String[] args) {
        System.out.println("=== Starting Demo: Survivor Journey ===");
        
        // This list will keep references to "surviving" objects
        List<MemoryObject> survivors = new ArrayList<>();
        
        // Create multiple objects to fill Eden space
        for (int i = 1; i <= 5; i++) {
            MemoryObject obj = new MemoryObject("Candidate-" + i);
            
            // Keep references to first 2 objects - they will survive
            if (i <= 2) {
                survivors.add(obj);
            }
            // Objects 3,4,5 will become garbage
        }
        
        System.out.println("--- Before GC: Eden is full ---");
        System.out.println("Survivors list holds: " + survivors.size() + " objects");
        
        // Force GC - this will trigger Minor GC
        System.gc();
        
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        System.out.println("--- After GC ---");
        System.out.println("Candidate-1 and Candidate-2 moved to Survivor Space");
        System.out.println("Candidate-3,4,5 were collected from Eden\n");
        
        // Clear the list - now our survivors become garbage
        survivors.clear();
        System.gc();
        
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        System.out.println("=== Demo Complete ===");
    }
}