package com.example.gerenciafrotamobile.Model;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Ocorrencia {

    private Integer id;
    private String titulo;
    private String descrição;
    private Date inicio;
    private Date fim;
    private Usuario usuario;
    private Veiculo veículo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Veiculo getVeículo() {
        return veículo;
    }

    public void setVeículo(Veiculo veículo) {
        this.veículo = veículo;
    }


}
