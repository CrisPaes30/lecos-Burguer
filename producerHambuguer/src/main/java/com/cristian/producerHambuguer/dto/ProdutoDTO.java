package com.cristian.producerHambuguer.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ProdutoDTO implements Serializable {

    private String nomeProduto;
    private String complementoLanche;
    private String observacao;
    private Double precoUnitario;

}
