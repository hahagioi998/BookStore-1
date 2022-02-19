package com.levy.domain;

/**
 * @author LevyXie
 * @create 2022-01-16 23:35
 * @description
 */
public class CartItem {
    private Integer id;
    private String name;
    private Integer count;
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return count * price;
    }

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, double price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
