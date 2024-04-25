package com.moonpool.mpapiserver.handler;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileHandler {
    @Value("${com.moonpool.upload.path}")
    private String uploadPath;
    @PostConstruct
    public void folderInit(){
        File saveFolder = new File(uploadPath);
        if(!saveFolder.exists()){
            saveFolder.mkdir();
        }
        uploadPath = saveFolder.getAbsolutePath();
    }

    public List<String> saveAnswerFiles(List<MultipartFile> answerFiles) throws RuntimeException{
        List<String> answeImgrNames = new ArrayList<>();
        if(answerFiles == null || answerFiles.isEmpty()){
            return answeImgrNames;
        }
        for (MultipartFile file : answerFiles){
            String savedName = UUID.randomUUID().toString()+"A_"+file.getOriginalFilename();
            Path savePath = Paths.get(uploadPath,savedName);
            try {
                Files.copy(file.getInputStream(),savePath);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return answeImgrNames;
    }
    public String saveQuizFile(MultipartFile quizFile) throws RuntimeException{
        if (quizFile == null || quizFile.isEmpty()){
            return "";
        }
        String savedName = UUID.randomUUID().toString()+"Q_"+quizFile.getOriginalFilename();
        Path savePath = Paths.get(uploadPath,savedName);
        return savedName;
    }
    public ResponseEntity<Resource> getFile(String fileName){ //파일 조회
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName); //경로에있는 파일들 조회
        if(!resource.isReadable()){
            resource = (Resource) new FileSystemResource(uploadPath+File.separator+"default.jpeg");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    public void deleteFiles(List<String> fileNames){
        if (fileNames == null || fileNames.isEmpty()){
            return ;
        }
        fileNames.forEach(fileName -> {
           String answerFileName = "A_" + fileName;
           Path answerPath = Paths.get(uploadPath, answerFileName);
        });
    }
}
