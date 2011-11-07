package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.domain.Category;
import at.irian.jsfatwork.domain.CreditCardType;
import at.irian.jsfatwork.gui.util.GuiUtil;
import at.irian.jsfatwork.service.FinderService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@ApplicationScoped
public class SelectItemsProducer {

    @Inject
    private FinderService finderService;

    private List<SelectItem> categories;

    @PostConstruct
    public void init() {
        // Initialize category items from DB
        List<Category> cats = finderService.findAll(Category.class);
        categories = new ArrayList<SelectItem>(cats.size());
        for (Category cat : cats) {
            categories.add(new SelectItem(cat, cat.getName()));
        }
    }

    @Produces @ApplicationScoped @CategoryItems @Named
    public List<SelectItem> getCategoryItems() {
        return categories;
    }

    @Produces @RequestScoped @CreditCardItems @Named
    public List<SelectItem> getCreditCardItems(FacesContext facesContext) {
        List<SelectItem> ccTypes = new ArrayList<SelectItem>();
        ccTypes.add(new SelectItem(CreditCardType.CARD_A, getCCTypeLabel(facesContext, CreditCardType.CARD_A)));
        ccTypes.add(new SelectItem(CreditCardType.CARD_B, getCCTypeLabel(facesContext, CreditCardType.CARD_B)));
        return ccTypes;
    }

    private String getCCTypeLabel(FacesContext facesContext, CreditCardType type) {
        String key = "credit_card_type_" + type.toString();
        return GuiUtil.getResourceText(facesContext, "msgs", key);
    }

}
