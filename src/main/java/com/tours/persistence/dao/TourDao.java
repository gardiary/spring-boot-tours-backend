package com.tours.persistence.dao;

import com.tours.persistence.entity.TourEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Created by gardiary on 10/12/18.
 */
@Repository
public class TourDao extends BaseDao<TourEntity> {
    public TourDao() {
        super(TourEntity.class);
    }

    public TourEntity getByExternalId(Long externalId) {
        final String query = "from " + entityName + " o where o.externalId = :externalId";

        try {
            return entityManager.createQuery(query, entityClass)
                    .setParameter("externalId", externalId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
