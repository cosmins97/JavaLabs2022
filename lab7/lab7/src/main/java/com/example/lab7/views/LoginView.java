package com.example.lab7.views;

import com.example.lab7.entities.User;
import com.example.lab7.services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named("loginView")
@SessionScoped
public class LoginView implements Serializable {
    private String username;
    private String password;

    @Inject
    private UserService service;

    public LoginView() {
    }

    public String login(){
        User user = this.service.validateLogin(username, password);

        if(user != null){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole().getName());

            return "home.xhtml";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
                    "password",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));

            return "login.xhtml";
        }
    }

    public String logout(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.invalidate();

        return "home.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }
}
