package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperMarket {
    private static SuperMarket ourInstance = new SuperMarket();
    private Map<String, Good> goods;
    private List<Order> orders;

    public static SuperMarket getInstance() {
        return ourInstance;
    }

    public Map<String, Good> getGoods() {
        return goods;
    }

    public List<Order> getOrders() {
        return orders;
    }

    private SuperMarket() {
        goods = new HashMap<>();
        orders = new ArrayList<>();
    }
}
