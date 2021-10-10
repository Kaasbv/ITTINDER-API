package com.ittinder.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
public class preMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long preMatchId;

    private String initiatedUser;

    private String affectedUser;

    private boolean initiatedUserChoice;

    private boolean affectedUserChoice;

    public preMatch(String initiatedUser, String affectedUser, boolean initiatedUserChoice, boolean affectedUserChoice) {
        this.initiatedUser = initiatedUser;
        this.affectedUser = affectedUser;
        this.initiatedUserChoice = initiatedUserChoice;
        this.affectedUserChoice = affectedUserChoice;
    }

    public preMatch() {

    }
}