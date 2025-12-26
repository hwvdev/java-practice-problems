class MemoryObject {
    private String id;
    //private byte[] data; // Some memory to make objects substantial
    
    public MemoryObject(String id) {
        this.id = id;
    //    this.data = new byte[1024 * 1024]; // 1MB to make GC noticeable
        System.out.println("Created: " + id);
    }
    
    @Override
    protected void finalize() throws Throwable {
        System.out.println(">>> GC Collected: " + id + " <<<");
        super.finalize();
    }
}