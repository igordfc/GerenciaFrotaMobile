package com.example.gerenciafrotamobile.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Usuario {

    private Integer id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String logradouro;

    private String cep;

    private String telefone;

    private TipoUsuario tipo;

}
