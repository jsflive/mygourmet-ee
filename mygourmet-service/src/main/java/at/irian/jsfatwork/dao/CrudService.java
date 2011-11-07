package at.irian.jsfatwork.dao;

import at.irian.jsfatwork.domain.BaseEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CrudService {

    @PersistenceContext
    private EntityManager em;

    public <T extends BaseEntity> T createNew(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends BaseEntity> void persist(T entity) {
        em.persist(entity);
    }

    public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        return em.createQuery(query.select(query.from(clazz))).getResultList();
    }

    public <T extends BaseEntity> T findById(Class<T> clazz, long id) {
        return em.find(clazz, id);
    }

    public <T extends BaseEntity> void delete(T entity) {
        em.remove(entity);
    }

    public <T extends BaseEntity> T merge(T entity) {
        return em.merge(entity);
    }

}
