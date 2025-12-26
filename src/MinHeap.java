public class MinHeap {
    private int[] arr;
    private int size;
    private int currPointer = 0;

    public MinHeap() {
        arr = new int[24];
        this.size = 24;
    }

    public void add(int val) {
        arr[currPointer] = val;
        rearrangeUp();
        currPointer++;
    }

    public void printArr(){
        for (int i = 0; i < currPointer; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    private void rearrangeUp() {
        int p=currPointer/2;
        int c=currPointer;
        while(arr[p]>arr[c] && p>=0){
            swap(arr, p, c);
            c=p;
            p=c/2;
        }
    }

    public int remove() throws Exception {
        if (currPointer == 0 ) throw new Exception("Heap is empty");
        int ans = arr[0];
        currPointer--;
        swap(arr, 0, currPointer);
        rearrangeDown();
        return ans;
    }

    private void rearrangeDown() {
        int p = 0;
        int c1 = 2*p+1;
        int c2 = 2*p+2;

        while(c1 < currPointer && c2 < currPointer && (arr[p]>arr[c1] || arr[p]>arr[c2])) {
            if (arr[c1]<arr[c2]) {
                swap(arr, c1, p);
                p=c1;
                c1=2*p+1;
                c2=2*p+2;
            } else {
                swap(arr, c2, p);
                p=c2;
                c1=2*p+1;
                c2=2*p+2;
            }
        }
    }

    private void swap(int[] arr, int p, int c){
        int t= arr[p];
        arr[p]=arr[c];
        arr[c]=t;
    }

    public static void main(String[] args) throws Exception {
        MinHeap m = new MinHeap();
        m.add(14);
        m.add(34);
        m.add(12);
        m.add(3);
        m.add(5);
        m.add(1);
        m.add(43);
        m.printArr();

        for (int i = 0; i < 7; i++) {
            System.out.println(m.remove());
            m.printArr();
        }
    }

}
