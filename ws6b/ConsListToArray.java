import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

int[] consListToArray(ConsList<Integer> lst){
    int[] s = new int [Length(lst)];
    for (int i=0; i<Length(lst); i++){
        s[i] = Nth(lst,i);
    }

    return s;
}

void print(int[] arr){
    String s ="";


    s+="[";

    for (int i = 0 ; i < arr.length; i++){
        s += (ToString(arr[i]));
        s += " ";
    }

    s = Trim(s);
    s+="]";

    println(s);
}

void main(){
    ConsList<Integer> s =MakeList(1,2,3);
    print(consListToArray(s));
}