import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

int numLeapYearsSince(int year){
    int s=0;
    for (int i=year; i< 2025 ; i++){
        if(i%4==0){
            s+=1;
        }
    }
    return s;
}


int myleap(int year){
    return helper(year,2024);
}

int helper(int year , int x){
    if(year <= x){
        return 1+helper(year,x-4);
    }else{
        return 0 ;
    }
}

void main(){
    testEqual(numLeapYearsSince(1976), 13);
    testEqual(numLeapYearsSince(1989), 9);
    println(myleap(1976));
}
