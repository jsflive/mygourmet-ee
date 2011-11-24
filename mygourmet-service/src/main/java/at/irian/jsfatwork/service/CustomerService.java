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

    public Address createAddress() {
        return crudService.createNew(Address.class);
    }

    public void saveAddress(Customer customer, Address address) {
        if (address.isTransient()) {
            Customer mergedCustomer = crudService.merge(customer);
            mergedCustomer.addAddress(address);
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

    public Order createOrder() {
        return crudService.createNew(Order.class);
    }

    public void saveOrder(Customer customer, Order order) {
        order.setOrderDate(new Date());
        Customer mergedCustomer = crudService.merge(customer);
        order.setCustomer(mergedCustomer);
        mergedCustomer.getOrders().add(order);
        crudService.persist(order);
    }

}
