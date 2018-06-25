import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FoodTruckService {

    //Return the JSON data to the user from the given url
    public String getJSONData() throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader rd = null;
        try {

            URL url = new URL("http://data.sfgov.org/resource/bbb8-hzi6.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            rd.close();
        }
        return result.toString();
    }

    //Get the current time in the desired format to compare
    public Date getCurrentTime() throws ParseException {

        Calendar calendar = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        String d = dateFormat.format(calendar.getTime());

        return dateFormat.parse(d);
    }

    //Get the current day in the desired format to compare
    public String getCurrentDay(){
        String days = new SimpleDateFormat("EEEE").format(new Date());
        return days;
    }

    //Filter the output based on given conditions
    public TreeMap<String, FoodTruck> filterData(JSONArray jsonArray, TreeMap<String, FoodTruck> map,
                                            Date currentTime, String days)
            throws JSONException, ParseException {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        for(int i=0;i<jsonArray.length();i++){
            JSONObject object = (JSONObject) jsonArray.get(i);

            String day = object.getString("dayofweekstr");
            Date endTime = dateFormat.parse(object.getString("end24"));
            Date startTime = dateFormat.parse(object.getString("start24"));
            long startAfter = currentTime.getTime() - startTime.getTime();
            long endBefore = endTime.getTime() - currentTime.getTime();
            if(startAfter>=0 && endBefore>=0 && days.equalsIgnoreCase(day)){
                FoodTruck fT = new FoodTruck();
                fT.setTruckName(object.getString("applicant"));
                fT.setAddress(object.getString("location"));
                map.put(fT.getTruckName(),fT);

            }
        }

        return map;
    }

    //Sort the filtered ouput and return it in the limited value batch
    public List<List<FoodTruck>> splitValues(Map<String, FoodTruck> truckMap, List<List<FoodTruck>> foodTruckList, int limit){
        for(Map.Entry<String, FoodTruck> food: truckMap.entrySet()){
            if(foodTruckList.size()==0 || foodTruckList.get(foodTruckList.size()-1).size()>=limit){
                List<FoodTruck> foodTruck = new ArrayList<>();
                foodTruck.add(food.getValue());
                foodTruckList.add(foodTruck);
            }
            else if(foodTruckList.get(foodTruckList.size()-1).size()<limit) {
                foodTruckList.get(foodTruckList.size() - 1).add(food.getValue());
            }
        }
        return foodTruckList;
    }

    //Print the details of food trucks
    public void printFoodTruckDetails(List<List<FoodTruck>> foodTruckList, int index, String specifiers){
        for(FoodTruck food: foodTruckList.get(index-1)){
            System.out.format(specifiers,food.getTruckName(),food.getAddress());
        }
    }
}
