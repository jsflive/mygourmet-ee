package at.irian.jsfatwork.gui.page.config;

import at.irian.jsfatwork.gui.page.CustomerBean;
import at.irian.jsfatwork.gui.page.CustomerListBean;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PageBean;

@Page
@PageBean(CustomerBean.class)
public final class ShowCustomer implements ViewConfig {
}
