package at.irian.jsfatwork.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ORDER_ITEM")
@SequenceGenerator(name = "ORDER_ITEM_SEQUENCE_GENERATOR", sequenceName = "ORDER_ITEM_SEQUENCE")
public class OrderItem extends AbstractBaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQUENCE_GENERATOR")
    private Long id;

    @Column(name = "AMOUNT")
    @Min(1)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "DISH_ID", referencedColumnName = "ID")
    private Dish dish;

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
