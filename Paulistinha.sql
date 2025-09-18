CREATE TABLE Produto (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200),
    preco DECIMAL(10,2) NOT NULL,
    qtd_estoque INT NOT NULL
);

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    email VARCHAR(100),
    telefone VARCHAR(20)
);

CREATE TABLE NotaSaida (
    id_nota INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    valorVenda INT NOT NULL,
    data_venda DATE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE ItensNotas(
	id_nota INT NOT NULL,
    id_produto INT NOT NULL,
    qtdVenda INT NOT NULL,
    FOREIGN KEY (id_nota) REFERENCES NotaSaida(id_nota),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);


