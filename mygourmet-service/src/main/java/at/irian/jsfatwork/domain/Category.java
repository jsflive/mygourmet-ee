package at.irian.jsfatwork.domain;

import javax.persistence.*;

@Entity
@Table(name = "FOOD_CATEGORY")
@SequenceGenerator(name = "FOOD_CAT_SEQUENCE_GENERATOR", sequenceName = "FOOD_CATEGORY_SEQUENCE")
public class Category extends AbstractBaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOOD_CAT_SEQUENCE_GENERATOR")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
