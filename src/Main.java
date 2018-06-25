import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        try {

            //Create the food truck service object
            FoodTruckService service = new FoodTruckService();

            //Get the JSON data from the given URL
            JSONArray jsonArray = new JSONArray(service.getJSONData());

            //Initializers
            System.out.println("The list of food trucks that are open now in San Francisco");
            System.out.println("----------------------------------------------------------------------------");
            String specifiers = "%-80s %-30s %n";
            System.out.format(specifiers, "NAME", "ADDRESS");


            //Data storage for sorting and storing the values to be displayed
            List<List<FoodTruck>> foodTruckList = new ArrayList<>();
            TreeMap<String, FoodTruck> map = new TreeMap<>();
            final int limit = 10;

            //Filter data based on given condition
            map = service.filterData(jsonArray, map, service.getCurrentTime(), service.getCurrentDay());

            //Sort the data based on given limit
            foodTruckList = service.splitValues(map, foodTruckList, limit);

            //If there are less than 10 values just display one page
            if(foodTruckList.size()==1){
                service.printFoodTruckDetails(foodTruckList,1,specifiers);
            }

            //if more than 10 values display based on user request
            else if(foodTruckList.size()>1){

                //Print the default first page
                service.printFoodTruckDetails(foodTruckList,1,specifiers);
                int option = 0;
                int currentPage = 1;
                System.out.println("");
                System.out.println("Current Page: "+currentPage);

                //Run till user says to exit
                do{

                    //Get the input from user for the page number
                    System.out.println("");
                    System.out.println("1. Next page");
                    System.out.println("2. Previous page");
                    System.out.println("3. Exit");
                    Scanner sc = new Scanner(System.in);
                    option = sc.nextInt();

                    //Go to the next page
                    if(option==1){
                        if(currentPage<foodTruckList.size()){
                            currentPage++;
                        }
                        if(currentPage==foodTruckList.size()){
                            System.out.println("");
                            System.out.println("This is the last page");
                            System.out.println("");
                        }

                        System.out.format(specifiers, "NAME", "ADDRESS");
                        service.printFoodTruckDetails(foodTruckList,currentPage,specifiers);
                        System.out.println("");
                        System.out.println("Current Page: "+currentPage);
                    }

                    //Go to the previous page
                    else if(option==2){
                        if(currentPage>1){
                            currentPage--;
                        }
                        if(currentPage==1){
                            System.out.println("");
                            System.out.println("This is the first page");
                            System.out.println("");
                        }

                        System.out.format(specifiers, "NAME", "ADDRESS");
                        service.printFoodTruckDetails(foodTruckList,currentPage,specifiers);
                        System.out.println("");
                        System.out.println("Current Page: "+currentPage);
                    }

                    //If page number is wrong alert it to the user
                    else if(option<1 || option>3){
                        System.out.println("Please select the correct option.");
                    }
                }while(option!=3);
            }

            //When no food trucks are open show the message to user
            else{
                System.out.println("Sorry! No food trucks are open at this time.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
