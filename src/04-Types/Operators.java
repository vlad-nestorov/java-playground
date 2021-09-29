public class Operators {
    public static void main(String[] args) {
        // Arithmetic operators can cause NullPointerException when working with boxed types.
        Integer i = null;
        i--;
    }
}
