package cl.nullpointer.farmaciapopular.paneles;

import javax.swing.JOptionPane;

/**
 *
 * @author Omar Paché
 */
public class PanelPrincipal extends javax.swing.JFrame {

    /**
     * Crea el PanelPrincipal
     */
    public PanelPrincipal() {
        initComponents();
    }

    private void configuracion() {
        
    }

    private void salir(){
        System.exit(0);
    }
    
    private void acercaDe() {
        JOptionPane.showMessageDialog(null, "Software desarrollado por NullPointer. \nSi tiene dudas escríbanos a "
                + "contacto@nullpointer.cl", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraMenu = new javax.swing.JMenuBar();
        menuSistema = new javax.swing.JMenu();
        menuItemConfiguracion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemSalir = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        menuItemAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuSistema.setText("Sistema");

        menuItemConfiguracion.setText("Configuracion");
        menuItemConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfiguracionActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemConfiguracion);
        menuSistema.add(jSeparator1);

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemSalir);

        barraMenu.add(menuSistema);

        menuAyuda.setText("Ayuda");

        menuItemAcercaDe.setText("Acerca de");
        menuItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAcercaDeActionPerformed(evt);
            }
        });
        menuAyuda.add(menuItemAcercaDe);

        barraMenu.add(menuAyuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 791, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfiguracionActionPerformed
       configuracion();
    }//GEN-LAST:event_menuItemConfiguracionActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        salir();
    }//GEN-LAST:event_menuItemSalirActionPerformed

    private void menuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercaDeActionPerformed
        acercaDe();
    }//GEN-LAST:event_menuItemAcercaDeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuItemAcercaDe;
    private javax.swing.JMenuItem menuItemConfiguracion;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuSistema;
    // End of variables declaration//GEN-END:variables
}
