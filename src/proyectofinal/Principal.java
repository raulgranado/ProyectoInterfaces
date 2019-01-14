/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import proyectofinal.controlador.CtrPrincipal;
import proyectofinal.vista.MainTool;
import proyectofinal.vista.Menu;
import proyectofinal.vista.VistaPrincipal;

/**
 *Clase principal de la aplicación. Gestiona el JFrame
 * @author raul
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        JFrame v=new JFrame("Libreria digital");
        VistaPrincipal p=new VistaPrincipal();
        Menu menu=new Menu();
        CtrPrincipal ctrPrincipal=new CtrPrincipal(p,v,menu);
        ctrPrincipal.ponLaAyuda();
        MainTool tool=new MainTool();
        tool.addControlador(ctrPrincipal);
        menu.addControlador(ctrPrincipal);
        v.setJMenuBar(menu);
        v.add(tool,BorderLayout.NORTH);
        p.addControlador(ctrPrincipal);
        p.addControladorTeclado(ctrPrincipal);
        v.add(p);
        v.setSize(600,500);
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
    }
    
}
