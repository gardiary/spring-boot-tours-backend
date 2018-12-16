package com.tours.persistence.dao;

import com.tours.persistence.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gardiary on 10/12/18.
 */
public abstract class BaseDao<E extends BaseEntity> {

    protected final Class<E> entityClass;
    protected final String entityName;

    @Autowired
    protected EntityManager entityManager;

    public BaseDao(final Class<E> entityClass) {
        this.entityClass = entityClass;
        this.entityName = this.entityClass.getName();
    }

    @Transactional
    public void save(E entity) {
        this.entityManager.persist(entity);
    }

    public E get(final Long id) {
        return this.entityManager.find(this.entityClass, id);
    }

    public List<E> findAll() {
        final String query = "from " + this.entityName + " o order by o.externalId";
        return this.entityManager.createQuery(query, this.entityClass).getResultList();
    }

    public List<E> findAll(String filterName) {
        final String query = "from " + this.entityName + " o where lower(o.name) like lower(:filterName) order by o.externalId";
        return this.entityManager.createQuery(query, this.entityClass)
                .setParameter("filterName", "%" + filterName + "%")
                .getResultList();
    }
}
