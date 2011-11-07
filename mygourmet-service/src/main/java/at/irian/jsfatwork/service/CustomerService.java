package at.irian.jsfatwork.service;

import at.irian.jsfatwork.dao.CrudService;
import at.irian.jsfatwork.domain.Address;
import at.irian.jsfatwork.domain.Customer;
import at.irian.jsfatwork.domain.Order;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CustomerService {
    @Inject
    private CrudService crudService;

    public Customer createNew() {
        return crudService.createNew(Customer.class);
    }

    public void save(Customer customer) {
        if (customer.isTransient()) {
            crudService.persist(customer);
        } else {
            crudService.merge(customer);
        }
    }

    public void delete(Customer customer) {
        customer = crudService.merge(customer);
        crudService.delete(customer);
    }

    public List<Customer> findAll() {
        return crudService.findAll(Customer.class);
    }

    public Customer findById(long id) {
        return crudService.findById(Customer.class, id);
    }

    public Address createAddress(Customer customer) {
        Address address = crudService.createNew(Address.class);
        customer.addAddress(address);
        return address;
    }

    public void saveAddress(Address address) {
        crudService.merge(address.getCustomer());
        if (address.isTransient()) {
            crudService.persist(address);
        } else {
            crudService.merge(address);
        }
    }

    public void deleteAddress(Address address) {
        Customer customer = address.getCustomer();
        customer.removeAddress(address);
        crudService.merge(customer);
        Address addressMerged = crudService.merge(address);
        crudService.delete(addressMerged);
    }

    public Order createOrder(Customer customer) {
        Order order = crudService.createNew(Order.class);
        customer.addOrder(order);
        return order;
    }

    public void saveOrder(Order order) {
        order.setOrderDate(new Date());
        crudService.merge(order.getCustomer());
        crudService.persist(order);
    }

}
