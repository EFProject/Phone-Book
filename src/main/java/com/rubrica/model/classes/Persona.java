package com.rubrica.model.classes;

public class Persona 
{
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private Integer eta;

    public Persona(int id, String nome, String cognome, String indirizzo, String telefono, Integer eta) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.eta = eta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getEta() {
        return eta;
    }

    public void SetEta(Integer eta) {
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cognome: " + cognome + ", Indirizzo: " + indirizzo + ", Numero: " + telefono + ", Eta: " + eta;
    }
}
