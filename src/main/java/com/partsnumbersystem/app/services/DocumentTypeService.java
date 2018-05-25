package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.DocumentTypeStatus;
import com.partsnumbersystem.app.entities.DocumentTypes;
import com.partsnumbersystem.app.repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public List<DocumentTypes> getAllDocumentTypes() {
        List<DocumentTypes> documentTypes = new ArrayList<>();
        documentTypeRepository.findAll().forEach(documentTypes::add);
        return documentTypes;
    }

    public List<DocumentTypes> getAllActiveNotDeletedDocumentTypes() {
        List<DocumentTypes> documentTypes = new ArrayList<>();
        documentTypeRepository.findByIsActiveAndIsDeleted(1,0).forEach(documentTypes::add);
        return documentTypes;
    }

    public List<DocumentTypes> getAllDocumentTypesByTypeStatusIdNotInAndIsActiveAndIsDeleted(List<DocumentTypeStatus> projectStatusCollection, int isActive, int isDeleted){
        List<DocumentTypes> documentTypes = new ArrayList<>();
        documentTypeRepository.findByTypeStatusIdNotInAndIsActiveAndIsDeleted(projectStatusCollection,isActive,isDeleted).forEach(documentTypes::add);
        return documentTypes;
    }

    public Integer countByDocumentTypeNumber(String documentTypeNumber){
        return documentTypeRepository.countByDocumentTypeNumber(documentTypeNumber);
    }

    public DocumentTypes getDocumentTypeById(int id) {
        return documentTypeRepository.findOne(id);
    }

    public void updateDocumentTypeById(int id, DocumentTypes documentTypes) {
        documentTypeRepository.save(documentTypes);
    }

    public void save(DocumentTypes documentTypes) {
        documentTypeRepository.save(documentTypes);
    }

    public void deleteDocumentType(int id) {
        documentTypeRepository.delete(id);
    }

}
