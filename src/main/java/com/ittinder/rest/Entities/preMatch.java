package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@lombok.Getter
@lombok.Setter
@Table(name = "pre_match")
public class preMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long preMatchId;

    // @JsonIgnore
    @JsonBackReference(value="user-prematch-initiated")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User initiatedUser;

    // @JsonIgnore
    @JsonBackReference(value="user-prematch-affected")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User affectedUser;

    private boolean initiatedUserChoice;

    private boolean affectedUserChoice;

    private Date ChangedDate;


    public preMatch(User initiatedUser, User affectedUser) {
        this.initiatedUser = initiatedUser;
        this.affectedUser = affectedUser;
    }

    public preMatch() {

    }

    public User getInitiatedUser() {
        return initiatedUser;
    }

    public User getAffectedUser() {
        return affectedUser;
    }
}