package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.PartStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zeeshan on 22/08/2017.
 */
@Repository
public interface PartStatusRepository extends CrudRepository<PartStatus, Integer> {

    List<PartStatus> findByIsActiveAndIsDeleted(int isActive, int isDeleted);
}
