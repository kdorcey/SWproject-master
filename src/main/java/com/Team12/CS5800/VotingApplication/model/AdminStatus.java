package com.Team12.CS5800.VotingApplication.model;

public enum AdminStatus {
    USER (0),
    MANAGER (1),
    ADMIN (2);

    private int value;
    
    private AdminStatus(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
}