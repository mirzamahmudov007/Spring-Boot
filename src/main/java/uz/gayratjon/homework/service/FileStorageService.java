package uz.gayratjon.homework.service;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.gayratjon.homework.entity.FileStorage;
import uz.gayratjon.homework.entity.enumeration.FileStorageStatus;
import uz.gayratjon.homework.repository.FileStorageRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;

    private final Hashids hashids;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    @Value("${upload.server.folder}")
    private String serverFolderPath;


    // save mathodi
    public FileStorage save(MultipartFile multipartFile) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorage.setExtension(getExtension(multipartFile.getOriginalFilename()));  // pastda getExtension degan method bor
        fileStorage = fileStorageRepository.save(fileStorage);


        Date now = new Date();

        String path = String.format("%s/upload_files/%d/%d/%d/",
                this.serverFolderPath, 1900 + now.getYear(), 1 + now.getMonth(), now.getDate());
        File uploadFolder = new File(path);
        if (!uploadFolder.exists() && uploadFolder.mkdirs()) {
            System.out.println("Folder created");
        }

        fileStorage.setHashId(hashids.encode(fileStorage.getId())); // fileStorage ning id sidan hashId yasayabdi

        String pathLocal = String.format("/upload_files/%d/%d/%d/%s.%s",
                1900 + now.getYear(), 1 + now.getMonth(), now.getDate(), fileStorage.getHashId(), fileStorage.getExtension());

        fileStorage.setUploadFolder(pathLocal);
        fileStorageRepository.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();

        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtension()));

        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileStorage;
    }

    public FileStorage findByHashId(String hashId) {
        return fileStorageRepository.findByHashId(hashId);
    }

    public void delete(String hashId) {
        FileStorage fileStorage = fileStorageRepository.findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.serverFolderPath, fileStorage.getUploadFolder()));
        if (file.delete()) {
            fileStorageRepository.delete(fileStorage);
        }
    }


    private String getExtension(String fileName) {
        String extension = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                extension = fileName.substring(dot + 1);
            }
        }
        return extension;
    }


}
