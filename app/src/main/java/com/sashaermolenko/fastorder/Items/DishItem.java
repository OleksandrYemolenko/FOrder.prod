package com.sashaermolenko.fastorder.Items;

public class DishItem extends Item {
    private String price;
    private String description;
    private boolean showFullName = false;

    public DishItem(String title, String imgURL, int id, String price, String description) {
        super(title, imgURL, id);
        this.price = price;
        this.description = description;
    }

    @Override
    public String getName() {
        return showFullName == true ? super.getName() : super.getName().substring(0, Math.min(9, super.getName().length())) + "...";
    }

    public void setVisOfFullName(boolean showFullName) {
        this.showFullName = showFullName;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean getVisOfFullName() {
        return showFullName;
    }
}
