package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.Dish;

import java.io.Serializable;

public class SelectableDish implements Serializable {
    private boolean selected;
    private Dish dish;
    private Integer amount;

    public SelectableDish(Dish dish) {
        this.dish = dish;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
