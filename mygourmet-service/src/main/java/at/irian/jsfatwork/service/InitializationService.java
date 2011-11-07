package at.irian.jsfatwork.service;

import at.irian.jsfatwork.domain.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class InitializationService {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        initProviders();
    }

    private void initProviders() {
        Category catIt = new Category();
        catIt.setName("Italienisch");
        em.persist(catIt);
        Category catGr = new Category();
        catGr.setName("Griechisch");
        em.persist(catGr);
        Category catAt = new Category();
        catAt.setName("Hausmannskost");
        em.persist(catAt);
        Category catMx = new Category();
        catMx.setName("Mexikanisch");
        em.persist(catMx);
        Category catAsian = new Category();
        catAsian.setName("Asiatisch");
        em.persist(catAsian);

        // Initialize customer
        Customer customer = new Customer();
        customer.setFirstName("Max");
        customer.setLastName("Mustermann");
        customer.setEmail("max@mustermann.org");
        customer.setGender('m');
        Address address = new Address(1010, "Wien", "Stephansplatz 1", "Austria");
        customer.addAddress(address);
        address = new Address(1040, "Wien", "Karlsplatz 1", "Austria");
        customer.addAddress(address);
        customer.getPreferredCategories().add(catIt);
        em.persist(customer);

        // Initialize providers
        Provider provider = new Provider("Pizzeria Venezia");
        provider.addCategory(catIt);
        provider.getAddress().setZipCode(1010);
        provider.getAddress().setCity("Wien");
        provider.getAddress().setStreet("Rathausplatz 1");
        provider.getAddress().setCountry("Österreich");
        // Add dish 1
        Dish dish = new Dish();
        dish.setName("Pizza Salami");
        dish.setPrice(7.7);
        dish.setDescription("Tomaten, Käse, Salami");
        dish.setProvider(provider);
        provider.addDish(dish);
        // Add dish 2
        dish = new Dish();
        dish.setName("Pizza Frutti di Mare");
        dish.setPrice(8.8);
        dish.setDescription("Tomaten, Käse, Frutti di Mare");
        dish.setProvider(provider);
        provider.addDish(dish);
        em.persist(provider);
        // Add provider 2
        provider = new Provider("Restaurant Mykonos");
        provider.addCategory(catGr);
        provider.getAddress().setZipCode(1040);
        provider.getAddress().setCity("Wien");
        provider.getAddress().setStreet("Wiedner Hauptstraße 1");
        provider.getAddress().setCountry("Österreich");
        em.persist(provider);
        // Add provider 3
        provider = new Provider("Zur lustigen Wirtin");
        provider.addCategory(catAt);
        provider.addCategory(catIt);
        provider.getAddress().setZipCode(1010);
        provider.getAddress().setCity("Wien");
        provider.getAddress().setStreet("Stephansplatz 1");
        provider.getAddress().setCountry("Österreich");
        em.persist(provider);
    }

}
