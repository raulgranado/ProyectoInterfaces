/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Esta clase se encarga de darle color a las filas
 * @author raul
 */
public class RenderFila extends DefaultTableCellRenderer{
    
    private Color color;
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
            boolean hasFocus,int row,int column){
        
        super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        color=new Color(212, 212, 212);
       
       
        if ( row%2==0 ){
            this.setOpaque(true);
            this.setBackground(color);
            this.setForeground(Color.BLACK);
        } else {
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        return this;
    }
    
}
