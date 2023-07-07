package com.example.project_book.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItemById(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getIdProduct() == id) {
                return items.get(i);
            }
        }
        return null;
    }

    public int getAmountById(int id) {
        return getItemById(id).getAmount();
    }

    public void addItem(Item item) {
        if (getItemById(item.getProduct().getIdProduct()) != null) {
            Item m = getItemById(item.getProduct().getIdProduct());
            m.setAmount(m.getAmount() + item.getAmount());
        } else {
            items.add(item);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (Item i: items) {
            total+=(i.getAmount()*i.getProduct().getPriceBook());
        }
        return total;
    }

    public void clearCart(){
        items = new ArrayList<>();
    }
}
