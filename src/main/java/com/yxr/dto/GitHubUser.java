package com.yxr.dto;

public class GitHubUser {
    private Long id;
    private String name;
    private String pio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPio() {
        return pio;
    }

    public void setPio(String pio) {
        this.pio = pio;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pio='" + pio + '\'' +
                '}';
    }
}
