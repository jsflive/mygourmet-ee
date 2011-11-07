package at.irian.jsfatwork.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@SequenceGenerator(name = "ADDRESS_SEQUENCE_GENERATOR", sequenceName = "ADDRESS_SEQUENCE")
public class Address extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @NotNull
    @Column(name = "STREET")
    private String street;
    @NotNull
    @Column(name = "CITY")
    private String city;
    @NotNull
    @Min(value = 1000)
    @Max(value = 99999)
    @Column(name = "ZIP")
    private Integer zipCode;
    @NotNull
    @Column(name = "COUNTRY")
    private String country;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private Customer customer;

    public Address() {
		super();
	}

	public Address(Integer zipCode, String city, String street, String country) {
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.country = country;
	}

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
