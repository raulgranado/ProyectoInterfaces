/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.vista;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Es la barra de herramientas de la pantalla Ver
 * @author raul
 */
public class VerTool extends JToolBar {
    private JButton ver;
    
    /**
     * Constructor de la clase
     */
    public VerTool(){
        ver=new JButton(new ImageIcon(getClass().getResource("/proyectofinal/img/icon/detalle.png")));
        ver.setToolTipText("Ver libro");
        add(ver);
        this.setFloatable(false);
    }
    
    /**
     * Añade el controlador a los botones
     * @param l Es el controlador
     */
    public void addControlador(ActionListener l){
        ver.setActionCommand("Ver");
        ver.addActionListener(l);
    }
}
