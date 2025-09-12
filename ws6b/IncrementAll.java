import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;


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

void incrementAll(int[] arr){
    for (int i = 0; i < arr.length; i++){
        arr[i] += 1;
    }
}

void main()
{
   int [] numbers = new int[3];
   numbers[0]=1;
   numbers[1]=2;
   numbers[2]=3;
   print(numbers);
   incrementAll(numbers);
   testEqual(2, numbers[0]);
   testEqual(3, numbers[1]);
   testEqual(4, numbers[2]);
   print(numbers);

}
