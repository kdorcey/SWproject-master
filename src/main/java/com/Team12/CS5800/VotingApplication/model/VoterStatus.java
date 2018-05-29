package com.Team12.CS5800.VotingApplication.model;

public enum VoterStatus {
    NOT_APPLIED (0),
    APPLIED (1),
    APPROVED (2);

    private int value;
    
    private VoterStatus(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
}