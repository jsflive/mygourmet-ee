package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.domain.Category;
import at.irian.jsfatwork.service.FinderService;
import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import javax.inject.Inject;
import javax.inject.Named;

@Advanced
@Named("categoryConverter")
public class CategoryConverter extends EntityConverter<Category> {
    @Inject
    private FinderService finderService;

    @Override
    protected Category find(long id) {
        return finderService.find(Category.class, id);
    }
}
