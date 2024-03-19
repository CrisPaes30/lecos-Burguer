package com.cristian.frontcozinha.front;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class TelaGerenciamento extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Bottom
        VBox bottomVBox = new VBox();
        bottomVBox.setPrefHeight(40.0);
        bottomVBox.setStyle("-fx-background-radius: 0 0 10 10;");

        StackPane bottomStackPane1 = new StackPane();
        bottomStackPane1.setPrefHeight(2.0);
        VBox.setVgrow(bottomStackPane1, Priority.ALWAYS);

        StackPane bottomStackPane2 = new StackPane();
        bottomStackPane2.setPrefHeight(40.0);
        bottomStackPane2.setStyle("-fx-background-color: #000000; -fx-background-radius: 10 10 10 10;");
        VBox.setVgrow(bottomStackPane2, Priority.NEVER);

        StackPane pedidosFinalizadosPane = new StackPane();
        pedidosFinalizadosPane.getChildren().add(new Text("PEDIDOS FINALIZADOS:"));
        pedidosFinalizadosPane.setAlignment(Pos.CENTER_LEFT);
        pedidosFinalizadosPane.setPadding(new Insets(0, 10, 0, 10));
        ((Text) pedidosFinalizadosPane.getChildren().get(0)).setFill(Color.WHITE);
        bottomStackPane2.getChildren().add(pedidosFinalizadosPane);

        bottomVBox.getChildren().addAll(bottomStackPane1, bottomStackPane2);
        root.setBottom(bottomVBox);

        // Center
        HBox centerHBox = new HBox();

        StackPane centerYellowStackPane = new StackPane();
        centerYellowStackPane.setStyle("-fx-background-color: rgba(255, 255, 0, 0.8); -fx-background-radius: 10;");
        centerYellowStackPane.setPrefSize(800, 400);
        centerYellowStackPane.setAlignment(Pos.CENTER);
        HBox.setMargin(centerYellowStackPane, new Insets(2.0));

        Rectangle blueBorderRectangle = new Rectangle(650, 170);
        blueBorderRectangle.setFill(Color.TRANSPARENT);
        blueBorderRectangle.setStroke(Color.rgb(0, 0, 255, 0.5));
        blueBorderRectangle.setStrokeWidth(2);
        blueBorderRectangle.setArcWidth(20);
        blueBorderRectangle.setArcHeight(20);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        // Adicionando Texts e TextFields à GridPane
        gridPane.add(new Text("NÚMERO PEDIDO:"), 0, 0);
        TextField numeroPedidoTextField = new TextField();
        numeroPedidoTextField.setPrefWidth(500); // Defina o valor desejado para a largura
        gridPane.add(numeroPedidoTextField, 1, 0);
        numeroPedidoTextField.setStyle("-fx-background-radius: 10 10 10 10;");

        gridPane.add(new Text("PEDIDO:"), 0, 1);
        TextField pedidoTextField = new TextField();
        pedidoTextField.setPrefWidth(500); // Defina o valor desejado para a largura
        gridPane.add(pedidoTextField, 1, 1);
        pedidoTextField.setStyle("-fx-background-radius: 10 10 10 10;");

        gridPane.add(new Text("COMPLEMENTO PEDIDO:"), 0, 2);
        TextField complementoPedidoTextField = new TextField();
        complementoPedidoTextField.setPrefWidth(500); // Defina o valor desejado para a largura
        gridPane.add(complementoPedidoTextField, 1, 2);
        complementoPedidoTextField.setStyle("-fx-background-radius: 10 10 10 10;");

        gridPane.add(new Text("OBSERVAÇÕES:"), 0, 3);
        TextField observacoesTextField = new TextField();
        observacoesTextField.setPrefWidth(500); // Defina o valor desejado para a largura
        gridPane.add(observacoesTextField, 1, 3);
        observacoesTextField.setStyle("-fx-background-radius: 10 10 10 10;");

        gridPane.add(new Text("ENDEREÇO:"), 0, 4);
        TextField enderecoTextField = new TextField();
        enderecoTextField.setPrefWidth(500); // Defina o valor desejado para a largura
        gridPane.add(enderecoTextField, 1, 4);
        enderecoTextField.setStyle("-fx-background-radius: 10 10 10 10;");

        centerYellowStackPane.getChildren().addAll(blueBorderRectangle, gridPane);
        StackPane.setAlignment(blueBorderRectangle, Pos.TOP_CENTER);
        StackPane.setMargin(blueBorderRectangle, new Insets(5.0));

        StackPane centerRedStackPane = new StackPane();
        centerRedStackPane.setStyle("-fx-background-color: rgba(255, 0, 0, 0.8); -fx-background-radius: 10;");
        centerRedStackPane.setPrefSize(800, 400);
        HBox.setMargin(centerRedStackPane, new Insets(2.0));

        centerHBox.getChildren().addAll(centerYellowStackPane, centerRedStackPane);
        root.setCenter(centerHBox);

        // Top
        HBox topHBox = new HBox();
        topHBox.setPrefHeight(40.0);
        topHBox.setStyle("-fx-background-radius: 10 10 0 0;");

        StackPane topStackPane1 = new StackPane();
        topStackPane1.setPrefWidth(800.0);
        topStackPane1.setStyle("-fx-background-color: #FFA500; -fx-background-radius: 10 10 10 10;");
        HBox.setHgrow(topStackPane1, Priority.ALWAYS);
        topStackPane1.getChildren().add(new Text("NOVOS PEDIDOS"));

        StackPane topStackPane2 = new StackPane();
        topStackPane2.setPrefWidth(120.0);
        HBox.setHgrow(topStackPane2, Priority.ALWAYS);

        StackPane topStackPane3 = new StackPane();
        topStackPane3.setPrefWidth(800.0);
        topStackPane3.setStyle("-fx-background-color: #FFA500; -fx-background-radius: 10 10 10 10;");
        HBox.setHgrow(topStackPane3, Priority.ALWAYS);
        topStackPane3.getChildren().add(new Text("PEDIDOS EM ANDAMENTO"));

        topHBox.getChildren().addAll(topStackPane1, topStackPane2, topStackPane3);
        root.setTop(topHBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gerenciamento de Pedidos Leco´s Burguer");
        primaryStage.show();
    }

}
