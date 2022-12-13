package com.example.lab7.config;

import com.example.lab7.controllers.DocumentController;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("resources")
@ApplicationScoped
public class ApplicationConfig extends Application {
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(DocumentController.class);
    }
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

}
