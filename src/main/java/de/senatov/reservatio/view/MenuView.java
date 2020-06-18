package de.senatov.reservatio.view;



import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



@ManagedBean
@RequestScope
@Slf4j
public class MenuView {

    private MenuModel model;



    @PostConstruct
    public void init() {

        model = new DefaultMenuModel();
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu();
        firstSubmenu.setLabel("Dynamic Submenu");

        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue("Github Repository");
        item.setUrl("https://github.com/senatov/spring-boot-primerfaces-sheduler");
        item.setIcon("pi pi-home");
        firstSubmenu.getElements().add(item);

        model.getElements().add(firstSubmenu);
        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu();
        secondSubmenu.setLabel("Dynamic Actions");

        item = new DefaultMenuItem();
        item.setValue("Save");
        item.setIcon("pi pi-save");
        item.setCommand("#{menuView.save}");
        item.setUpdate("messages");
        secondSubmenu.getElements().add(item);

        item = new DefaultMenuItem();
        item.setValue("Delete");
        item.setIcon("pi pi-times");
        item.setCommand("#{menuView.delete}");
        item.setAjax(false);
        secondSubmenu.getElements().add(item);

        item = new DefaultMenuItem();
        item.setValue("Redirect");
        item.setIcon("pi pi-search");
        item.setCommand("#{menuView.redirect}");
        secondSubmenu.getElements().add(item);
        model.getElements().add(secondSubmenu);
    }



    public MenuModel getModel() {

        return model;
    }



    public void save() {

        addMessage("Success", "Data saved");
    }



    public void update() {

        addMessage("Success", "Data updated");
    }



    public void delete() {

        addMessage("Success", "Data deleted");
    }



    public void addMessage(String summary, String detail) {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
