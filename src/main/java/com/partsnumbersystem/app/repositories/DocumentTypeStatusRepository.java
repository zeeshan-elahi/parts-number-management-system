package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.DocumentTypeStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Repository
public interface DocumentTypeStatusRepository extends CrudRepository<DocumentTypeStatus, Integer> {

    List<DocumentTypeStatus> findByIsActiveAndIsDeleted(int isActive, int isDeleted);
}
