package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.ProjectStatus;
import com.partsnumbersystem.app.entities.Projects;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Projects, Integer> {

    List<Projects> findByIsActiveAndIsDeleted(int isActive, int isDeleted);

    List<Projects> findByProjectStatusIdNotInAndIsActiveAndIsDeletedOrderByProjectNumber(List<ProjectStatus>  projectStatusCollection, int isActive, int isDeleted);

    Integer countByProjectNumber(Integer projectNumber);

    Integer countByProjectIdNotAndProjectCode(Integer projectId, String projectCode);

    Integer countByIsLegacyProject(Integer isLegacyProject);

    Projects findTop1ByOrderByProjectNumberDesc();
}
