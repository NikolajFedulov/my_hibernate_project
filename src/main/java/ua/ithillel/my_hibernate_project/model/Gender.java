package ua.ithillel.my_hibernate_project.model;

public enum Gender {
    MALE ("MALE"),
    FEMALE ("FEMALE");
    public final String var;

    Gender(String var) {
        this.var = var;
    }
}
