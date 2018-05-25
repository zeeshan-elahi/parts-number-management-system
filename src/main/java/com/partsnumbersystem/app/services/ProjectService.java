package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.ProjectStatus;
import com.partsnumbersystem.app.entities.Projects;
import com.partsnumbersystem.app.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Projects> getAllProjects() {
        List<Projects> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    public List<Projects> getAllActiveNotDeletedProjects() {
        List<Projects> projects = new ArrayList<>();
        projectRepository.findByIsActiveAndIsDeleted(1,0).forEach(projects::add);
        return projects;
    }

    public List<Projects> getAllProjectsByProjectStatusIdNotInAndIsActiveAndIsDeleted(List<ProjectStatus> projectStatusCollection, int isActive, int isDeleted){
        List<Projects> projects = new ArrayList<>();
        projectRepository.findByProjectStatusIdNotInAndIsActiveAndIsDeletedOrderByProjectNumber(projectStatusCollection,isActive,isDeleted).forEach(projects::add);
        return projects;
    }

    public Integer countByProjectNumber(Integer projectNumber){
        return projectRepository.countByProjectNumber(projectNumber);
    }

    public Integer countByProjectIdNotAndProjectCode(Integer projectId, String projectCode){
        return projectRepository.countByProjectIdNotAndProjectCode(projectId, projectCode);
    }

    public Integer getMaxProjectNumber(){
        Projects projectObj = projectRepository.findTop1ByOrderByProjectNumberDesc();

        return (projectObj != null)? projectObj.getProjectNumber() : 0;
    }

    public Integer countByIsLegacyProject(Integer isLegacyProject){
        return projectRepository.countByIsLegacyProject(isLegacyProject);
    }

    public Projects getProjectById(int id) {
        return projectRepository.findOne(id);
    }

    public void updateProjectById(int id, Projects project) {
        projectRepository.save(project);
    }

    public void save(Projects project) {
        projectRepository.save(project);
    }

    public void deleteProject(int id) {
        projectRepository.delete(id);
    }

}
