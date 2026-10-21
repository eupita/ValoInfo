package com.valoinfo.model;

import jakarta.persistence.*; // Importação necessária

@Entity // Indica que isto vai ser uma tabela na base de dados
public class ContaRiot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária gerada automaticamente
    
    @Column(unique = true) // Impede que existam dois nicks iguais
    private String nick;
    
    private String password;

    public ContaRiot() {}

    // Gere os Getters e Setters para o id, nick e password
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}