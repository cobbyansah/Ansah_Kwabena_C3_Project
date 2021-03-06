import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    @Spy
    Restaurant spyRestaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        spyRestaurant = Mockito.spy(restaurant);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        /**
         * LocalTime betweenOpeningAndClosingTime = LocalTime.parse("10:50:01");
         * // InjectMocks
         * assertTrue(restaurant.isRestaurantOpen(betweenOpeningAndClosingTime));
         */

        LocalTime betweenOpeningAndClosingTime = LocalTime.parse("10:50:01");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(betweenOpeningAndClosingTime);
        assertTrue(spyRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime outsideOpeningAndClosingTime = LocalTime.parse("10:01:01");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(outsideOpeningAndClosingTime);
        assertFalse(spyRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //------- Part 3: Solution -------
    /**
     * -> TODO
     *
     *  Method to return total order cost
     *
     *  Takes list of order names (String) as parameter
     *  Find each corresponding order cost and add to sum
     *  return double: sum - cost of all order items
    */

    @Test
    public void get_order_cost_should_return_sum_of_prices_of_all_selected_items(){
        List<String> order = new ArrayList<>();
        order.add("Sweet corn soup");
        order.add("Vegetable lasagne");

        int orderCost = restaurant.getOrderCost(order);
        assertNotNull(orderCost);
        assertThat(orderCost, greaterThan(0));
        assertEquals(388, orderCost);
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}