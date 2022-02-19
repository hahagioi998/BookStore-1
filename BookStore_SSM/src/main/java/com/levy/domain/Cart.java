package com.levy.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LevyXie
 * @create 2022-01-16 23:41
 * @description
 */
public class Cart {
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public double getTotalCount() {
        int totalCount = 0;
        for(CartItem i : items.values()){
            totalCount += i.getCount();
        }
        return totalCount;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(CartItem i : items.values()){
            totalPrice += i.getCount() * i.getPrice();
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount( item.getCount() + cartItem.getCount());
        }
    }
    public void deleteItem(Integer id){
        items.remove(id);

    }
    public void clear(){
        items.clear();
    }
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem != null) {
            cartItem.setCount(count);
        }
    }

    
}
