public class IntersectionTypes {
    public static void main(String[] args) {

    }

    // ?? Capture conversion ???
    void dummy(SuperC & I1 t ) {}
}

// TODO: revisit after learning about capture conversion
// This seems to imply that an intersection type could have more than one class, which is prevented by the
// rules for type bounds (only first type can be a class or type variable)

// 4.9
// For each Ti (1 ≤ i ≤ n), let Ci be the most specific class or array type such that Ti <: Ci.
// Then there must be some Ck such that Ck <: Ci for any i (1 ≤ i ≤ n), or a compile-time error occurs.


class SuperC { }

class SubC extends  Super {}

class UnrelatedC {}

interface I1 {}
