package csvtojson.csvjson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opencsv.CSVReader;



public class App {

    public static void main(String[] args) {

        String csvFile ="C:/Users/ddas109/Documents/CSVTOJASON_Code/modified_cold_storage_sample.csv";
        String jsonfile="C:/Users/ddas109/Documents/CSVTOJASON_Code/output.json";

        CSVReader reader = null;
        try {
        	reader = new CSVReader(new FileReader(csvFile));
            List<String[]> all= reader.readAll();
            all=all.subList(1, all.size());
            
            Collections.sort(all, new StringArrayComparator(11));  //call from here
            JSONObject jsonobj = new JSONObject();
            JSONArray customer_id = new JSONArray();
            JSONArray customer_location = new JSONArray();
            File file = new File(jsonfile);
            file.createNewFile();
            FileWriter writer = new FileWriter(file); 
            String ds_prev=null;
            int i=0;
                    while(i < all.size()) {     
                    //	System.out.println(all.get(i)[11]);
                    	if(all.get(i)[11].equals(ds_prev)||i==0)
                    	{
                    		ds_prev=all.get(i)[11];
                    		customer_id.add(all.get(i)[0]);
                        	customer_location.add(all.get(i)[1]);
                        //	System.out.println("\npush " + i + customer_id);
                        //	System.out.println("\npush " + i + customer_location);
                        }
                    	else
                    	{
                    		jsonobj.put("device_severity", ds_prev);
                    		jsonobj.put("customer_id", customer_id);
                        	jsonobj.put("customer_location", customer_location);
                    		writer.write(jsonobj.toJSONString());
                    		//System.out.println("\nwrite " + i + jsonobj);
                    		customer_id.clear();
                    		customer_location.clear();
                    		customer_id.add(all.get(i)[0]);
                        	customer_location.add(all.get(i)[1]);
                    	}
                    	
                    	ds_prev=all.get(i)[11];
                    	i++;
                    }
                    jsonobj.put("device_severity", ds_prev);
            		jsonobj.put("customer_id", customer_id);
                	jsonobj.put("customer_location", customer_location);
            		writer.write(jsonobj.toJSONString());
            	//	System.out.println("\nwrite " + i + jsonobj);
                    writer.flush();
                    writer.close();
                    System.out.println("Successfully Written all the json object...");
                } catch (IOException e) {
                    e.printStackTrace();
                  	
                        
        }


    }

}
