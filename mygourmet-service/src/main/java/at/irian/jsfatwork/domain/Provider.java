package at.irian.jsfatwork.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "PROVIDER")
@SequenceGenerator(name = "PROVIDER_SEQUENCE_GENERATOR", sequenceName = "PROVIDER_SEQUENCE")
public class Provider extends AbstractBaseEntity {
    @NotNull
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVIDER_SEQUENCE_GENERATOR")
    private Long id;
    @NotNull
    @Column(name = "NAME")
    private String name;
    @Min(value = 0)
    @Column(name = "STARS")
    private Integer stars;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private Address address = new Address();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROVIDER_CATEGORY",
            joinColumns = @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
    private Set<Category> categories = new HashSet<Category>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<Dish>();

    public Provider() {
    }

    public Provider(String name) {
        this.name = name;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        dish.setProvider(this);
        this.dishes.add(dish);
    }

}
