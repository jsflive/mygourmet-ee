package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.Provider;
import at.irian.jsfatwork.service.ProviderService;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ProviderListBean implements Serializable {

    private List<Provider> providerList;
    @Inject
    private ProviderService providerService;

    public void preRenderView() {
        providerList = providerService.findAll();
    }

    public List<Provider> getProviderList() {
        return providerList;
    }

    public String deleteProvider(Provider provider) {
        providerService.delete(provider);
        return null;
    }

}
