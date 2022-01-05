package com.ittinder.rest.Entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Getter
@Entity
public class Session {
    @Id
    private String sessionId;

    @ManyToOne
    private User user;

    private Date createdDate;

    public Session(){}

    public Session(User user){
        this.user = user;
        this.createdDate = new Date();
        this.sessionId = this.generateSessionId();
    }

    public static String generateSessionId () {//Definitely not copied from stackoverflow
        final int length = 32;
        final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();

        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(allAllowed[random.nextInt(allAllowed.length)]);
        }

        return password.toString();
    }
}
