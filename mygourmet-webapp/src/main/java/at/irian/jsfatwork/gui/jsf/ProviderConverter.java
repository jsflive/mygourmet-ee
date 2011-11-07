package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.domain.Provider;
import at.irian.jsfatwork.service.FinderService;
import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Advanced
public class ProviderConverter extends EntityConverter<Provider> {
    @Inject
    private FinderService finderService;

    @Override
    protected Provider find(long id) {
        return finderService.find(Provider.class, id);
    }
}
