package de.senatov.drill.jsf_springboot.model;

import javax.inject.Named;

@Named
public class HelloWorld {

    private String firstName = "John";
    private String lastName = "Doe";

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return this.lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String showGreeting() {
        return "Hello " + this.firstName + ' ' + this.lastName + '!';
    }
}
