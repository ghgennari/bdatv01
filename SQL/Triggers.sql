CREATE TRIGGER update_valor
ON ItensNotas
AFTER INSERT
AS
BEGIN
    UPDATE NotaSaida
    SET valorVenda = valorVenda + (p.preco * i.qtdVenda)
    FROM NotaSaida n
    INNER JOIN inserted i ON n.id_nota = i.id_nota
    INNER JOIN Produto p ON p.id_produto = i.id_produto;
END;


CREATE TRIGGER update_estoque
ON ItensNotas
AFTER INSERT
AS
BEGIN
    DECLARE @estoque INT, @qtdVenda INT, @id_produto INT, @id_nota INT, @preco DECIMAL(10,2);

    SELECT 
        @id_produto = i.id_produto,
        @qtdVenda = i.qtdVenda,
        @id_nota = i.id_nota,
        @estoque = p.qtd_estoque,
        @preco = p.preco
    FROM inserted i
    JOIN Produto p ON p.id_produto = i.id_produto;

    IF @estoque < @qtdVenda
    BEGIN
        PRINT('Estoque indisponível! Disponível: ' + CAST(@estoque AS VARCHAR) + ' | Solicitado: ' + CAST(@qtdVenda AS VARCHAR));
        ROLLBACK;
    END

    UPDATE Produto
    SET qtd_estoque = qtd_estoque - i.qtdVenda
    FROM Produto p
    JOIN inserted i ON p.id_produto = i.id_produto;

    PRINT('Venda Registrada. Novo Estoque: ' + CAST(@estoque - @qtdVenda AS VARCHAR));

END;