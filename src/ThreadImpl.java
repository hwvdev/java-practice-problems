public class ThreadImpl {
    public static void main(String[] args) {
        Student std = new Student();


    }

    public static class Student {
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class Student2 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
