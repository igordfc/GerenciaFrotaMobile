package com.example.gerenciafrotamobile.Model;

import lombok.Data;

@Data
public abstract class Veiculo {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private TipoVeiculo tipo;
    private boolean inativo;
    private String imagem;
}
