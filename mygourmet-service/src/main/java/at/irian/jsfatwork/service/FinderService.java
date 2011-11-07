package at.irian.jsfatwork.service;

import at.irian.jsfatwork.dao.CrudService;
import at.irian.jsfatwork.domain.BaseEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FinderService {
    @Inject
    private CrudService crudService;

    public <T extends BaseEntity> T find(Class<T> clazz, long id) {
        return crudService.findById(clazz, id);
    }

    public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
        return crudService.findAll(clazz);
    }

}
