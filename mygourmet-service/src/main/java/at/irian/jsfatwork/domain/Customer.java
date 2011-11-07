package at.irian.jsfatwork.domain;

import at.irian.jsfatwork.validation.Birthday;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.*;

/*
 * customer profile
 */
@Entity
@Table(name = "CUSTOMER")
@SequenceGenerator(name = "CUSTOMER_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_SEQUENCE")
public class Customer extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @NotNull
    @Column(name = "FIRSTNAME")
	private String firstName;
    @NotNull
    @Column(name = "LASTNAME")
	private String lastName;
    @NotNull
    @Column(name = "EMAIL")
    private String email;
    @NotNull
    @Column(name = "GENDER")
	private Character gender;
    @Birthday
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
	private Date birthday;
    @Column(name = "USE_CREDIT_CARD")
	private Boolean useCreditCard = Boolean.FALSE;
    @NotNull
    @Column(name = "CC_TYPE")
    @Enumerated(EnumType.STRING)
	private CreditCardType creditCardType;
    @NotNull
    @Column(name = "CC_NUMBER")
	private String creditCardNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTOMER_CATEGORY",
            joinColumns = @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
	private Set<Category> preferredCategories = new HashSet<Category>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<Address>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addAddress(Address address) {
        address.setCustomer(this);
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
        address.setCustomer(null);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getUseCreditCard() {
		return useCreditCard;
	}

	public void setUseCreditCard(Boolean useCreditCard) {
		this.useCreditCard = useCreditCard;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

    public Set<Category> getPreferredCategories() {
        return preferredCategories;
    }

    public void setPreferredCategories(Set<Category> preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

    public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        order.setCustomer(this);
        this.orders.add(order);
    }
}
