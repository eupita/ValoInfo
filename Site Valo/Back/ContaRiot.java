package com.valoinfo.model;

import jakarta.persistence.*;

@Entity
public class ContaRiot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String nick;
    
    private String password;

    public ContaRiot() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}