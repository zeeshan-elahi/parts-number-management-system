package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.PartsChangeLog;
import com.partsnumbersystem.app.repositories.PartsChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 22/08/2017.
 */
@Service
public class PartsChangeLogService {

    @Autowired
    private PartsChangeLogRepository partsChangeLogRepository;

    public List<PartsChangeLog> getAllPartsChangeLog() {
        List<PartsChangeLog> partsChangeLogs = new ArrayList<>();
        partsChangeLogRepository.findAll().forEach(partsChangeLogs::add);
        return partsChangeLogs;
    }

    public PartsChangeLog getPartById(int id) {
        return partsChangeLogRepository.findOne(id);
    }

    public void updatePartById(int id, PartsChangeLog partsChangeLog) {
        partsChangeLogRepository.save(partsChangeLog);
    }

    public void save(PartsChangeLog partsChangeLog) {
        partsChangeLogRepository.save(partsChangeLog);
    }

    public void deletePartsChangeLog(int id) {
        partsChangeLogRepository.delete(id);
    }

}
