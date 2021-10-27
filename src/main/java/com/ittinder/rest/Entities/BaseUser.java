package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;

@Table(name = "user")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PremiumUser.class, name = "premium"),
        @JsonSubTypes.Type(value = User.class, name = "normal")
})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class BaseUser {
        public BaseUser(){

        }
}
