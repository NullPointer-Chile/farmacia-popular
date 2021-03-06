package cl.nullpointer.farmaciapopular.paneles;

import cl.nullpointer.farmaciapopular.main.Main;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Ventana principal de la aplicación con los menús para acceder a las distintas
 * funcionalidades.
 *
 * @author Omar Paché
 */
public class PanelPrincipal extends JFrame {

    /**
     * Crea el PanelPrincipal con la barra de menus.
     */
    public PanelPrincipal() {
        initComponents();

        JDialog dialogAutenticacion;
        dialogAutenticacion = new JDialog(this);
        dialogAutenticacion.setName("dialogAutenticacion");
        dialogAutenticacion.setTitle("Autenticación Usuario");
        dialogAutenticacion.setModal(true);
        dialogAutenticacion.setResizable(false);
        PanelAutenticacion panelAutenticacion = new PanelAutenticacion();
        dialogAutenticacion.setContentPane(panelAutenticacion);
        dialogAutenticacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogAutenticacion, true);

        if (!panelAutenticacion.estaUsuarioAutenticado()) {
            System.exit(0);
        }
    }

    /**
     * Se cierra la aplicación.
     */
    private void salir() {
        System.exit(0);
    }

    /**
     * Se abre el panel informativo de la aplicación.
     */
    private void abrirPanelAcercaDe() {
        JOptionPane.showMessageDialog(null, "Software desarrollado por NullPointer. \nSi tiene dudas escríbanos a "
                + "contacto@nullpointer.cl", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Se abre el panel de configuración de la aplicación.
     */
    private void abrirPanelConfiguracion() {
        //TODO
    }

    /**
     * Se abre el panel mantenedor de usuarios.
     */
    private void abrirPanelUsuarios() {
        JFrame mainFrame = Main.getVentanaPrincipal();
        JDialog dialogUsuarios;
        dialogUsuarios = new JDialog(mainFrame);
        dialogUsuarios.setName("dialogUsuarios");
        dialogUsuarios.setTitle("Listado Usuarios Activos");
        dialogUsuarios.setModal(true);
        dialogUsuarios.setResizable(false);
        PanelUsuarios panelUsuarios = new PanelUsuarios();
        dialogUsuarios.setContentPane(panelUsuarios);
        dialogUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogUsuarios, true);
    }

    /**
     * Se abre el panel mantenedor de fabricantes.
     */
    private void abrirPanelfabricantes() {
        JFrame mainFrame = Main.getVentanaPrincipal();
        JDialog dialogFabricantes;
        dialogFabricantes = new JDialog(mainFrame);
        dialogFabricantes.setName("dialogFabricantes");
        dialogFabricantes.setTitle("Listado Fabricantes");
        dialogFabricantes.setModal(true);
        dialogFabricantes.setResizable(false);
        PanelFabricantes panelFabricantes = new PanelFabricantes();
        dialogFabricantes.setContentPane(panelFabricantes);
        dialogFabricantes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogFabricantes, true);
    }

    /**
     * Se abre el panel mantenedor de proveedores.
     */
    private void abrirPanelProveedores() {
        JFrame mainFrame = Main.getVentanaPrincipal();
        JDialog dialogProveedores;
        dialogProveedores = new JDialog(mainFrame);
        dialogProveedores.setName("dialogProveedor");
        dialogProveedores.setTitle("Listado Proveedores Activos");
        dialogProveedores.setModal(true);
        dialogProveedores.setResizable(false);
        PanelProveedores panelProveedores = new PanelProveedores();
        dialogProveedores.setContentPane(panelProveedores);
        dialogProveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogProveedores, true);
    }

    /**
     * Se abre el panel mantenedor de productos.
     */
    private void abrirPanelProductos() {

    }

    /**
     * Se abre el panel mantenedor de clientes.
     */
    private void abrirPanelClientes() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraMenu = new javax.swing.JMenuBar();
        menuSistema = new javax.swing.JMenu();
        menuItemConfiguracion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemSalir = new javax.swing.JMenuItem();
        menuMantenedores = new javax.swing.JMenu();
        menuUsuarios = new javax.swing.JMenuItem();
        menuFabricantes = new javax.swing.JMenuItem();
        menuProveedores = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenuItem();
        menuClientes = new javax.swing.JMenuItem();
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

        menuMantenedores.setText("Mantenedores");

        menuUsuarios.setText("Usuarios");
        menuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuariosActionPerformed(evt);
            }
        });
        menuMantenedores.add(menuUsuarios);

        menuFabricantes.setText("Fabricantes");
        menuFabricantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFabricantesActionPerformed(evt);
            }
        });
        menuMantenedores.add(menuFabricantes);

        menuProveedores.setText("Proveedores");
        menuProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProveedoresActionPerformed(evt);
            }
        });
        menuMantenedores.add(menuProveedores);

        menuProductos.setText("Productos");
        menuProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductosActionPerformed(evt);
            }
        });
        menuMantenedores.add(menuProductos);

        menuClientes.setText("Clientes");
        menuClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClientesActionPerformed(evt);
            }
        });
        menuMantenedores.add(menuClientes);

        barraMenu.add(menuMantenedores);

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
            .addGap(0, 448, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfiguracionActionPerformed
        abrirPanelConfiguracion();
    }//GEN-LAST:event_menuItemConfiguracionActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        salir();
    }//GEN-LAST:event_menuItemSalirActionPerformed

    private void menuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercaDeActionPerformed
        abrirPanelAcercaDe();
    }//GEN-LAST:event_menuItemAcercaDeActionPerformed

    private void menuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuariosActionPerformed
        abrirPanelUsuarios();
    }//GEN-LAST:event_menuUsuariosActionPerformed

    private void menuFabricantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFabricantesActionPerformed
        abrirPanelfabricantes();
    }//GEN-LAST:event_menuFabricantesActionPerformed

    private void menuProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProveedoresActionPerformed
        abrirPanelProveedores();
    }//GEN-LAST:event_menuProveedoresActionPerformed

    private void menuProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductosActionPerformed
        abrirPanelProductos();
    }//GEN-LAST:event_menuProductosActionPerformed

    private void menuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClientesActionPerformed
        abrirPanelClientes();
    }//GEN-LAST:event_menuClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuClientes;
    private javax.swing.JMenuItem menuFabricantes;
    private javax.swing.JMenuItem menuItemAcercaDe;
    private javax.swing.JMenuItem menuItemConfiguracion;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuMantenedores;
    private javax.swing.JMenuItem menuProductos;
    private javax.swing.JMenuItem menuProveedores;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenuItem menuUsuarios;
    // End of variables declaration//GEN-END:variables
}
