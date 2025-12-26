import java.util.Objects;

class MultiplyStrings {
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        String subAns = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            String res = multiplyDigit(num1, num2.charAt(i), num2.length() - 1 - i);
            System.out.println(res);
            if (subAns.equals("0")) {
                subAns = res;
            } else {
                subAns = addition(subAns, res);
            }
            System.out.println(subAns);
        }
        for (int i = 0; i < subAns.length(); i++) {
            if (subAns.charAt(i) != '0') {
                return subAns;
            }
        }
        return "0";
    }

    public String addition(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;

        if (a.length() > b.length()) {
            b = "0".repeat(a.length() - b.length()) + b;
        } else
            a = "0".repeat(b.length() - a.length()) + a;

        for (int i = a.length() - 1; i >= 0; i--) {
            int sum =  (a.charAt(i) - '0') + (b.charAt(i) - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public String multiplyDigit(String num1, char num2, int noOfZeros) {
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, noOfZeros)));

        int carry = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int pro = (num1.charAt(i) - '0') * (num2 - '0') + carry;
            carry = pro / 10;
            sb.append(pro % 10);
        }
        if (carry != 0) sb.append(carry);

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        MultiplyStrings m = new MultiplyStrings();
        String num1 = "929";
        String num2 = "98";
        String ans = m.multiply(num1, num2);
        System.out.println(ans);
    }
}