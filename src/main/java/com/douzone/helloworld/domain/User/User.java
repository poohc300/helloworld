package com.douzone.helloworld.domain.User;
import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email){
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this .email = email;
    }

    public void getId(UUID id){
        this.id = id;
    }

    public void getUsername(String username){
        this.username = username;
    }

    public void getEmail(String email){
        this.email = email;
    }
}
