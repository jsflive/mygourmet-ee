package at.irian.jsfatwork.service;

import at.irian.jsfatwork.dao.CrudService;
import at.irian.jsfatwork.domain.Dish;
import at.irian.jsfatwork.domain.Provider;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DishService {

    @Inject
    private CrudService crudService;

    public Dish createNew() {
        return crudService.createNew(Dish.class);
    }

    public Dish createNew(Provider provider) {
        Dish dish = createNew();
        provider.addDish(dish);
        return dish;
    }

    public void save(Dish dish) {
        crudService.merge(dish.getProvider());
        if (dish.isTransient()) {
            crudService.persist(dish);
        } else {
            crudService.merge(dish);
        }
    }

    public void delete(Dish dish) {
        Provider provider = dish.getProvider();
        if (provider != null) {
            provider.getDishes().remove(dish);
        }
        crudService.merge(provider);
        dish = crudService.merge(dish);
        crudService.delete(dish);
    }

    public List<Dish> findAll() {
        return crudService.findAll(Dish.class);
    }

    public Dish findById(long id) {
        return crudService.findById(Dish.class, id);
    }

}
