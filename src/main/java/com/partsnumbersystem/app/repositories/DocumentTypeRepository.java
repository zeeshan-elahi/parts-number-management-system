package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.DocumentTypeStatus;
import com.partsnumbersystem.app.entities.DocumentTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Repository
public interface DocumentTypeRepository extends CrudRepository<DocumentTypes, Integer> {

    List<DocumentTypes> findByIsActiveAndIsDeleted(int isActive, int isDeleted);

    List<DocumentTypes> findByTypeStatusIdNotInAndIsActiveAndIsDeleted(List<DocumentTypeStatus> documentTypeStatusList, int isActive, int isDeleted);

    Integer countByDocumentTypeNumber(String documentTypeNumber);

}
