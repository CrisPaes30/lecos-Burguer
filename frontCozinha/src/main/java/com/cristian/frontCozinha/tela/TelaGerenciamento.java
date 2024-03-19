package com.cristian.frontCozinha.tela;

import com.cristian.frontCozinha.entitites.Clientes;
import com.cristian.frontCozinha.entitites.Pedidos;
import com.cristian.frontCozinha.repository.StatusRepository;
import com.cristian.frontCozinha.services.PedidoService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Slf4j
@Component
public class TelaGerenciamento extends Application {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private StatusRepository statusRepository;
    private VBox colunaNovosPedidos;
    private VBox colunaEmAndamento; // Coluna "Em Andamento"
    private VBox colunaFinalizados; // Coluna "Finalizados"
    private final String NOVOS_PEDIDOS = "Novos Pedidos";
    private final String EM_ANDAMENTO = "Em Andamento";
    private final String FINALIZADOS = "Finalizados";
    private BorderPane root;
    private final List<OrderCard> listaCards = new ArrayList<>();
    HBox colunaPedidoEncontrado = createHorizontalColumn("Pedido Encontrado");
    HBox colunaRecentes = createFooterColumn("Pedidos Recentes");


    @Override
    public void start(Stage primaryStage) {
        this.root = new BorderPane();
        root.setPadding(new Insets(10));

        // Criando a barra de pesquisa
        HBox searchBar = new HBox();
        searchBar.setMaxWidth(250);
        searchBar.setPadding(new Insets(10));
        searchBar.setAlignment(Pos.CENTER_LEFT);

        searchBar.setStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 10px;");
        TextField searchField = new TextField();
        searchField.setPromptText("Digite o número do Pedido");
        searchField.setMinWidth(150);
        searchField.setPrefWidth(200);
        Button searchButton = new Button("Buscar");
        searchButton.setPrefWidth(70);
        searchButton.setMinWidth(Control.USE_PREF_SIZE); // Definindo a largura mínima para o tamanho preferencial
        searchField.setMinHeight(Control.USE_PREF_SIZE);

        colunaPedidoEncontrado = createHorizontalColumn("Pedido Encontrado");

        searchButton.setOnAction(event -> {
            try {
                String textoPedido = searchField.getText().trim(); // Obtém o texto do campo de pesquisa e remove espaços em branco no início e no final
                if (!textoPedido.isEmpty()) { // Verifica se o campo não está vazio
                    // Verifica se já há um card de pedido encontrado e remove antes de adicionar um novo
                    if (!searchBar.getChildren().contains(colunaPedidoEncontrado)) {
                        searchBar.getChildren().add(colunaPedidoEncontrado);
                    } else {
                        searchBar.getChildren().remove(colunaPedidoEncontrado);
                        searchBar.getChildren().add(colunaPedidoEncontrado);
                    }

                    Long numeroPedido = Long.parseLong(textoPedido);
                    Pedidos pedidos = pedidoService.searchPedido(numeroPedido);
                    retornaBuscaPedidos(colunaPedidoEncontrado, String.valueOf(pedidos.getNumeroPedido()));

                    searchField.clear();
                }
            } catch (NumberFormatException e) {
                // Lidar com o caso em que o número digitado não é um Long válido
                log.info("Número de pedido inválido: ");
            }
        });


        searchField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                searchButton.fire(); // Aciona o evento do botão de busca ao pressionar Enter
            }
        });

        Label title = new Label("Gerenciamento de Pedidos");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        searchBar.getChildren().addAll(searchButton, searchField);

        VBox searchBox = new VBox(title, searchBar);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        root.setTop(searchBox);

        // Criando as colunas para os pedidos
        colunaNovosPedidos = createOrderColumn("Novos Pedidos");
        colunaEmAndamento = createOrderColumn("Em Andamento"); // Atribuir a coluna "Em Andamento" ao campo da classe
        colunaFinalizados = createOrderColumn("Finalizados"); // Atribuir a coluna "Finalizados" ao campo da classe

        // Adicionando ScrollPanes com largura máxima para manter o espaçamento
        ScrollPane newOrdersScrollPane = createScrollPane(colunaNovosPedidos);
        ScrollPane inProgressScrollPane = createScrollPane(colunaEmAndamento);
        ScrollPane completedScrollPane = createScrollPane(colunaFinalizados);

        // Adicionando as colunas ao GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10); // Espaçamento horizontal entre as colunas
        gridPane.add(newOrdersScrollPane, 0, 0);
        gridPane.add(inProgressScrollPane, 1, 0);
        gridPane.add(completedScrollPane, 2, 0);

        // Definindo as larguras das colunas para que sejam iguais
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(33.33); // Cada coluna terá 33.33% da largura total
        gridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);

        // Adicionando o GridPane ao centro do BorderPane
        root.setCenter(gridPane);

        // Adicionando a coluna horizontal no rodapé
        root.setBottom(colunaRecentes);
        colunaRecentes.setAlignment(Pos.CENTER_LEFT);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Gerenciamento de Pedidos");
        primaryStage.show();
    }

    public class OrderCard extends VBox {
        private String numeroPedido;
        private VBox parentColumn;
        private Timeline timeline;
        private Label tempoDecorridoLabel;
        private int segundos;

        public OrderCard(VBox parentColumn, String numero, String lanche, String complemento,
                         String observacao, String endereco, String tempoTotal) {
            this.parentColumn = parentColumn;
            this.numeroPedido = numero;
            this.segundos = 0;

            setPadding(new Insets(10));
            setSpacing(5);
            setAlignment(Pos.TOP_LEFT);
            setStyle("-fx-background-color: white; -fx-background-radius: 10px;");

            Label numeroPedidoLabel = new Label(numero);
            Label lanchePedido = new Label(lanche);
            Label complementoLanche = new Label(complemento);
            Label observacaoPedido = new Label(observacao);
            Label enderecoEndereco = new Label(endereco);
            Label tempoTotalLabel = new Label(tempoTotal);

            HBox numeroEtempoBox = new HBox();
            numeroEtempoBox.getChildren().addAll(numeroPedidoLabel);
            numeroEtempoBox.setAlignment(Pos.CENTER_LEFT);
            numeroEtempoBox.setSpacing(200);
            numeroEtempoBox.setPadding(new Insets(0, 10, 0, 0));

            getChildren().addAll(numeroEtempoBox, lanchePedido, complementoLanche, observacaoPedido, enderecoEndereco, tempoTotalLabel);

            // Adiciona o botão apenas se necessário
            if (!parentColumn.equals(colunaFinalizados)) {
                Button moveButton;
                if (parentColumn.equals(colunaEmAndamento)) {
                    String status = "FINALIZADO";
                    moveButton = new Button("Finalizar Pedido");
                    moveButton.setOnAction(event -> {
                        String s = extrairNumeroPedido(numero);
                        Long numeroPedido = Long.parseLong(s);
                        String tPedidos = atualizarTempoDecorrido();
                        pedidoService.salvarTempoRelogioEmAndamento(numeroPedido, tPedidos);
                        parentColumn.getChildren().remove(this);
                        pedidoService.atualizaStatus(numeroPedido, status);

                        String tempo = pedidoService.buscaTempoDoPedido(numeroPedido);

                        adicionarPedido(colunaFinalizados, numero, lanche, complemento, observacao, endereco, "Tempo Total Pedido: " + tempo);
                        adicionarPedidoRecente(colunaRecentes, "Pedido: " + s);
                    });
                } else {
                    String status = "EM ANDAMENTO";
                    moveButton = new Button("Mover para Em Andamento");
                    moveButton.setOnAction(event -> {
                        String s = extrairNumeroPedido(numero);
                        Long numeroPedido = Long.parseLong(s);

                        moverPedido(parentColumn, colunaEmAndamento, numeroPedido, lanche, complemento, observacao, endereco, status);
                        parentColumn.getChildren().remove(this);
                        pedidoService.atualizaStatus(numeroPedido, status);
                        adicionarPedido(colunaEmAndamento, numero, lanche, complemento, observacao, endereco, null);
                        String tPedidos = atualizarTempoDecorrido();
                        pedidoService.salvarTempoRelogioNovosPedidos(numeroPedido, tPedidos);
                    });
                }

                getChildren().add(moveButton);
            }
            // Se não for a coluna "Finalizados", iniciamos o timer e definimos o tempoDecorridoLabel
            if (!parentColumn.equals(colunaFinalizados)) {
                iniciarTimer();
                tempoDecorridoLabel = new Label(); // Inicializa o tempoDecorridoLabel
                numeroEtempoBox.getChildren().add(tempoDecorridoLabel); // Adiciona o tempoDecorridoLabel à caixa de número e tempo
            }
        }

        private void iniciarTimer() {
            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        segundos++;
                        atualizarTempoDecorrido();
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        public String getNumeroPedido() {
            return numeroPedido;
        }

        public void cancelTimer() {
            timeline.stop();
        }

        public void pauseTimer() {
            timeline.pause();
        }

        private String atualizarTempoDecorrido() {
            int minutos = segundos / 60;
            int segRestantes = segundos % 60;
            tempoDecorridoLabel.setStyle("-fx-background-color: white; -fx-background-radius: 5px; -fx-border-color: blue; -fx-border-width: 1px; -fx-border-radius: 5px;");
            tempoDecorridoLabel.setText("Tempo: " + minutos + ":" + String.format("%02d", segRestantes));
            String tempo = minutos + ":" + String.format("%02d", segRestantes);

            if (minutos >= 30) {
                setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            } else if (minutos >= 15) {
                setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }

            return tempo;
        }
    }

    private ScrollPane createScrollPane(VBox column) {
        ScrollPane scrollPane = new ScrollPane(column);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(550);
        scrollPane.setMaxWidth(500); // Definindo a largura máxima do ScrollPane para manter o espaçamento
        return scrollPane;
    }

    private VBox createOrderColumn(String title) {
        VBox column = new VBox();
        column.setPadding(new Insets(10));
        column.setSpacing(10);
        column.setAlignment(Pos.TOP_CENTER);
        column.setStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 10px;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold;");
        column.getChildren().add(titleLabel);

        column.setStyle("-fx-background-color: #ADD8E6; -fx-background-radius: 10px;");

        switch (title) {
            case EM_ANDAMENTO -> {
                colunaEmAndamento = column;
                List<Pedidos> emAndamento = pedidoService.buscaPedidosEmAndamento();
                for (Pedidos pedido : emAndamento) {
                    String observacao = "";
                    if (pedido.getObservacao() != null) {
                        observacao = pedido.getObservacao();
                    }
                    // Verificar se o pedido já está sendo exibido em algum outro lugar
                    boolean pedidoJaExibido = pedidoJaExibido(pedido, colunaEmAndamento);
                    if (!pedidoJaExibido) {
                        adicionarPedido(column, // Passando a referência do VBox correspondente
                                "Pedido: " + pedido.getNumeroPedido(),
                                "Lanche: " + pedido.getNomeProduto(),
                                "Complemento: " + pedido.getComplementoLanche(),
                                "Observação: " + observacao,
                                "Endereço: " + pedidoService.dadosClientes(pedido.getNumeroPedido()).getEndereco(),
                                null);
                    }
                }
            }
            case NOVOS_PEDIDOS -> {
                Thread monitorNovosPedidosThread = new Thread(() -> {
                    while (true) {
                        // Recuperar pedidos pendentes apenas uma vez
                        Platform.runLater(() -> {
                            // Recuperar pedidos pendentes apenas uma vez
                            List<Pedidos> pedidosPendentes = pedidoService.processarPedidosPendentes();

                            for (Pedidos pedido : pedidosPendentes) {
                                // Verificar se o pedido já está sendo exibido em algum outro lugar
                                boolean pedidoJaExibido = pedidoJaExibido(pedido, colunaNovosPedidos);
                                String observacao = "";
                                if (pedido.getObservacao() != null) {
                                    observacao = pedido.getObservacao();
                                }
                                if (!pedidoJaExibido) {
                                    adicionarPedido(column, // Passando a referência do VBox correspondente
                                            "Pedido: " + pedido.getNumeroPedido(),
                                            "Lanche: " + pedido.getNomeProduto(),
                                            "Complemento: " + pedido.getComplementoLanche(),
                                            "Observação: " + observacao,
                                            "Endereço: " + pedidoService.dadosClientes(pedido.getNumeroPedido()).getEndereco(),
                                            null);
                                }
                            }
                        });
                        // Aguardar um tempo antes de verificar novamente
                        try {
                            sleep(180000); // Verificar a cada 3 minutos
                        } catch (InterruptedException e) {
                            log.error("Erro ao verificar novos pedidos: " + e.getMessage());
                        }
                    }
                });
                monitorNovosPedidosThread.setDaemon(true);
                monitorNovosPedidosThread.start();
            }
            case FINALIZADOS -> {
                colunaFinalizados = column;
                // Recuperar pedidos finalizados
                List<Pedidos> finalizadosDoDia = pedidoService.buscaPedidosFinalizadosDoDia();
                for (Pedidos pedido : finalizadosDoDia) {
                    // Verificar se o pedido já está sendo exibido em algum outro lugar
                    boolean pedidoJaExibido = pedidoJaExibido(pedido, colunaFinalizados);
                    if (!pedidoJaExibido) {
                        String observacao = "";
                        if (pedido.getObservacao() != null) {
                            observacao = pedido.getObservacao();
                        }
                        adicionarPedido(column, // Passando a referência do VBox correspondente
                                "Pedido: " + pedido.getNumeroPedido(),
                                "Lanche: " + pedido.getNomeProduto(),
                                "Complemento: " + pedido.getComplementoLanche(),
                                "Observação: " + observacao,
                                "Endereço: " + pedidoService.dadosClientes(pedido.getNumeroPedido()).getEndereco(),
                                null);
                    }
                }
            }
        }
        GridPane.setConstraints(column, 0, 0);
        GridPane.setColumnSpan(column, 3);
        return column;
    }

    private void adicionarPedido(VBox column, String numero, String lanche, String complemento,
                                 String observacao, String endereco, String tempoTotal) {
        OrderCard orderCard = new OrderCard(column, numero, lanche, complemento, observacao, endereco, tempoTotal);
        column.getChildren().add(orderCard);
    }

    private boolean pedidoJaExibido(Pedidos pedido, VBox colunas) {
        // Verificar se o pedido já está sendo exibido na coluna de Novos Pedidos
        if (colunas.equals(colunaNovosPedidos) || colunas.equals(colunaEmAndamento)) {
            for (Node node : colunas.getChildren()) {
                if (node instanceof OrderCard) {
                    OrderCard orderCard = (OrderCard) node;
                    String numeroPedido = orderCard.getNumeroPedido();
                    numeroPedido = extrairNumeroPedido(numeroPedido);
                    if (numeroPedido.equals(pedido.getNumeroPedido())) {
                        return true; // O pedido já está exibido na coluna
                    } else {
                        adicionarOrderCard(orderCard);

                        for (OrderCard orderCard1 : listaCards) {
                            String numeroPedido1 = orderCard1.getNumeroPedido();
                            numeroPedido1 = extrairNumeroPedido(numeroPedido1);

                            Long n = Long.parseLong(numeroPedido1);
                            if (n.equals(pedido.getNumeroPedido())) {
                                return true; // O pedido já está exibido na coluna
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void moverPedido(VBox origemColumn, VBox destinoColumn, Long numeroPedidoInt, String lanche, String complemento, String observacao, String endereco, String statusDestino) {
        for (Node node : origemColumn.getChildren()) {
            if (node instanceof OrderCard) {
                OrderCard orderCard = (OrderCard) node;
                String s = extrairNumeroPedido(orderCard.numeroPedido);
                if (s.equals(numeroPedidoInt)) {
                    origemColumn.getChildren().remove(orderCard); // Remover da coluna original
                    adicionarPedido(destinoColumn, orderCard.getNumeroPedido(), lanche, complemento,
                            observacao, endereco, null);
                    pedidoService.atualizaStatus(numeroPedidoInt, statusDestino);
                    break;
                }
            }
        }
    }

    private String extrairNumeroPedido(String numero) {
        String numeroPedido = numero.trim();
        while (numeroPedido.startsWith("Pedido:")) {
            numeroPedido = numeroPedido.replaceFirst("Pedido:", "").trim();
        }
        return numeroPedido;
    }

    private void adicionarPedidoRecente(HBox column, String numeroPedido) {
        RecentOrderCard recentOrderCard = new RecentOrderCard(numeroPedido);
        column.getChildren().add(recentOrderCard);
    }

    private class RecentOrderCard extends VBox {
        private String numeroPedido;

        public RecentOrderCard(String numeroPedido) {
            this.numeroPedido = numeroPedido;
            setPadding(new Insets(10));
            setSpacing(5);
            setAlignment(Pos.TOP_LEFT);
            setStyle("-fx-background-color: white; -fx-background-radius: 10px;");

            Label numeroPedidoLabel = new Label(numeroPedido);

            getChildren().addAll(numeroPedidoLabel);
        }
    }

    private HBox createFooterColumn(String title) {
        HBox footerColumn = new HBox();
        footerColumn.setPadding(new Insets(10));
        footerColumn.setSpacing(10);
        footerColumn.setAlignment(Pos.CENTER);
        footerColumn.setStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 10px;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold;");
        footerColumn.getChildren().add(titleLabel);

        return footerColumn;
    }

    private HBox createHorizontalColumn(String title) {
        HBox horizontalColumn = new HBox();
        horizontalColumn.setPadding(new Insets(10));
        horizontalColumn.setSpacing(10);
        horizontalColumn.setAlignment(Pos.CENTER_LEFT); // Ajuste para alinhamento à esquerda
        horizontalColumn.setStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 10px;");

        horizontalColumn.setMinWidth(600); // Definindo uma largura mínima

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold;");
        horizontalColumn.getChildren().add(titleLabel);

        return horizontalColumn;
    }

    private void adiconaPedidoReader(HBox column, String nomeCliente, String numeroPedido, String lanche, String status) {
        SearchOrderCard searchOrderCard = new SearchOrderCard(nomeCliente, numeroPedido, lanche, status);
        column.getChildren().add(searchOrderCard);
    }

    private class SearchOrderCard extends VBox {
        private String numeroPedido;

        private Runnable onCloseAction;

        public void setOnCloseAction(Runnable action) {
            this.onCloseAction = action;
        }

        public SearchOrderCard(String nomeCliente, String numeroPedido, String lanche, String status) {
            this.numeroPedido = numeroPedido;
            setPadding(new Insets(10, 20, 10, 20));
            setSpacing(5);
            setAlignment(Pos.TOP_LEFT);
            setStyle("-fx-background-color: white; -fx-background-radius: 10px;");

            Label nomeClienteLabel = new Label(nomeCliente);
            Label numeroPedidoLabel = new Label(numeroPedido);
            Label lancheLabel = new Label(lanche);
            Label statusLabel = new Label(status);

            Button closeButton = new Button("Fechar");
            closeButton.setStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 10px;-fx-font-size: 10px;");
            closeButton.setOnAction(event -> {
                // Remover o card quando o botão "Fechar" for clicado
                ((HBox) getParent()).getChildren().remove(this);
                // Remover a coluna "Pedido Encontrado" do BorderPane
                removerColunaPedidoEncontrado(colunaPedidoEncontrado);
            });

            HBox headerBox = new HBox(new Region(), closeButton);
            headerBox.setAlignment(Pos.CENTER_RIGHT);
            headerBox.setHgrow(new Region(), Priority.ALWAYS);

            getChildren().addAll(headerBox, nomeClienteLabel, numeroPedidoLabel, lancheLabel, statusLabel);
        }
    }

    private void removerColunaPedidoEncontrado(HBox root) {
        HBox searchBox = (HBox) root.getParent();
        searchBox.getChildren().remove(colunaPedidoEncontrado);
    }

    private void retornaBuscaPedidos(HBox column, String numero) {

        if(!numero.equals("null")) {
            Clientes clientes = pedidoService.dadosClientes(Long.parseLong(numero));
            String status = pedidoService.buscaStatus(clientes.getNumeroPedido());
            Pedidos pedidos = pedidoService.searchPedido(clientes.getNumeroPedido());

            if (clientes != null && status != null && pedidos != null) {
                adiconaPedidoReader(column,
                        "Nome: " + clientes.getNomeCliente(),
                        "Pedido: " + clientes.getNumeroPedido(),
                        "Lanche: " + pedidos.getNomeProduto(),
                        "Status: " + status);
            }
            // Adicionando um listener para remover a coluna "Pedido Encontrado" quando o botão "Fechar" for clicado
            for (Node node : column.getChildren()) {
                if (node instanceof SearchOrderCard) {
                    SearchOrderCard searchOrderCard = (SearchOrderCard) node;
                    searchOrderCard.setOnCloseAction(() -> column.getChildren().remove(searchOrderCard));
                }
            }
        }else {
            adiconaPedidoReader(column,
                    "Nome: " ,
                    "Pedido: Não Encontrado",
                    "Lanche: ",
                    "Status: ");
        }
    }

    private void adicionarOrderCard(OrderCard orderCard) {
        listaCards.add(orderCard);
    }

}