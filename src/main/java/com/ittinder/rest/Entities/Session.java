package com.ittinder.rest.Entities;

import com.ittinder.rest.Util.RandomString;
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
        this.sessionId = RandomString.generate();
    }
}
