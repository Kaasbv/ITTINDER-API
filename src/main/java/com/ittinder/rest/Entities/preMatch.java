package com.ittinder.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class preMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long preMatchId;

    private String initiatedUser;

    private String affectedUser;

    private boolean initiatedUserChoice;

    private boolean affectedUserChoice;

    public preMatch(String initiatedUser) { this.initiatedUser = initiatedUser;}
}