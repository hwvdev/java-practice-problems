import java.math.BigDecimal;
import java.util.*;

public class AutoboxingPitfalls {

    public static void main(String[] args) {
        BigDecimal no = new BigDecimal(50);
        BigDecimal bd = sqrt(no, new BigDecimal(0), no, new BigDecimal(0.0001));
        System.out.println("Number: " + no + " ,Sqrt: " + bd + " , sqrt: " + bd.multiply(bd));
    }

    private static BigDecimal sqrt(BigDecimal bd, BigDecimal low, BigDecimal high, BigDecimal error) {
        if (low.compareTo(high) > 0) return null;
        BigDecimal mid = (low.add(high)).divide(new BigDecimal(2));

        BigDecimal product = mid.multiply(mid);
        if (product.subtract(bd).abs().compareTo(error)<=0) return mid;

        if (product.compareTo(bd)<0)
            return sqrt(bd, mid, high, error);
        else
            return sqrt(bd, low, mid, error);
    }

}