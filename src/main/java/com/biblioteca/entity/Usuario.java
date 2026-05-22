package com.biblioteca.entity;

/*
 * Importações do JPA.
 * JPA = tecnologia usada para mapear objetos Java em tabelas do banco.
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(length = 20)
    private String telefone;
    
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
private List<Emprestimo> emprestimos;

    public Long getIdUsuario() {
        return idUsuario;
    }   

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsusario) {
        this.nomeUsuario = nomeUsusario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

   public List<Emprestimo> getEmprestimos() {
    return emprestimos;
}

public void setEmprestimos(List<Emprestimo> emprestimos) {
    this.emprestimos = emprestimos;
}
}
    