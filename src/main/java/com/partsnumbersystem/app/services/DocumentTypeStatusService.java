package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.DocumentTypeStatus;
import com.partsnumbersystem.app.entities.DocumentTypes;
import com.partsnumbersystem.app.repositories.DocumentTypeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Service
public class DocumentTypeStatusService {

    @Autowired
    private DocumentTypeStatusRepository documentTypeStatusRepository;

    public List<DocumentTypeStatus> getAllDocumentTypeStatuses() {
        List<DocumentTypeStatus> documentTypeStatuses = new ArrayList<>();
        documentTypeStatusRepository.findAll().forEach(documentTypeStatuses::add);
        return documentTypeStatuses;
    }

    public List<DocumentTypeStatus> getAllActiveNotDeletedDocumentTypeStatuses() {
        List<DocumentTypeStatus> documentTypeStatuses = new ArrayList<>();
        documentTypeStatusRepository.findByIsActiveAndIsDeleted(1, 0).forEach(documentTypeStatuses::add);
        return documentTypeStatuses;
    }

}
