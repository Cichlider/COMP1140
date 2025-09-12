import comp1110.lib.*;
import static comp1110.lib.Functions.*;

void printCountryCapital(String country, Maybe<String> capital){
    switch(capital) {
       case Nothing() -> println("Unknown capital for country " + country);
       case Something(var value) -> println("Capital of " + country + " is " + value);
    };
}

void addFrance(Map<String,String> countryToCapital)
{
    Put(countryToCapital, "France", "Paris");
}

void main()
{
   Pair<String,String> p1 = new Pair<String,String>("Australia", "Canberra");
   Pair<String,String> p2 = new Pair<String,String>("Spain", "Madrid");
   var countryToCapitalMap = MakeHashMap(p1,p2);
   printCountryCapital("Australia", Get(countryToCapitalMap,"Australia"));
   printCountryCapital("Spain", Get(countryToCapitalMap,"Spain"));
   printCountryCapital("France", Get(countryToCapitalMap,"France"));
   addFrance(countryToCapitalMap);
   printCountryCapital("France", Get(countryToCapitalMap,"France"));
}
