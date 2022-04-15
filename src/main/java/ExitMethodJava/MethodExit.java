package ExitMethodJava;

public class MethodExit {
    public static void main(String[] args) {
        int[] array = {0, 2, 4, 6, 8, 10};
        exampleMethod(array);
    }

    public static void exampleMethod(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i > 3) {
                System.exit(0);
            }
            System.out.println("Array Index: " + i);
        }
    }
}
