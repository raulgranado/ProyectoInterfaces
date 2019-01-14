/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.controlador;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyectofinal.vista.AnnadirLibro;
import proyectofinal.vista.Buscar;
import proyectofinal.vista.Menu;
import proyectofinal.vista.Nosotros;
import proyectofinal.vista.VerTool;
import proyectofinal.vista.VistaPrincipal;

/**
 *Clase que controla la pantalla principal y el menú.
 * @author raul
 */
public class CtrPrincipal extends KeyAdapter implements ActionListener{
    private VistaPrincipal v;
    private Buscar buscar;
    private JFrame ventana;
    private Menu menu;
    
    /**
     * Constructor de la clase
     * @param v Es el JPanel que contiene los componentes de la pantalla
     * @param ventana Es el JFrame que contiene el JPanel
     * @param menu Es el menú de nuestra ventana
     */
    public CtrPrincipal(VistaPrincipal v,JFrame ventana, Menu menu){
        this.v=v;
        this.ventana=ventana;
        this.menu=menu;
        buscar=new Buscar();
    }   
    
    /**
     * Gestiona el evento de los botones y los JMenuItems
     * @param e Es el evento ocurrido
     */
    public void actionPerformed(ActionEvent e) {
        String accion;
        accion=e.getActionCommand();
        switch(accion){
            case "Buscar":
                buscar();
                break;
            case "VerTodo":
                verTodo();
                break;
            case "Añadir":
                annadir();
                break;
            case "bNombre":
                buscarPor("NOMBRE");
                break;
            case "bAutor":
                buscarPor("AUTOR");
                break;
            case "bGenero":
                buscarPor("GENERO");
                break;
            case "Nosotros":
                nosotros();
                break;
            case "Manual":
                abrirManual();
               break;
            case "Salir":
                System.exit(0);
                break;
                
        }
        v.limpiar();    
    }

    /**
     * Es un evento de teclado
     * @param e Es el evento que lo llama
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }
    
    /**
     * Es el método que obtiene los registros por los campos dado en la base de datos.
     */
    private void buscar() {
        Object[][] libros;
        if(v.getTxt().length()>0){
            try {
                AccesoDatos db=new AccesoDatos();
                String opt=v.optBuscar();
                String txt=v.getTxt();
                libros=db.obtenerBusqueda(opt, txt);
                db.cerrarConexion();
                if(libros.length==0){
                    JOptionPane.showMessageDialog(v, "No se ha encontrado ningun libro", "ERROR", JOptionPane.ERROR_MESSAGE);

                }else{
                    mostrar(libros);
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(ventana, "No has introducido nada", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }

    /**
     * Recoge todos los recogistros de la base de datos.
     */
    private void verTodo() {
        Object[][] libros;
        try {
            AccesoDatos db=new AccesoDatos();
            libros=db.obtenerLibros();
            db.cerrarConexion();
            if(libros.length==0){
                JOptionPane.showMessageDialog(v, "No se ha encontrado ningun libro", "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                mostrar(libros);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Es el método que se encarga de abrir la ventana de añadir un nuevo registro.
     */
    private void annadir() {
        AnnadirLibro al=new AnnadirLibro();
        JDialog dial=new JDialog();
        CtrAnnadir ctrAnnadir=new CtrAnnadir(al,dial);
        ctrAnnadir.ponLaAyuda();
        al.addControlador(ctrAnnadir);
        dial.setTitle("Añadir Libro");
        dial.add(al);
        dial.pack();
        dial.setLocationRelativeTo(null);
        dial.setModal(true);
        dial.setVisible(true);
        dial.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Se encarga de abrir la ventana de Ver con todos sus registros
     * @param libros Es el conjunto de registros obtenidos en la base de datos
     */
    private void mostrar(Object[][] libros) {
        String[] cabecera={"Nombre","Autor","Año","Genero"};
        Buscar encontrado=new Buscar();
        
        
        encontrado.addCabecera(cabecera);
        encontrado.modificarTamannoColumna();
        for(int i=0;i<libros.length;i++){
            encontrado.addFila(libros[i]);
        }
        
        JDialog dial=new JDialog();
        VerTool toolVer=new VerTool();
        dial.add(toolVer, BorderLayout.NORTH);
        CtrVer ctrVer=new CtrVer(encontrado,dial);
        toolVer.addControlador(ctrVer);
        ctrVer.ponLaAyuda();
        dial.setTitle("Libros");
        dial.add(encontrado);
        dial.setSize(600, 400);
        dial.setModal(true);
        dial.setLocationRelativeTo(null);
        dial.setVisible(true);
        dial.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Se encarga de buscar los registros por los campos dados en el menú
     * @param opt Es la opción por la que se buscarán estos registros
     */
    private void buscarPor(String opt) {
        String txt;
        txt=JOptionPane.showInputDialog(v, "Introduzca el nombre:", "Buscar", JOptionPane.INFORMATION_MESSAGE);
        Object[][] libros;
        if(!txt.equals("")){
            try {
                AccesoDatos db=new AccesoDatos();
                libros=db.obtenerBusqueda(opt, txt);
                db.cerrarConexion();
                if(libros.length==0){
                    JOptionPane.showMessageDialog(v, "No se ha encontrado ningun libro", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
                else{
                    mostrar(libros);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showConfirmDialog(ventana, "No has introducido nada","Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
        }
        
        
    }

    /**
     * Abre una ventana con los datos de la compañia
     */
    private void nosotros() {
        Nosotros nosotros=new Nosotros();
        JOptionPane.showConfirmDialog(v, nosotros, "Sobre nosotros", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
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

            hb.enableHelpOnButton(menu.getJMenuItem(), "aplicacion", helpset);
            hb.enableHelpKey(ventana.getContentPane(), "principal",helpset);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void abrirManual() {
        try {
            File path = new File ("help/Manual SISGE.pdf");
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
