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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyectofinal.vista.AnnadirLibro;

/**
 *Esta clase se encarga de controlar la ventana de añadir de nuestra aplicación
 * @author raul
 */
public class CtrAnnadir implements ActionListener {
    
    private AnnadirLibro al;
    private JDialog dial;
    /**
     * Constructor de la clase
     * @param al Es el JPanel que contiene los componentes
     * @param dial Es el JDialog que contiene el JPanel
     */
    public CtrAnnadir(AnnadirLibro al, JDialog dial){
        this.al=al;
        this.dial=dial;
    }
    
    /**
     * Aquí se gestiona los eventos producidos por los botones
     * @param e Es el evento provocado por los botones de la ventana
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "buscar":
                buscar();
                break;
            case "aceptar":
                aceptar();
                break;
            case "cancelar":
                dial.dispose();
                break;
        }
    }

    /**
     * Método que gestiona el buscador de ficheros
     */
    private void buscar() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagen (.jpg, .gif, .png)", "jpg", "gif", "png");
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(filtro);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=fc.showOpenDialog(al);
        if (result == JFileChooser.APPROVE_OPTION){
            File name= fc.getSelectedFile();
            String url=fc.getSelectedFile().getPath();
            al.setImagen(url);
            al.setUrlImagen(url);
        }
    }

    /**
     * Método que gestiona el botón de añadir. Es necesario introducir el nombre de forma obligatoria y en caso de introducir
     * el año, este debe ser numerico.
     */
    private void aceptar() {
        try {
            AccesoDatos bd=new AccesoDatos();
            String libro[]=new String[6];
            boolean annadido =false;
            boolean inserta=true;
            if(al.getNombre().equals("") || !comprobarNumerico(al.getAnno())){
                inserta=false;
            }
            if(inserta){
                libro[0]=al.getNombre();
                if(al.getAutor().equals("")){
                    libro[1]=null;
                }else{
                    libro[1]=al.getAutor();
                }
                if(al.getAnno().equals("")){
                    libro[2]=null;
                }else{
                    libro[2]=al.getAnno();
                }
                if(al.getGenero().equals("")){
                    libro[3]=null;
                }else{
                    libro[3]=al.getGenero();
                }
                if(al.getArgumento().equals("")){
                    libro[4]=null;
                }else{
                    libro[4]=al.getArgumento();
                }
                if(al.getImagen().equals("")){
                    libro[5]=null;
                }else{
                    int i=al.getImagen().indexOf("/proyectofinal");
                    libro[5]=al.getImagen().substring(i);
                }
                annadido=bd.insertaLibro(libro);
                bd.cerrarConexion();
                if(annadido){
                    JOptionPane.showMessageDialog(al, "Se ha añadido correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                    dial.dispose();
                }else{
                    JOptionPane.showMessageDialog(al, "No se ha podido añadir", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
             }else{
                JOptionPane.showMessageDialog(al, "<html>No se ha podido añadir, comprueba que el nombre no este vacío, "
                        + "<br/>o que has introducido el año correctamente", "Atencion", JOptionPane.WARNING_MESSAGE);
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CtrAnnadir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CtrAnnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Metodo interno que comprueba que lo introducido en el campo Año sea numérico.
     * @param anno Es el valor a comprobar
     * @return Devuelve true si es numérico y false en caso contrario
     */
    private boolean comprobarNumerico(String anno) {
        boolean correcto=true;
        int i=0;
        if(anno.length()>0){
            while(i<anno.length() && correcto){
                if(!Character.isDigit(anno.charAt(i))){
                    correcto=false;
                }
                i++;
            }
        }
        return correcto;
    }
    
    /**
     * Método que añade la ayuda a la ventana
     */
    public void ponLaAyuda() {
        try {
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();
            
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            
            hb.enableHelpKey(dial.getContentPane(), "annadir",helpset);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}


