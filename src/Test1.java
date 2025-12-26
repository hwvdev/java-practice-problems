import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        int n = 5;
        int[] array;
        array = new int[]{2, 4, 5, 6, 25,67,43};

        List<Integer> prime = new ArrayList<>();
        List<Integer> nonPrime = new ArrayList<>();

        prime = Arrays.stream(array).filter(o-> isPrime(o)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        nonPrime = Arrays.stream(array).filter(o-> !isPrime(o)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

   //     System.out.println(prime);

  //      System.out.println(nonPrime);

        String exp = "1-10+30*10*11-20+3";
        System.out.println(cal(exp));
    }

    public static int cal(String expression) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int val = 0;

        for (int i = 0; i < expression.length(); i++) {
            int x = 0;
            while (i<expression.length() && Character.isDigit(expression.charAt(i))) {
                x= x*10 + (expression.charAt(i)-'0');
                i++;
            }
            if (!ops.isEmpty() && ops.peek()=='*') {
                ops.pop();
                int v1 = numbers.pop();
                int v2 = x;
                x = v2*v1;
            }
            numbers.push(x);
            if (i<expression.length())
                ops.push(expression.charAt(i));
        }

        while (!numbers.isEmpty()) {
            if (ops.pop()=='+') {
                int v1 = numbers.pop();
            } else {

            }
        }


        return val;
    }
    //  - + * *
    //  0 1 2 3 4
    // 3 - 2 + 150 - 6 + 2
    // 3,2,150,8
    //  - +  -
    // 0 1 2 3 4 5
    private static boolean isPrime(int n) {
        if (n==2 || n==3) return true;
        int upperLimit = (int)Math.sqrt(n);

        for (int i = 2; i <= upperLimit; i++) {
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }

}
