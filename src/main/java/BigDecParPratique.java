import java.math.BigDecimal;

public class BigDecParPratique {
    //https://getdoc.wiki/Java-math-bigdecimal
    //https://getdoc.wiki/Java-math-bigdecimal-abs-mc
    public static void main(String[] argv) throws Exception {
        BigDecimal val1 = new BigDecimal("37578975587.876876989");
        BigDecimal val2 = new BigDecimal("62567875598.976876569");
        BigDecimal val3 = new BigDecimal("72567875598.376876569");
        System.out.println("Value 1 : "+val1);
        System.out.println("Value 2 : "+val2);
        val1 = val1.add(val2);
        System.out.println("Addition Operation = " + val1);
        val1 = val1.multiply(val2);
        System.out.println("Multiplication Operation = " + val1);
        val2 = val3.subtract(val2);
        System.out.println("Subtract Operation = " + val2);
        val2 = val3.divide(val2,BigDecimal.ROUND_UP);
        System.out.println("Division Operation = " + val2);
        //https://stackoverflow.com/questions/42413020/bigdecimal-math-operations
        //C = A.subtract(A.multiply(B).divide(BigDecimal.valueOf(100)));
    }
}
