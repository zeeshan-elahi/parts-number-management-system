package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.DocumentTypes;
import com.partsnumbersystem.app.entities.Parts;
import com.partsnumbersystem.app.entities.Projects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Repository
public interface PartRepository extends CrudRepository<Parts, Integer> {

    List<Parts> findByIsActiveAndIsDeleted(int isActive, int isDeleted);

    Parts findTopByProjectIdAndDocumentTypeIdOrderByPartNumberDesc(Projects projectId, DocumentTypes documentTypeId);

}
