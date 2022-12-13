package com.example.lab7.controllers;

import com.example.lab7.entities.Document;
import com.example.lab7.services.FileService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/documents")
@ApplicationScoped
public class DocumentController {
    @Inject
    private FileService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> getDocuments(){
        return service.getFiles();
    }

    @GET
    @Path("/{username}")
    public List<Document> getDocumentsByUser(@PathParam("username") String usr){
        return service.getByAuthor(usr);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDocumentById(@PathParam("id") String id){
        int res = service.deleteFile(id);
        if(res == 1) {
            return Response.ok().entity("Document deleted!").build();
        }
        else{
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDocument(@QueryParam("username") String username,
                            @QueryParam("filename") String filename,
                            @QueryParam("content") String content){
        this.service.addFile(username, content.getBytes(), filename);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateDocument(@PathParam("id") String id,
                               @QueryParam("username") String username,
                               @QueryParam("filename") String filename,
                               @QueryParam("content") String content){
        this.service.updateFile(id, username, content.getBytes(), filename);
    }
}
