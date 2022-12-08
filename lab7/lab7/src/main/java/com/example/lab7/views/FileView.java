package com.example.lab7.views;

import com.example.lab7.entities.Document;
import com.example.lab7.entities.User;
import com.example.lab7.services.FileService;
import com.example.lab7.services.UserService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.IOUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Named("fileView")
@ViewScoped
public class FileView implements Serializable {
    private List<Document> allFiles;

    private List<Document> filesByUser;

    private String newFileName;

    private UploadedFile newFileContent;

    private String currentUsername;

    @Inject
    private FileService service;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if(session.getAttribute("username") != null){
            currentUsername = session.getAttribute("username").toString();
            filesByUser = service.getByAuthor(currentUsername);
        }
        allFiles = service.getFiles();
    }

    public void saveFile(){

        /* convert file from part to byte[] */
        try (InputStream input = newFileContent.getInputStream()) {
            byte[] file = IOUtils.toByteArray(input);
            this.service.addFile(currentUsername, file, newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public StreamedContent getStreamedContent(Document d){
        ByteArrayInputStream file = new ByteArrayInputStream(d.getContent());
        return DefaultStreamedContent.builder()
                .name(d.getName() + ".txt")
                .contentType("text")
                .stream(() -> file)
                .build();
    }




    public List<Document> getAllFiles() {
        return allFiles;
    }

    public void setAllFiles(List<Document> files) {
        this.allFiles = files;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public UploadedFile getNewFileContent() {
        return newFileContent;
    }

    public void setNewFileContent(UploadedFile newFileContent) {
        this.newFileContent = newFileContent;
    }

    public FileService getService() {
        return service;
    }

    public void setService(FileService service) {
        this.service = service;
    }

    public List<Document> getFilesByUser() {
        return filesByUser;
    }

    public void setFilesByUser(List<Document> filesByUser) {
        this.filesByUser = filesByUser;
    }
}
