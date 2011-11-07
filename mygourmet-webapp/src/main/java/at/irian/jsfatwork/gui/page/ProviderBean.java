package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.Category;
import at.irian.jsfatwork.domain.Dish;
import at.irian.jsfatwork.domain.Provider;
import at.irian.jsfatwork.service.DishService;
import at.irian.jsfatwork.service.FinderService;
import at.irian.jsfatwork.service.ProviderService;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewAccessScoped
public class ProviderBean implements Serializable {

    @Inject
    private ProviderService providerService;
    @Inject
    private FinderService finderService;
    @Inject
    private DishService dishService;

    private Provider provider;
    private Dish dish;
    private List<SelectItem> categoryItems;

    @PostConstruct
    public void init() {
        List<Category> categories = finderService.findAll(Category.class);
        categoryItems = new ArrayList<SelectItem>(categories.size());
        for (Category cat : categories) {
            categoryItems.add(new SelectItem(cat, cat.getName()));
        }
    }

    public Provider getProvider() {
        return provider;
    }

    public String createProvider() {
        this.provider = providerService.createNew();
        return ViewIds.EDIT_PROVIDER;
    }

    public String showProvider(long id) {
        this.provider = providerService.findById(id);
        return ViewIds.SHOW_PROVIDER;
    }

    public String cancel() {
        if (provider.isTransient()) {
            return ViewIds.PROVIDER_LIST;
        } else {
            return ViewIds.SHOW_PROVIDER;
        }
    }

    public Dish getDish() {
        return dish;
    }

    public String createDish() {
        this.dish = dishService.createNew(provider);
        return ViewIds.EDIT_DISH;
    }

    public String editDish(Dish dish) {
        this.dish = dish;
        return ViewIds.EDIT_DISH;
    }

    public String saveDish() {
        dishService.save(dish);
        this.provider = providerService.findById(this.provider.getId());
        this.dish = null;
        return ViewIds.SHOW_PROVIDER;
    }

    public String cancelDish() {
        this.dish = null;
        return ViewIds.SHOW_PROVIDER;
    }

    public String deleteDish(Dish dish) {
        dishService.delete(dish);
        return null;
    }

    public List getCategoryItems() {
        return categoryItems;
    }

    public String saveProvider() {
        providerService.save(provider);
        return ViewIds.SHOW_PROVIDER;
    }

}
