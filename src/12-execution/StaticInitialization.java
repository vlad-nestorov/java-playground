public class StaticInitialization {
    public static void main(String[] args) {
        // Static initializers not invoked unless the type is directly or indirectly referenced.

        // Uncomment the following invocations to check the different effects

        // nullField();
        // superOnly();
        // subOnly();
        // fieldAndBlockOrder();
    }

    // Declaring a variable does not load the class if variable is always null
    static void nullField() {
        Super r = null;
        System.out.println("Class not loaded because always " + r);
    }

    // Subclasses are not initialized
    // Static block runs before method.
    static void superOnly() {
        Super.dummyMethod();
    }

    // Super class initialized before sub class
    static void subOnly() {
        Sub.dummyMethod();
    }

    // field initializers run before the static block, so i = 0 in the first call to printFields()
    // Statements in the static initialization block are executed in order.
    static void fieldAndBlockOrder() {
        SubWithFields.printFields();
    }
}

class Super {
    static void dummyMethod() {
        System.out.println("Super dummy method");
    }

    static {
        System.out.println("Super static block");
    }
}

class Sub extends Super {
    static void dummyMethod() {
        System.out.println("Sub dummy method");
    }

    static {
        System.out.println("Sub static block");
    }
}

class SubWithFields extends Super {
    static int i;
    static int ii = 2;

    static void printFields() {
        System.out.println("SubWithFields printFields i = " + i + " ii = " + ii);
    }

    static {
        System.out.println("SubWithFields static block start");
        printFields();
        i = 4;
        System.out.println("SubWithFields static block end");
    }
}