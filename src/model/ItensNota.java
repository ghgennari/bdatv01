/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ghgen
 */
public class ItensNota {
    private int id_nota;
    private int id_produto;
    private int qtdVenda;
    private String NomeProduto;
    private String nomeCliente;

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(int qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public void setNomeProduto(String string) {
        NomeProduto = string;
    }
    
    public String getNomeProduto(){
        return NomeProduto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String S) {
        nomeCliente = S;
    }
    
    
}
