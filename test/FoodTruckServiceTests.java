import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class FoodTruckServiceTests {

    Map<String, FoodTruck> foodMap = new TreeMap<>();
    TreeMap<String, FoodTruck> actualMap = new TreeMap<>();

    @Before
    public void setUp()  throws IOException, JSONException, ParseException {
        FoodTruckService service = new FoodTruckService();

        //Get the JSON data from the given URL
        JSONArray jsonArray = new JSONArray(service.getJSONData());


        foodMap.put("Leo's Hot Dogs", new FoodTruck("Leo's Hot Dogs", "2301 MISSION ST"));
        foodMap.put("San Francisco Carts & Concessions, Inc. DBA Stanley's Steamers Hot Dogs", new FoodTruck("San Francisco Carts & Concessions, Inc. DBA Stanley's Steamers Hot Dogs","345 STOCKTON ST"));
        foodMap.put("Julie's Hot Dogs",new FoodTruck("Julie's Hot Dogs","2386 MISSION ST"));



        LocalTime user = LocalTime.parse("23:30");

        //Calendar calendar = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        Date d = dateFormat.parse(String.valueOf(user));

        actualMap = service.filterData(jsonArray, actualMap, d, "Wednesday");
    }

    @Test
    public void testApi(){
        Assert.assertEquals(foodMap.keySet(), actualMap.keySet());
        //Assert.assertEquals(1,1);
    }

    @Test
    public void hi(){
        Assert.assertEquals(1,1);
    }

}
