package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.PartStatus;
import com.partsnumbersystem.app.repositories.PartStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 22/08/2017.
 */
@Service
public class PartStatusService {

    @Autowired
    private PartStatusRepository partStatusRepository;

    public List<PartStatus> getAllPartStatuses() {
        List<PartStatus> partStatuses = new ArrayList<>();
        partStatusRepository.findAll().forEach(partStatuses::add);
        return partStatuses;
    }

    public List<PartStatus> getAllActiveNotDeletedPartStatuses() {
        List<PartStatus> partStatuses = new ArrayList<>();
        partStatusRepository.findByIsActiveAndIsDeleted(1, 0).forEach(partStatuses::add);
        return partStatuses;
    }

}
