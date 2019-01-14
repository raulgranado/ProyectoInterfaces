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
 * Esta clase es la barra de herramientas de la ventana principal
 * @author raul
 */
public class MainTool extends JToolBar{
    private JButton annadir, ver;
    
    /**
     * Constructor de la clase
     */
    public MainTool(){
        annadir=new JButton(new ImageIcon(getClass().getResource("/proyectofinal/img/icon/add.png")));
        ver=new JButton(new ImageIcon(getClass().getResource("/proyectofinal/img/icon/ver.png")));
        annadir.setToolTipText("Añadir");
        ver.setToolTipText("Ver todo");
        add(annadir);
        add(ver);
        this.setFloatable(false);
        
    }
    
    /**
     * Añade el controlador a los botones
     * @param l Es el controlador
     */
    public void addControlador(ActionListener l){
        annadir.setActionCommand("Añadir");
        annadir.addActionListener(l);
        ver.setActionCommand("VerTodo");
        ver.addActionListener(l);
    }
}
