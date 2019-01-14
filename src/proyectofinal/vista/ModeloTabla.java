/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.vista;

import javax.swing.table.DefaultTableModel;

/**
 * Es el modelo personalizado de nuestra tabla
 * @author raul
 */
public class ModeloTabla extends DefaultTableModel{
    
    private Class[] tipoColumn={String.class, String.class, String.class, String.class};
    
    /**
     * Constructor de la clase
     */
    public ModeloTabla(){
        super();
    }
    
    /**
     * Devuelve el tipo de la columna
     * @param columnIndex Indice de la columna
     * @return Devuelve el Class de la columna
     */
    public Class getColumnClass(int columnIndex) {
        return tipoColumn[columnIndex];
    }

    /**
     * Método que usamos para no hacer editable las celdas
     * @param row Fila
     * @param column Columna
     * @return Devuelve siempre false
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
