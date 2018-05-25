package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.ProjectStatus;
import com.partsnumbersystem.app.repositories.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 22/08/2017.
 */
@Service
public class ProjectStatusService {

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    public List<ProjectStatus> getAllProjectStatuses() {
        List<ProjectStatus> projectStatuses = new ArrayList<>();
        projectStatusRepository.findAll().forEach(projectStatuses::add);
        return projectStatuses;
    }

    public List<ProjectStatus> getAllActiveNotDeletedProjectStatuses() {
        List<ProjectStatus> projectStatuses = new ArrayList<>();
        projectStatusRepository.findByIsActiveAndIsDeleted(1, 0).forEach(projectStatuses::add);
        return projectStatuses;
    }

}
