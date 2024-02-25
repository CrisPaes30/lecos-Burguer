package com.cristian.frontCozinha.tela;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class FrontTela2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciamento de Pedidos Leco´s Burguer");

        // Criação do GridPane para organizar a interface
        GridPane gridPane = new GridPane();
        gridPane.setHgap(220);
        gridPane.setVgap(20);

        // Define o fundo preto para o GridPane
        gridPane.setStyle("-fx-background-color: black;");

        //**************************************** Coluna Novos Pedidos **************************************

        // Linha vertical
        Separator verticalSeparator = new Separator(Orientation.VERTICAL);
        verticalSeparator.setPrefHeight(800); // Define a altura da linha para o tamanho da janela
        verticalSeparator.setStyle("-fx-background-color: yellow;");

        // Adiciona a linha vertical ao GridPane
        gridPane.add(verticalSeparator, 1, 0, 1, 3); // Coluna 1, Linha 0, Span 1 coluna, 3 linhas

        // Separador antes de Novos Pedidos
        Separator separatorBeforeNovosPedidos = new Separator(Orientation.VERTICAL);
        separatorBeforeNovosPedidos.setPrefHeight(800); // Define a altura do separador para o tamanho da janela
        separatorBeforeNovosPedidos.setStyle("-fx-background-color: yellow;");

        // Adiciona o separador antes de Novos Pedidos ao GridPane
        gridPane.add(separatorBeforeNovosPedidos, 0, 0, 1, 3); // Coluna 0, Linha 0, Span 1 coluna, 3 linhas

        // Rótulos para as seções
        Label novosPedidosLabel = new Label("NOVOS PEDIDOS");
        novosPedidosLabel.setTextFill(Color.YELLOW);

        // Adiciona os rótulos ao GridPane
        GridPane.setMargin(novosPedidosLabel, new Insets(0, 0, 0, 50)); // Define a margem esquerda para 50 pixels

        // Adiciona os rótulos ao GridPane
        gridPane.add(novosPedidosLabel, 0, 0);


        //*********************************** Coluna Pedidos em Andamento***********************************************

        Label pedidosAndamentoLabel = new Label("PEDIDOS EM ANDAMENTO");
        pedidosAndamentoLabel.setTextFill(Color.YELLOW);

        // Adiciona os rótulos ao GridPane
        gridPane.add(pedidosAndamentoLabel, 2, 0);

        // Separadores entre as colunas
        Separator separator1 = new Separator(Orientation.VERTICAL);

        separator1.setStyle("-fx-background-color: yellow;");

        gridPane.add(separator1, 3, 0, 1, 3); // Adiciona o separador entre Novos Pedidos e Pedidos em Andamento

        // Adiciona os rótulos ao GridPane
       // GridPane.setMargin(separator1, new Insets(0, 0, 0, 50)); // Define a margem esquerda para 50 pixels

        //************************************ Coluna Finalizados *************************************************


        Label pedidosFinalizadosLabel = new Label("PEDIDOS FINALIZADOS");
        pedidosFinalizadosLabel.setTextFill(Color.YELLOW);

        // Adiciona os rótulos ao GridPane
        gridPane.add(pedidosFinalizadosLabel, 4, 0);

        // Separadores entre as colunas
        Separator separator2 = new Separator(Orientation.VERTICAL);
        Separator separator3 = new Separator(Orientation.VERTICAL);

        separator2.setStyle("-fx-background-color: yellow;");
        separator3.setStyle("-fx-background-color: yellow;");


        gridPane.add(separator2, 5, 0, 1, 3); // Adiciona o separador entre Pedidos em Andamento e Pedidos Finalizados
        //gridPane.add(separator3, 6, 0, 1, 3); // Adiciona um separador após Pedidos Finalizados


        //********************** Seperador Horizontal**************************************************

        Separator horizontal = new Separator(Orientation.HORIZONTAL);
        Separator horizontal1 = new Separator(Orientation.HORIZONTAL);
        Separator horizontal2 = new Separator(Orientation.HORIZONTAL);
        GridPane.setMargin(horizontal, new Insets(25, 0, 0, 0));
        GridPane.setMargin(horizontal1, new Insets(25, 0, 0, 0));
        GridPane.setMargin(horizontal2, new Insets(25, 0, 0, 0));
        //horizontal.setPrefHeight(5);

        gridPane.add(horizontal,0,0,2,1);
        gridPane.add(horizontal1,1,0,4,1);
        gridPane.add(horizontal2,4,0,2,1);

        horizontal.setStyle("-fx-background-color: yellow;");
        horizontal1.setStyle("-fx-background-color: yellow;");
        horizontal2.setStyle("-fx-background-color: yellow;");

        Scene scene = new Scene(gridPane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
