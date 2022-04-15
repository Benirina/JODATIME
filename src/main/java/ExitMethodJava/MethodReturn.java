package ExitMethodJava;

public class MethodReturn {
    public static void main(String[] args) {

        exampleMethod(2, 5);
        exampleMethod(3, 2);
        exampleMethod(100, 20);
        exampleMethod(102, 110);
    }

    public static void exampleMethod(int num1, int num2) {

        if (num2 > num1)
            return;

        int subtractedValue = num1 - num2;
        System.out.println(subtractedValue);


    }
}
