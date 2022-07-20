package ua.ithillel.my_hibernate_project.model;

public enum Species {
    CAT ("CAT"),
    DOG ("DOG");

    public final String var;

    Species(String var) {
        this.var = var;
    }
}
