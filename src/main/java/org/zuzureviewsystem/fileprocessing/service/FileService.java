package org.zuzureviewsystem.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.FilesEntity;
import org.zuzureviewsystem.util.Constants;
import org.zuzureviewsystem.util.LogMessages;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FileService {
    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());

    @Autowired
    RepositoryService repositoryService;
    public void save(boolean isFileProcessed,Long providerId, String file, String bucket){
        try {
            FilesEntity filesEntity = repositoryService.getFileByNameAndProviderId(file,providerId);

            if(filesEntity == null) {
                filesEntity = new FilesEntity();
                filesEntity.setBucket(bucket);
                filesEntity.setFile(file);
                filesEntity.setProviderId(providerId);
            }
            filesEntity.setStatus(isFileProcessed);

            repositoryService.saveFile(filesEntity);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, LogMessages.ERROR_WHILE_STORING_LOG + " :: " + e.getMessage());
        }
    }
}
