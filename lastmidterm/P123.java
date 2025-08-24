import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * 这是第一题
 * please check ws3a exercise "ConcatenateStrings"
 * 可以去查看一下 ws3a的练习题 “ConcatenateStrings”
 * totalLength() Give you a ConsList<String> strings
 * you need return sum of all strings
 */
String totalLength(ConsList<String>lst){
    return switch(lst){
        case Nil<String>() -> "";
        case Cons<String> (var elem,var rest) -> elem + totalLength(rest);
    };
}


/**
 * 这是第二题
 * Average() Give you a ConsList<Integer> numbers.
 * you need return average of all numbers (return floattype)
 */
int sum(ConsList<Integer>list){
    return switch(list){
        case Nil<Integer>()-> 0;
        case Cons<Integer>(var elem,var rest) -> elem+sum(rest);
    };
}

float Average(ConsList<Integer>list){
    int value = sum(list);
    int len = Length(list);
    return((float)value/(float)len);
}
/**
 * 这是第三题
 * AllPair() Give two ConsList<lnteger>, 
 * return all Pairof these two lists 
 * (follow lexicographical order,means need to sort)
 * Example：
 *  -Given [11,5,7] ,[2,8]
 *      -Return [(5,2),(5,8),(7,2),(7,8),(11,2),(11,8)]
 */
ConsList<Pair<Integer,Integer>> combine(ConsList<Integer> list1, ConsList<Integer> list2) {
    return switch(list1) {
        case Nil<Integer>() -> new Nil<Pair<Integer,Integer>>();
        case Cons<Integer>(var elem, var rest) -> 
            Append(
                Map(x -> new Pair<>(elem, x), list2),
                combine(rest, list2)
            );
    };
}

ConsList<Pair<Integer,Integer>> AllPair(ConsList<Integer> list1, ConsList<Integer> list2){
    ConsList<Integer> list3 = Sort(list1);
    ConsList<Integer> list4 = Sort(list2);

    return combine(list3,list4);
}

void main() {
    // 测试第一题：totalLength
    ConsList<String> strings = MakeList("hello", "world", "java");
    String result1 = totalLength(strings);
    println(result1);

    // 测试第二题：Average
    ConsList<Integer> numbers = MakeList(2, 4, 6);
    float result2 = Average(numbers);
    println(result2);

    // 测试第三题：AllPair
    ConsList<Integer> list1 = MakeList(5, 11, 7);
    ConsList<Integer> list2 = MakeList(8, 2);
    ConsList<Pair<Integer,Integer>> result3 = AllPair(list1, list2);
    // 应该返回 [(5,2), (5,8), (7,2), (7,8), (11,2), (11,8)]
    println(result3);
}