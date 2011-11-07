package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.Address;
import at.irian.jsfatwork.gui.page.config.ShowCustomer;
import at.irian.jsfatwork.service.CustomerService;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewAccessScoped
public class CustomerBean extends CustomerBeanBase {

    @Inject
    private CustomerService customerService;

    private boolean collapsed = false;
    private Address address;

    public Class<? extends ViewConfig> showCustomer(long id) {
        this.customer = customerService.findById(id);
        return ShowCustomer.class;
    }

	public String saveCustomer() {
        customerService.save(customer);
		return ViewIds.SHOW_CUSTOMER;
	}

	public String createAddress() {
        this.address = customerService.createAddress(customer);
        return ViewIds.EDIT_ADDRESS;
	}

    public String editAddress(Address address) {
        this.address = address;
        return ViewIds.EDIT_ADDRESS;
    }

    public String saveAddress() {
        customerService.saveAddress(address);
        this.customer = customerService.findById(this.customer.getId());
        this.address = null;
        return ViewIds.SHOW_CUSTOMER;
    }

    public String deleteAddress(Address address) {
        customerService.deleteAddress(address);
        return null;
    }

    public String cancelAddress() {
        this.address = null;
        return ViewIds.SHOW_CUSTOMER;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public Address getAddress() {
        return address;
    }

}
