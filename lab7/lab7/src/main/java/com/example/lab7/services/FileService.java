package com.example.lab7.services;

import com.example.lab7.entities.Document;
import com.example.lab7.entities.User;
import com.example.lab7.repositories.FileRepository;
import com.example.lab7.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class FileService {
    private List<Document> files;

    @Inject
    private FileRepository fileRepo;

    @Inject
    private UserRepository userRepo;

    @PostConstruct
    public void init(){

    }

    public void updateList(){
        files = fileRepo.getAll();
    }

    public void addFile(String username, byte[] file, String fileName){
        User author = this.userRepo.getByUsername(username).get(0);
        Document doc = new Document(generateRegistrationNumber(), fileName, file, author);
        fileRepo.add(doc);
    }

    public int deleteFile(String id){
        Document f = this.fileRepo.getById(id).get(0);

        if(f != null){
            this.fileRepo.delete(id);
            return 1;
        }
        else{
            return 0;
        }
    }

    public void updateFile(String id, String username, byte[] file, String fileName){
        Document f = this.fileRepo.getById(id).get(0);

        f.setName(fileName);
        f.setContent(file);

        User u = this.userRepo.getByUsername(username).get(0);
        f.setUser(u);

        fileRepo.update(f);
    }

    @Produces
    @SessionScoped
    public String generateRegistrationNumber(){
        String date = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
        return date;
    }

    public List<Document> getFiles(){
        updateList();
        return new ArrayList<>(files);
    }

    public List<Document> getByAuthor(String usr){
        User author = this.userRepo.getByUsername(usr).get(0);
        return this.fileRepo.getByAuthor(author);
    }

    public Document getByFilename(String name){
        List<Document> d = this.fileRepo.getByFileName(name);
        return d.get(0);
    }
}
