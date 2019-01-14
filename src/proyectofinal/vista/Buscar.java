/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.vista;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 * Esta clase es la visualización de los registros encontrados por Buscar
 * @author raul
 */


public class Buscar extends JPanel {
    
    private JTable tabla;
    private ModeloTabla dm;
    private JScrollPane scroll;
    private TableColumnModel modelColum;
    
    /**
     * Constructor de la clase
     */
    public Buscar(){
        dm=new ModeloTabla();
        tabla=new JTable(dm);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setBackground(Color.DARK_GRAY);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setDefaultRenderer(Object.class, new RenderFila());
        scroll=new JScrollPane(tabla);
        setLayout(new BorderLayout());
        
        add(scroll);        
    }
    
    /**
     * Devuelve la fila seleccionada
     * @return int - Fila seleccionada
     */
    public int getFila(){
        return tabla.getSelectedRow();
    }
 
    /**
     * Devuelve los valores contenidos en el JTable en las posiciones (x,y)
     * @param x Es la fila
     * @param y Es la columna
     * @return Object - Valor que está en esa posición
     */
    public Object getTablaValue(int x, int y){
        return tabla.getValueAt(x, y);
    }
    
    /**
     * Añade la cabecera al JTable
     * @param str Es un array de String que contiene los valores de la cabecera
     */
    public void addCabecera(String[] str) {
        dm.setColumnIdentifiers(str);
    }
    
    /**
     * Añade una fila al JTable
     * @param obj Es un array de Object con los valores a introducir 
     */
    public void addFila(Object[] obj){
        dm.addRow(obj);
    }

    /**
     * Se encanrga de modificar el tamaño de las columnas
     */
    public void modificarTamannoColumna() {
        modelColum=tabla.getColumnModel();
        modelColum.getColumn(0).setPreferredWidth(360);
        modelColum.getColumn(0).setMinWidth(300);
        modelColum.getColumn(1).setPreferredWidth(150);
        modelColum.getColumn(1).setMinWidth(150);
        modelColum.getColumn(2).setPreferredWidth(40);
        modelColum.getColumn(2).setMinWidth(40);
        modelColum.getColumn(3).setPreferredWidth(100);
        modelColum.getColumn(3).setMinWidth(110);
    }
    
}
