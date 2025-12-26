public class EvenOddCount implements Runnable {

    private final Printer printer;

    EvenOddCount(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.print();
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        EvenOddCount evenOddCount = new EvenOddCount(printer);
        Thread evenThread = new Thread(evenOddCount, "Even");
        evenThread.start();

        Thread oddThread = new Thread(evenOddCount, "Odd");
        oddThread.start();

    }

}
