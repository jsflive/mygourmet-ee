package at.irian.jsfatwork.gui.page.config;

import at.irian.jsfatwork.gui.page.CustomerBean;
import at.irian.jsfatwork.gui.page.CustomerListBean;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PageBean;

/**
 *
 */
@SuppressWarnings({"CdiManagedBeanInconsistencyInspection"})
@Page(basePath = "")
public interface Pages extends ViewConfig {

    @Page
    @PageBean(CustomerListBean.class)
    public final class CustomerList implements Pages {}

    @Page
    @PageBean(CustomerBean.class)
    public final class ShowCustomer implements Pages {}

    @Page
    public final class ProviderList implements Pages {}

    @Page
    public final class ShowProvider implements Pages {}

    @Page
    public final class EditProvider implements Pages {}

    @Page
    public final class EditDish implements Pages {}

    @Page
    public final class EditAddress implements Pages {}

    @Page
    public final class OrderProvider implements Pages {}

    @Page
    public final class OrderDishes implements Pages {}

    @Page
    public final class OrderFinish implements Pages {}

}
