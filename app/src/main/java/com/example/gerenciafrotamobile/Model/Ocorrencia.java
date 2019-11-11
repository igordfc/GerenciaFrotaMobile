package com.example.gerenciafrotamobile.Model;

import java.util.Date;

import lombok.Data;

@Data
public class Ocorrencia {
    private Integer id;
    private String titulo;
    private String descrição;
    private Date inicio;
    private Date fim;
    private Usuario usuario;
    private Veiculo veículo;
}
