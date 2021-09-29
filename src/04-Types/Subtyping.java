import java.util.ArrayList;
import java.util.Collection;

public class Subtyping {



    public static void main(String[] args) {
        // If S and T are both reference types, then S[] >1 T[] iff S >1 T.
        Sub[] subArray = {new Sub()};
        Super[] array = new Sub[]{};
        array = subArray;
        // Best guess: Arrays don't suffer from type erasure, so it can check at runtime whether an object is of the right type
        // this throws an ArrayStoreException
        // array[0] = new Super();

        // This is fine:
        array = new Super[]{new Sub()};



        // Yet it doesn't work with generic collections
        // This is because an ArrayList<Sub> should only contain items that are Sub or a subclass of Sub
        // if we could refer to ArrayList<Sub> by an ArrayList<Super> reference, we could insert a Super in the list.
        // ArrayList<Super> collection = new ArrayList<Sub>();

        // We can use a wildcard type to get around this.
        // WARNING: ? extends X should not be used to insert elements/return results, but to read them
        //          Returning or inserting a value should use a lower bound instead (
        ArrayList<? extends Super> upperBound = new ArrayList<Sub>();
        //upperBound.add(new Super());
        Super s = upperBound.get(0);

        ArrayList<? super Sub> lowerBound = new ArrayList<Super>();
        lowerBound.add(new Sub());
        //Sub sub = lowerBound.get(0);
        //Super super1 = lowerBound.get(0);



    }

    // Again, related to captures
    void testUpperBoundParameter() {
        SimpleCollection<? extends Super> ubc = new SimpleCollection<>();
        ubc.set(new Object());
        ubc.set(new Sub());
        ubc.set(new Super());
        Sub sub = ubc.get();
        Super s = ubc.get();
    }

    void testLowerBoundParameter() {
        SimpleCollection<? super Sub> ubc = new SimpleCollection<>();
        ubc.set(new Sub());
        ubc.set(new Super());
        Sub sub = ubc.get();
        Super s = ubc.get();
        Object o = ubc.get();
    }

}


// Wildcard type arguments not allowed in extends for class/interface declarations
//abstract class GenericInheritance implements Collection<?> {}
// You instead need to make your type generic:
abstract class GenericInheritance<T> implements Collection<T> { }


// Final variables don't need to be initialized in-line, but it must be guaranteed they are only set once.
class FinalVariable {
    final Object variable;

    FinalVariable() {
        variable = new Object();
    }

    FinalVariable(Object object) {
        this();
    }
}


class SimpleCollection<T> {
    T t;

    void set(T value) {
        t = value;
    }

    T get() {
        return t;
    }
}