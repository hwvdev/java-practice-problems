import java.util.Stack;

public class Random {


    // {{(([[]]))}}
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        String str = "}}}}{{{{";
        int n = str.length();
        int i=0;
        boolean ans = false;

        while (i<n) {
            char ch = str.charAt(i);
            if (ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);
                i++;
            } else {
                if (stack.isEmpty() && ch == ']' || ch == ')' || ch == '}') {
                    ans = false;
                    break;
                }
                char top = stack.peek();
                if (top == '(' && ch == ')') {
                    stack.pop();
                } else if (top == '[' && ch == ']') {
                    stack.pop();
                } else if (top == '{' && ch == '}') {
                    stack.pop();
                } else {
                    break;
                }
                i++;
            }
        }
        if (stack.isEmpty()) ans = true;
        System.out.println(ans);

    }

}
