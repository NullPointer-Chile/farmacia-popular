package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.AtajoDeTeclado;
import base.paneles.PanelBase;
import cl.nullpointer.farmaciapopular.dominio.Proveedor;
import cl.nullpointer.farmaciapopular.main.Main;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class PanelProveedores extends PanelBase {

    public PanelProveedores() {
        initComponents();
        activarAtajosTeclado();

        int indiceColumnaId = 0;
        TableColumn columnaId = masterTable.getColumnModel().getColumn(indiceColumnaId);
        columnaId.setPreferredWidth(50);
        centrarColumna(masterTable, indiceColumnaId);
        columnaId.setResizable(true);

        int indiceColumnaNombre = 1;
        TableColumn columnaNombre = masterTable.getColumnModel().getColumn(indiceColumnaNombre);
        columnaNombre.setPreferredWidth(300);
        columnaNombre.setResizable(true);

        refrescar();
    }

    private void refrescar() {
        list.clear();
        list.addAll(Proveedor.getProveedoresHabilitadosBD());
    }

    /**
     * Activar los atajos de teclado.
     */
    private void activarAtajosTeclado() {
        AtajoDeTeclado atajo = new AtajoDeTeclado(this);
        atajo.agregarAtajoSalirDialog();
        atajo.agregarAtajoBotonNuevo(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonNuevoActionPerformed(evt);
            }
        });
        atajo.agregarAtajoBotonModificar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        atajo.agregarAtajoBotonEliminar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
    }

    private Proveedor getProveedorSeleccionado() {
        int filaSeleccionada = masterTable.getSelectedRow();
        if (filaSeleccionada != -1) {
            return (Proveedor) list.get(masterTable.convertRowIndexToModel(filaSeleccionada));
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        list = java.beans.Beans.isDesignTime() ? Collections.emptyList() : ObservableCollections.observableList(new ArrayList());
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonNuevo = new javax.swing.JButton();

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable, "bindListaUsuarios"); // NOI18N
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Short.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(base.tipoDato.Texto.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(masterTable);

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quitar chica.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar chica.png"))); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar chica.png"))); // NOI18N
        botonNuevo.setText("Nuevo");
        botonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonModificar)
                    .addComponent(botonNuevo))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed
        JFrame mainFrame = Main.getVentanaPrincipal();
        JDialog dialogProveedor;
        dialogProveedor = new JDialog(mainFrame);
        dialogProveedor.setName("dialogProveedor");
        dialogProveedor.setTitle("Nuevo Proveedor");
        dialogProveedor.setModal(true);
        dialogProveedor.setResizable(false);
        PanelProveedor panelProveedor = new PanelProveedor(new Proveedor(), true);
        dialogProveedor.setContentPane(panelProveedor);
        dialogProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogProveedor, true);

        refrescar();
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        Proveedor proveedor = getProveedorSeleccionado();
        if (proveedor != null) {
            JFrame mainFrame = Main.getVentanaPrincipal();
            JDialog dialogProveedor;
            dialogProveedor = new JDialog(mainFrame);
            dialogProveedor.setName("dialogProveedor");
            dialogProveedor.setTitle("Modificar Proveedor");
            dialogProveedor.setModal(true);
            dialogProveedor.setResizable(false);
            PanelProveedor panelProveedor = new PanelProveedor(proveedor, false);
            dialogProveedor.setContentPane(panelProveedor);
            dialogProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Main.getAplicacion().show(dialogProveedor, true);

            refrescar();
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        Proveedor proveedor = getProveedorSeleccionado();
        if (proveedor != null && JOptionPane.showConfirmDialog(
                this,
                "¿Realmente desea borrar al proveedor " + proveedor.getNombre().toString() + "?",
                "ATENCIÓN",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            proveedor.setHabilitado(false);
            proveedor.actualizarEnBD();

            refrescar();
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        if (hizoDobleClic(evt)) {
            botonModificarActionPerformed(null);
        }
    }//GEN-LAST:event_masterTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Proveedor> list;
    private javax.swing.JTable masterTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
