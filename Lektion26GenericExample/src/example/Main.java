package example;

import java.util.*;

import model.Food;
import model.Product;

public class Main {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("Hej");
        list.add(123);
        list.add(new Product(123, "Produkt"));
        
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1500, "Fjernsyn"));
        
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food(200, "Pizza"));
        
        //print(list);
        print(productList);
        print(foodList);
        
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("Hej");
    }
    
    public static <T extends Product> void print(List<T> list) {
        for (T o : list) {
            System.out.println(o.getName());
        }
    }
    
    public static void print2(List<? super Product> list) {
        
    }
    
}
