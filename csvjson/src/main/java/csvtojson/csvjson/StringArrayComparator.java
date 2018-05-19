package csvtojson.csvjson;

import java.util.Comparator;

public class StringArrayComparator implements Comparator<String[]>{

    Integer sortNum;

    public StringArrayComparator(Integer index) {  //cons
           sortNum = index;
    }

    @Override
    public int compare(String[] strings, String[] otherStrings) {
           return 1*(strings[sortNum].compareTo(otherStrings[sortNum]));
    }
}