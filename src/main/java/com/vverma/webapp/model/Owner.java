package com.vverma.webapp.model;

public class Owner {

    private String id;
    private String name;
    private String accountNumber;
    private int level;

    // Getter for Id.
    public String getId() { return id; }

    // Setter for Id.
    public void setId(String id) { this.id = id; }

    // Getter for name.
    public String getName() { return name; }

    // Setter for name.
    public void setName(String name) { this.name = name; }

    // Getter for accountNumber.
    public String getAccountNumber() { return accountNumber; }

    // Setter for accountNumber.
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    // Getter for level.
    public int getLevel() { return level; }

    // Setter for level.
    public void setLevel(int level) { this.level = level; }
}
