package com.cristian.producerHambuguer.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class PedidoDTO implements Serializable {

    private String numeroPedido;
    private String cliente;
    private String endereco;
    private String complementoEndereco;
    private List<ProdutoDTO> produtos = new ArrayList<>();
    private Double precoTotal;

}
