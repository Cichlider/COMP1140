import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;


ConsList<Integer> arrayToConsList(int[] arr){
    ConsList<Integer> s = new Nil<Integer>();

    for (int i=arr.length-1; i>=0; i--){
        s = new Cons<Integer>(arr[i] , s);
    }

    return s;
}

void main(){
   int [] numbers = new int[3];
   numbers[0]=1;
   numbers[1]=2;
   numbers[2]=3;

   println(arrayToConsList(numbers));

}