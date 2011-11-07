package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.Dish;
import at.irian.jsfatwork.domain.Provider;
import at.irian.jsfatwork.gui.page.config.Pages;
import at.irian.jsfatwork.service.DishService;
import at.irian.jsfatwork.service.ProviderService;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewAccessScoped
public class ProviderBean implements Serializable {

    @Inject
    private ProviderService providerService;
    @Inject
    private DishService dishService;

    private Provider provider;
    private Dish dish;

    public Provider getProvider() {
        return provider;
    }

    public Class<? extends ViewConfig> createProvider() {
        this.provider = providerService.createNew();
        return Pages.EditProvider.class;
    }

    public Class<? extends ViewConfig> showProvider(long id) {
        this.provider = providerService.findById(id);
        return Pages.ShowProvider.class;
    }

    public Class<? extends ViewConfig> cancel() {
        if (provider.isTransient()) {
            return Pages.ProviderList.class;
        } else {
            return Pages.ShowProvider.class;
        }
    }

    public Dish getDish() {
        return dish;
    }

    public Class<? extends ViewConfig> createDish() {
        this.dish = dishService.createNew(provider);
        return Pages.EditDish.class;
    }

    public Class<? extends ViewConfig> editDish(Dish dish) {
        this.dish = dish;
        return Pages.EditDish.class;
    }

    public Class<? extends ViewConfig> saveDish() {
        dishService.save(dish);
        this.provider = providerService.findById(this.provider.getId());
        this.dish = null;
        return Pages.ShowProvider.class;
    }

    public Class<? extends ViewConfig> cancelDish() {
        this.dish = null;
        return Pages.ShowProvider.class;
    }

    public String deleteDish(Dish dish) {
        dishService.delete(dish);
        return null;
    }

    public Class<? extends ViewConfig> saveProvider() {
        providerService.save(provider);
        return Pages.ShowProvider.class;
    }

}
