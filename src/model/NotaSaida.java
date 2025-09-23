/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ghgen
 */
public class NotaSaida {
    private int id_nota;
    private int id_cliente;
    private int qtdVendida;
    private LocalDate data_venda;

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getQuantidadeVendida() {
        return qtdVendida;
    }

    public void setQuantidadeVendida(int qV) {
        this.qtdVendida = qV;
    }

    public LocalDate getData_venda() {
        return data_venda;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }
    
    
}
