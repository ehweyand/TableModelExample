/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author evand
 */
public class ProdutoTableModel extends AbstractTableModel {
    //precisamos de uma lista para colocar os dados (produtos aqui)

    //puxar do java.util
    private List<Produto> dados = new ArrayList<>();
    private String[] colunas = {"Descrição", "QTD", "Valor"};

    @Override
    public int getRowCount() {
        return dados.size();//tamanho da lista, logo o número de rows (linhas) de dados
    }

    @Override
    public String getColumnName(int column) {//retorna o nome da Coluna, o java usa para noemar as colunas também!
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;//mesma coisa que as linhas, a quantidade de colunas
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getDescricao();
            case 1:
                return dados.get(linha).getQtd();
            case 2:
                return dados.get(linha).getValor();
        }

        return null;
    }

    public void addRow(Produto p) {//adiciona uma linha
        this.dados.add(p);
        this.fireTableDataChanged();//atualiza a tabela quando houver alteração
    }

    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);// parâmetros ~> (início, fim)
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setDescricao((String) valor);
                break;
            case 1:
                dados.get(linha).setQtd(Integer.parseInt((String) valor));
                break;
            case 2:
                dados.get(linha).setValor(Double.parseDouble((String) valor));//valor que vem do Text Field é sempre uma String
                break;
        }
        
        this.fireTableRowsUpdated(linha, linha);
    }

}
