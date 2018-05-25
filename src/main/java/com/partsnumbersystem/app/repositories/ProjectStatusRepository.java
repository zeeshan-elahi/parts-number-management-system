package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.ProjectStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zeeshan on 22/08/2017.
 */
@Repository
public interface ProjectStatusRepository extends CrudRepository<ProjectStatus, Integer> {

    List<ProjectStatus> findByIsActiveAndIsDeleted(int isActive, int isDeleted);
}
