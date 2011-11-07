package at.irian.jsfatwork.service;

import at.irian.jsfatwork.dao.CrudService;
import at.irian.jsfatwork.domain.Dish;
import at.irian.jsfatwork.domain.Order;
import at.irian.jsfatwork.domain.OrderItem;
import at.irian.jsfatwork.domain.Provider;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProviderService {

    @Inject
    private CrudService crudService;

    public Provider createNew() {
        return crudService.createNew(Provider.class);
    }

    public void save(Provider entity) {
        if (entity.isTransient()) {
            crudService.persist(entity);
        } else {
            crudService.merge(entity);
        }
    }

    public void delete(Provider provider) {
        provider = crudService.merge(provider);
        Set<Order> orders = new HashSet<Order>();
        for (Dish dish : provider.getDishes()) {
            for (OrderItem item : dish.getOrderItems()) {
                orders.add(item.getOrder());
            }
        }
        for (Order order : orders) {
            order.setCustomer(null);
            crudService.delete(order);
        }
        provider.getCategories().clear();
        crudService.delete(provider);
    }

    public Provider findById(long id) {
        return crudService.findById(Provider.class, id);
    }

    public List<Provider> findAll() {
        return crudService.findAll(Provider.class);
    }

}
