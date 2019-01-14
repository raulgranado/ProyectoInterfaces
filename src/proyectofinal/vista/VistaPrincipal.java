/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.vista;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Esta clase visualiza los componentes de la pantalla principal
 * @author raul
 */
public class VistaPrincipal extends javax.swing.JPanel {

    /**
     * El constructor de la clase
     */
    public VistaPrincipal() {
        initComponents();
        buscador1.addOpcion("Nombre");
        buscador1.addOpcion("Autor");
        buscador1.addOpcion("Genero");
    }

    /**
     * Este método obtiene la opcion seleccionada del buscador
     * @return Devuelve la opción seleccionada
     */
    public String optBuscar(){
        return buscador1.getOpcion();
    }
    
    /**
     * Este método devuelve el texto introducido en el buscador
     * @return Devuelve el texto a buscar
     */
    public String getTxt(){
        return buscador1.getTexto();
    }
    
    /**
     * Pone el texto del buscador vacío
     */
    public void limpiar(){
        buscador1.setTexto("");
    }
    
    /**
     * Añade un controlador que escucha eventos de teclado
     * @param kl Es el controlador
     */
    public void addControladorTeclado(KeyListener kl){
        buscador1.addControlador(kl);
    }
    
    /**
     * Añade un controlador que escucha eventos de los botones
     * @param l Es el controlador
     */
    public void addControlador(ActionListener l){
        jButton1.addActionListener(l);
        jButton2.addActionListener(l);
        jButton3.addActionListener(l);
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscador1 = new beanbuscador.Buscador();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(500, 300));
        setPreferredSize(new java.awt.Dimension(600, 400));

        jButton1.setText("Buscar");

        jButton2.setText("Añadir");

        jButton3.setText("Ver todo");
        jButton3.setActionCommand("VerTodo");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/img/principal.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(buscador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jLabel2))
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buscador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private beanbuscador.Buscador buscador1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}