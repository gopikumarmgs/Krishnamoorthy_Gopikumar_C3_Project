import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach()
    public void beforeEachTest() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("12:00:00");
        restaurant =new Restaurant("Gopi's cafe","Chennai",openingTime,closingTime);
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void calculate_total_oder_value_method_should_return_zero_if_user_not_select_any_item(){
        //WRITE UNIT TEST CASE HERE
        List<String>  selectedItem = new ArrayList<String>(2);
        assertEquals("0",restaurant.calculateTotalOrderValue(selectedItem));
    }

    @Test
    public void calculate_total_oder_value_method_should_return_119_if_user_select_sweet_corn_soup(){
        //WRITE UNIT TEST CASE HERE
        List<String>  selectedItem = new ArrayList<String>(2);
        selectedItem.add("Sweet corn soup");
        assertEquals("119",restaurant.calculateTotalOrderValue(selectedItem));
    }

    @Test
    public void calculate_total_oder_value_method_should_return_388_if_user_select_sweet_corn_soup_and_vegetable_lasagne(){
        //WRITE UNIT TEST CASE HERE
        List<String>  selectedItem = new ArrayList<String>(2);
        selectedItem.add("Sweet corn soup");
        selectedItem.add("Vegetable lasagne");
        assertEquals("388",restaurant.calculateTotalOrderValue(selectedItem));
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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