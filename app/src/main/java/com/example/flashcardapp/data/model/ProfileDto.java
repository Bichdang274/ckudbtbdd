package com.example.flashcardapp.data.model;

public class ProfileDto {
    public final String id;
    public final String name;
    public final String email;

    public ProfileDto(String id, String name, String email) {
        this.id = id; this.name = name; this.email = email;
    }
}
