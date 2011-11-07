package at.irian.jsfatwork.gui.page.config;

import at.irian.jsfatwork.gui.page.AddCustomerBean;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PageBean;

@PageBean(AddCustomerBean.class)
@Page(basePath = "")
public interface AddCustomerWizard extends ViewConfig {

    @Page
    public final class AddCustomer1 implements AddCustomerWizard {
    }

    @Page
    public final class AddCustomer2 implements AddCustomerWizard {
    }

}
