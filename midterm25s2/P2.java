import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * 题目2：整数到布尔值的Maybe转换
 * 
 * 题目描述：
 * 编写一个函数 returnList，接收一个整数列表 ConsList<Integer>，将列表中的每个整数按以下规则转换为 Maybe<Boolean> 类型：
 * - 1 转换为 Something(true)
 * - 0 转换为 Something(false)
 * - 其他数字转换为 Nothing()
 * 
 * 函数签名：
 * ConsList<Maybe<Boolean>> returnList(ConsList<Integer> lst)
 * Maybe<Boolean> helper(int a)
 * 
 * 测试用例：
 * - 输入：[1, 2, 3, 0, -1, 2, 3]
 * - 输出：[Something(true), Nothing(), Nothing(), Something(false), Nothing(), Nothing(), Nothing()]
 */

ConsList<Maybe<Boolean>> returnList(ConsList<Integer>lst){
    return Map(x -> helper(x),lst);
}

Maybe<Boolean> helper(int a){
    if (Equals(a,1)){
        return new Something<Boolean>(true);
    }else if (Equals(a,0)){
        return new Something<Boolean>(false);
    }else{
        return new Nothing<Boolean>();
    }
}

void main(){
    ConsList<Integer> a =MakeList(1,2,3,0,-1,2,3);
    println(returnList(a));
}