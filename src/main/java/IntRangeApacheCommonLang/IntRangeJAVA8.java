package IntRangeApacheCommonLang;
import org.apache.commons.lang.math.IntRange;
public class IntRangeJAVA8 {

    private static int brandNumberRange(Integer number){
        IntRange range0To20 = new IntRange(0,20);
        IntRange range21To40 = new IntRange(21,40);
        IntRange range41To60 = new IntRange(41,60);
        IntRange range61To80 = new IntRange(61,80);
        IntRange range81To100 = new IntRange(81,100);
        if(range0To20.containsInteger(number)){
            return 1;
        }else if(range21To40.containsInteger(number)){
            return 2;
        }else if(range41To60.containsInteger(number)){
            return 3;
        }else if(range61To80.containsInteger(number)){
            return 4;
        }else{
            return 5;
        }
    }


    public static void main(String[] args) {
        //<!-- https://mvnrepository.com/artifact/commons-lang/commons-lang -->
        IntRange range = new IntRange(0,20);

        System.out.println(" Est-ce que ce nombre appartient à cette intervalle d'entiers : "+brandNumberRange(81));


    }



}
