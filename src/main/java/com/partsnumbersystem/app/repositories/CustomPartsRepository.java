package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.Parts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Zeeshan on 28/08/2017.
 */
@Repository
public class CustomPartsRepository{

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Parts> getMatchedParts(String query) {
        String hql = query;
        return (List<Parts>) entityManager.createQuery(hql).getResultList();
    }

}
