import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    /**
     *
     * This method returns true if restaurant is open and false if restaurant is closed
     *
     * @return boolean indicating status of restaurant
     */
    public boolean isRestaurantOpen() {
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        LocalTime currentTime = getCurrentTime();

        return currentTime.isAfter(openingTime)
                && currentTime.isBefore(closingTime);
    }

    /**
     public boolean isRestaurantOpen(LocalTime currentTime) {
     return currentTime.isAfter(openingTime)
     && currentTime.isBefore(closingTime);
     }
     */


    public LocalTime getCurrentTime(){
        return  LocalTime.now();
    }


    /**
     * This method returns menu of the restaurant found
     *
     * @return List<Item>: menu
     */
    public List<Item> getMenu() {
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }


    /**
     * This method returns the order value, given the name of the items in <String> format
     *
     * @return int: totalOrderCost - total cost of order
     */
    public int getOrderCost(List<String> selectedItems){
        int totalOrderCost = 0;
        for(String orderItem: selectedItems) {
            totalOrderCost += findItemByName(orderItem).getPrice();
        }
        return totalOrderCost;
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
