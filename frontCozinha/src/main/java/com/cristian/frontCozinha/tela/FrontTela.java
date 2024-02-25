package com.cristian.frontCozinha.tela;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;



public class FrontTela extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criando o contêiner principal BorderPane
        BorderPane root = new BorderPane();

        // Configurando a parte superior do BorderPane
        HBox topHBox = new HBox();
        topHBox.setPrefHeight(40.0);
        topHBox.setStyle("-fx-background-radius: 10 10 0 0;");

        // Criando o primeiro StackPane da parte superior
        StackPane topStackPane1 = new StackPane();
        topStackPane1.setPrefWidth(800.0);
        topStackPane1.setStyle("-fx-background-color: #FFA500; -fx-background-radius: 10 10 10 10;");
        HBox.setHgrow(topStackPane1, javafx.scene.layout.Priority.ALWAYS);

        // Adicionando o texto "Novos Pedidos" à parte superior esquerda
        Text novoPedidosText = new Text("NOVOS PEDIDOS");
        novoPedidosText.setFill(Color.BLACK); // Define a cor do texto
        StackPane.setAlignment(novoPedidosText, Pos.CENTER); // Alinha o texto
        topStackPane1.getChildren().add(novoPedidosText);

        // Criando o segundo StackPane da parte superior
        StackPane topStackPane2 = new StackPane();
        topStackPane2.setPrefWidth(120.0);
        HBox.setHgrow(topStackPane2, javafx.scene.layout.Priority.ALWAYS);

        // Criando o terceiro StackPane da parte superior
        StackPane topStackPane3 = new StackPane();
        topStackPane3.setPrefWidth(800.0);
        topStackPane3.setStyle("-fx-background-color: #FFA500; -fx-background-radius: 10 10 10 10;");
        HBox.setHgrow(topStackPane3, javafx.scene.layout.Priority.ALWAYS);

        // Adicionando o texto "Pedidos em Andamento" à parte superior direita
        Text pedidosAndamento = new Text("PEDIDOS EM ANDAMENTO");
        pedidosAndamento.setFill(Color.BLACK); // Define a cor do texto
        StackPane.setAlignment(pedidosAndamento, Pos.CENTER); // Alinha o texto
        topStackPane3.getChildren().add(pedidosAndamento);

        // Adicionando os elementos à parte superior
        topHBox.getChildren().addAll(topStackPane1, topStackPane2, topStackPane3);
        root.setTop(topHBox);

        // Configurando a parte inferior do BorderPane
        VBox bottomVBox = new VBox();
        bottomVBox.setPrefHeight(40.0);
        bottomVBox.setStyle("-fx-background-radius: 0 0 10 10;");

        StackPane bottomStackPane1 = new StackPane();
        bottomStackPane1.setPrefHeight(2.0);
        VBox.setVgrow(bottomStackPane1, javafx.scene.layout.Priority.ALWAYS);

        StackPane bottomStackPane2 = new StackPane();
        bottomStackPane2.setPrefHeight(40.0);
        bottomStackPane2.setStyle("-fx-background-color: #000000; -fx-background-radius: 10 10 10 10;");
        VBox.setVgrow(bottomStackPane2, javafx.scene.layout.Priority.NEVER);

        // Criando um StackPane para envolver o texto "Pedidos Finalizados"
        StackPane pedidosFinalizadosPane = new StackPane();
        pedidosFinalizadosPane.getChildren().add(new Text("PEDIDOS FINALIZADOS:"));
        pedidosFinalizadosPane.setAlignment(Pos.CENTER_LEFT);
        pedidosFinalizadosPane.setPadding(new Insets(0, 10, 0, 10)); // Aplicando a margem

        // Definindo a cor do texto
        ((Text) pedidosFinalizadosPane.getChildren().get(0)).setFill(Color.WHITE);

        bottomStackPane2.getChildren().add(pedidosFinalizadosPane);

        bottomVBox.getChildren().addAll(bottomStackPane1, bottomStackPane2);
        root.setBottom(bottomVBox);

        // Configurando o centro do BorderPane
        HBox centerHBox = new HBox();
        centerHBox.setAlignment(javafx.geometry.Pos.CENTER);
        centerHBox.setPrefHeight(200.0);
        centerHBox.setPrefWidth(200.0);
        centerHBox.setStyle("-fx-background-radius: 0 0 10 10;");

        StackPane centerStackPane1 = new StackPane();
        centerStackPane1.setPrefWidth(800.0);
        centerStackPane1.setStyle("-fx-background-color: rgba(255, 255, 0, 0.8); -fx-background-radius: 10;");
        HBox.setHgrow(centerStackPane1, javafx.scene.layout.Priority.ALWAYS);
        HBox.setMargin(centerStackPane1, new Insets(2.0));

        StackPane centerStackPane2 = new StackPane();
        centerStackPane2.setPrefWidth(120.0);
        HBox.setHgrow(centerStackPane2, javafx.scene.layout.Priority.ALWAYS);

        StackPane centerStackPane3 = new StackPane();
        centerStackPane3.setPrefWidth(800.0);
        centerStackPane3.setStyle("-fx-background-color: rgba(255, 0, 0, 0.8); -fx-background-radius: 10;");
        HBox.setHgrow(centerStackPane3, javafx.scene.layout.Priority.ALWAYS);
        HBox.setMargin(centerStackPane3, new Insets(2.0));

        centerHBox.getChildren().addAll(centerStackPane1, centerStackPane2, centerStackPane3);
        root.setCenter(centerHBox);

        // Configurando a cena e exibindo a janela
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gerenciamento de Pedidos Leco´s Burguer");
        primaryStage.show();
    }

}
