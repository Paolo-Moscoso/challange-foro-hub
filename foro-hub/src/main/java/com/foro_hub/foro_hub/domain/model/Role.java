package com.foro_hub.foro_hub.domain.model;


public class Role {
    private Long id;
    private String name; // Ej: ADMIN, USER

    public Role(String name) {
        this.name = name;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}