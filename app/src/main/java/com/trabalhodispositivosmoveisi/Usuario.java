package com.trabalhodispositivosmoveisi;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by rafael on 27/05/2017.
 */

public class Usuario extends RealmObject implements Serializable{

    private int id;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String genero;
    private float altura;
    private float peso;
    private int nivelAtividade;
    private boolean logado;

    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha, int idade, String genero, float altura, float peso, int nivelAtividade, boolean logado) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.nivelAtividade = nivelAtividade;
        this.logado = logado;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getNivelAtividade() {
        return nivelAtividade;
    }

    public void setNivelAtividade(int nivelAtividade) {
        this.nivelAtividade = nivelAtividade;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
