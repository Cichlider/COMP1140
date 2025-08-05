import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


/** ConsList<T> 表示 T 类型元素的列表，其形式为：
 * - Nil<T> 表示空列表；
 * - Cons<T> 表示一个元素 + 一个列表 */
sealed interface ConsList<T> permits Nil, Cons {}

/** Nil<T> 表示空列表 */
record Nil<T>() implements ConsList<T> {}

/** Cons<T> 表示一个元素和其余列表 */
record Cons<T>(T element, ConsList<T> rest) implements ConsList<T> {}

// return switch(listOfIntegers) {
//   case Nil<Integer>() -> ...;
//   case Cons<Integer>(var element, var rest) ->
//     ... element ... 递归调用(... rest ... ) ...;
// };

ConsList<Integer> newIntegerList(int a, ConsList<Integer> s) {
    return new Cons<>(a, s);
}

int len(ConsList<Integer> list){
    return switch(list){
        case Nil<Integer>() -> 0;
        case Cons<Integer>(var num , var rest) -> 1+len(rest);
    };
}

void main (){
    ConsList<Integer> list = new Cons<>(42,
            new Cons<>(6,
                new Nil<>()));
    println (newIntegerList(5,list));
}
