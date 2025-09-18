INSERT INTO Cliente (nome, endereco, email, telefone)
VALUES ('João Silva', 'Rua A, 123', 'joao@email.com', '1199999999'),
       ('Maria Souza', 'Av B, 456', 'maria@email.com', '1188888888');

INSERT INTO Produto (nome, descricao, preco, qtd_estoque)
VALUES ('Mouse Gamer', 'Mouse RGB 7200dpi', 150.00, 10),
       ('Teclado Mecânico', 'Teclado Switch Blue', 250.00, 5),
       ('Monitor 24"', 'Monitor Full HD', 900.00, 3);

INSERT INTO NotaSaida (id_cliente, valorVenda, data_venda)
VALUES (2, 0, GETDATE());

INSERT INTO ItensNotas (id_nota, id_produto, qtdVenda)
VALUES (6, 1, 1);

INSERT INTO ItensNotas (id_nota, id_produto, qtdVenda)
VALUES (2, 2, 2);

SELECT * FROM Produto;

SELECT * FROM NotaSaida;

SELECT * FROM ItensNotas;

