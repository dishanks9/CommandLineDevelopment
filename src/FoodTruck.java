public class FoodTruck {


    private String truckName;

    private String address;

    public FoodTruck(){

    }

    public FoodTruck(String name, String add){
        truckName = name;
        address = add;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
