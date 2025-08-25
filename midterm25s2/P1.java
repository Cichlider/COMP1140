import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * 题目1：查找最短字符串
 * 
 * 题目描述：
 * 编写一个函数 findShortest，接收一个字符串列表 ConsList<String>，返回列表中长度最短的字符串。
 * 
 * 函数签名：
 * String findShortest(ConsList<String> lst)
 * 
 * 测试用例：
 * - 输入：["abc", "erft", "we"] → 输出："we"
 * - 输入：[""] → 输出：""
 */

String findShortest(ConsList<String> lst){
    return helper(lst,First(lst));
}

String helper(ConsList<String> lst, String str){
    return switch(lst){
        case Nil<String>() -> str ;
        case Cons<String> ( var elem,var rest) -> 
            (Length(elem)<Length(str)) ? helper(rest,elem) : helper(rest,str);
    };
}

void main(){
    ConsList<String> a =MakeList("abc","erft","we");
    ConsList<String> b =MakeList("");
    println(findShortest(a));
    println(findShortest(b));
}

