/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.vista;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Esta clase es el Menú de la ventana principal
 * @author raul
 */
public class Menu extends JMenuBar{
    private JMenu archivo, opciones, buscar, ayuda;
    private JMenuItem salir, buscarNombre, buscarGenero, buscarAutor, verTodo, annadir,ayudame, nosotros, manual; 
    
    /**
     * Constructor de la clase
     */
    public Menu(){
        archivo=new JMenu("Archivo");
        opciones=new JMenu("Opciones");
        buscar=new JMenu("Buscar por...");
        ayuda=new JMenu("Ayuda");
        
        salir=new JMenuItem("Salir");
        buscarNombre=new JMenuItem("Nombre");
        buscarGenero=new JMenuItem("Genero");
        buscarAutor=new JMenuItem("Autor");
        annadir=new JMenuItem("Añadir");
        verTodo=new JMenuItem("Ver todo");
        ayudame=new JMenuItem("Ayuda");
        nosotros=new JMenuItem("Sobre nosotros");
        manual=new JMenuItem("Manual");
        
        archivo.setMnemonic('A');
        opciones.setMnemonic('O');
        buscar.setMnemonic('B');
        salir.setMnemonic('S');
        buscarNombre.setMnemonic('N');
        buscarGenero.setMnemonic('G');
        buscarAutor.setMnemonic('T');
        annadir.setMnemonic('D');
        verTodo.setMnemonic('V');
        ayuda.setMnemonic('D');
        ayudame.setMnemonic('Y');
        nosotros.setMnemonic('N');
        manual.setMnemonic('M');
        
        archivo.add(salir);
        
        buscar.add(buscarNombre);
        buscar.add(buscarAutor);
        buscar.add(buscarGenero);
        
        ayuda.add(ayudame);
        ayuda.add(manual);
        ayuda.add(nosotros);
        
        opciones.add(verTodo);
        opciones.add(buscar);
        opciones.add(annadir);
        
        add(archivo);
        add(opciones);
        add(ayuda);
    }
    
    /**
     * Añade el controlador a los JMenuItems
     * @param l Es el controlador
     */
    public void addControlador(ActionListener l){
        verTodo.setActionCommand("VerTodo");
        verTodo.addActionListener(l);
        buscarNombre.setActionCommand("bNombre");
        buscarNombre.addActionListener(l);
        buscarAutor.setActionCommand("bAutor");
        buscarAutor.addActionListener(l);
        buscarGenero.setActionCommand("bGenero");
        buscarGenero.addActionListener(l);
        annadir.setActionCommand("Añadir");
        annadir.addActionListener(l);
        salir.setActionCommand("Salir");
        salir.addActionListener(l);
        ayudame.setActionCommand("Ayuda");
        ayudame.addActionListener(l);
        nosotros.setActionCommand("Nosotros");
        nosotros.addActionListener(l);
        manual.addActionListener(l);
        manual.setActionCommand("Manual");
    }
    
    /**
     * Método que devuelve el JMenuItem
     * @return JMenuItem devuelto
     */
    public JMenuItem getJMenuItem(){
        return ayudame;
    }

}
