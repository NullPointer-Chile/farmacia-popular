package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.AtajoDeTeclado;
import base.paneles.PanelBase;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import cl.nullpointer.farmaciapopular.main.Main;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.TableColumn;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class PanelUsuarios extends PanelBase {

    public PanelUsuarios() {
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
        list.addAll(Usuario.getUsuariosHabilitadosBD());
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
    }

    private Usuario getUsuarioSeleccionado() {
        int filaSeleccionada = masterTable.getSelectedRow();
        if (filaSeleccionada != -1) {
            return (Usuario) list.get(masterTable.convertRowIndexToModel(filaSeleccionada));
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

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

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
                    .addComponent(jScrollPane1)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        Usuario usuario = getUsuarioSeleccionado();
        if (usuario != null) {
            JFrame mainFrame = Main.getVentanaPrincipal();
            JDialog dialogUsuario;
            dialogUsuario = new JDialog(mainFrame);
            dialogUsuario.setName("dialogUsuario");
            dialogUsuario.setTitle("Modificar Usuario");
            dialogUsuario.setModal(true);
            dialogUsuario.setResizable(false);
            PanelUsuario panelUsuario = new PanelUsuario(usuario);
            dialogUsuario.setContentPane(panelUsuario);
            dialogUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Main.getAplicacion().show(dialogUsuario, true);
            
            refrescar();
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed

    }//GEN-LAST:event_botonEliminarActionPerformed

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        if(hizoDobleClic(evt)){
            botonModificarActionPerformed(null);
        }
    }//GEN-LAST:event_masterTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Usuario> list;
    private javax.swing.JTable masterTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
