/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import proyectofinal.vista.Buscar;
import proyectofinal.vista.PrevisualizarLibro;

/**
 * Esta clase se encarga de controlar la ventana de Ver.
 * @author raul
 */
public class CtrVer implements ActionListener{
    private Buscar b;
    private JDialog dial;
    
    /**
     * Es el constructor de la clase
     * @param b Es el JPanel que contiene los componentes de la pantalla
     * @param dial Es el JDialog que contiene al JPanel
     */
    public CtrVer(Buscar b, JDialog dial){
        this.b=b;
        this.dial=dial;
    }

    /**
     * Es el método que gestiona los botones
     * @param e Es el evento producido
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String accion;
        accion=e.getActionCommand();
        switch(accion){
            case "Ver":
                ver();
                break;
        }
    }

    /**
     * Este método se encarga de ver los detalles del componente seleccionado
     */
    private void ver() {
        try {
            AccesoDatos db=new AccesoDatos();
            int fila;
            fila=b.getFila();
            if(fila>=0){
                String nombre;
                nombre=(String)b.getTablaValue(fila, 0);
                Object[] componente=db.obtenerLibro(nombre);
                db.cerrarConexion();
                JDialog detalle=new JDialog(dial);
                detalle.setTitle(componente[0].toString());
                PrevisualizarLibro pl=new PrevisualizarLibro();
                pl.cargarDatos(componente);
                detalle.add(pl);
                detalle.setSize(500, 470);
                detalle.setLocationRelativeTo(null);
                detalle.setModal(true);
                detalle.setVisible(true);
                detalle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }else{
                JOptionPane.showMessageDialog(b, "No has seleccionado ningun libro", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CtrVer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CtrVer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Se encarga de añadir la ayuda en la ventana
     */
    public void ponLaAyuda() {
        try {
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            
            hb.enableHelpKey(dial.getContentPane(), "ver",helpset);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
}
