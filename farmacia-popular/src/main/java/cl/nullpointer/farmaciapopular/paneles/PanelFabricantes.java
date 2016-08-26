package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.PanelBase;
import base.validacion.ResultadoMetodo;
import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Omar Paché
 */
public class PanelFabricantes extends PanelBase {

    private static final Logger LOG = Logger.getLogger(PanelFabricantes.class);

    // Definiendo el indice de las columnas de la tabla
    private final int columnaID = 0;
    private final int columnaNombre = 1;

    /**
     * Crear un nuevo Panel Fabricantes.
     */
    public PanelFabricantes() {
        initComponents();
        setLookAndFeel();
        refrescarTabla();
    }

    /**
     * Establecer propiedades de componentes graficos.
     */
    private void setLookAndFeel() {
        botonNuevo.setToolTipText("Crear nuevo fabricante");
        botonEliminar.setToolTipText("Eliminar fabricante seleccionado");
        tablaFabricantes.getColumnModel().getColumn(columnaID).setPreferredWidth(50);
        tablaFabricantes.getColumnModel().getColumn(columnaNombre).setPreferredWidth(250);
        centrarColumna(tablaFabricantes, columnaID);
    }

    /**
     * Se refresca la tabla principal obteniendo el listado de todos los fabricantes.
     */
    private void refrescarTabla() {
        fabricantesList.clear();
        fabricantesList.addAll(Fabricante.getAll());
    }

    private void nuevo() {

    }

    private void deshabilitar() {
        LOG.info("Rechazando tratamiento");

        if (getFabricanteSeleccionado() == null) {
            return;
        }

        if (JOptionPane.showConfirmDialog(null, "¿Está seguro(a) que desea eliminar el fabricante seleccionado?",
                "Confirmación", JOptionPane.YES_NO_OPTION) != 0) {
            return;
        }

        ResultadoMetodo resultado;
        resultado = Fabricante.deshabilitar(getFabricanteSeleccionado());

        if (resultado.isError()) {
            JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        refrescarTabla();
    }

    /**
     * Obtener el objeto Convenio a partir de la fila seleccionada en la tabla principal.
     *
     * @return el convenio contigo seleccionado
     */
    private Fabricante getFabricanteSeleccionado() {
        Fabricante fabricante;
        int seleccion = tablaFabricantes.getSelectedRow();
        if (seleccion == -1 || fabricantesList.isEmpty() || seleccion > fabricantesList.size()) {
            return null;
        }
        fabricante = fabricantesList.get(tablaFabricantes.convertRowIndexToModel(seleccion));
        return fabricante;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        fabricantesList = java.beans.Beans.isDesignTime() ? Collections.emptyList() : ObservableCollections.observableList(new ArrayList<>());
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFabricantes = new javax.swing.JTable();
        botonEliminar = new javax.swing.JButton();
        botonNuevo = new javax.swing.JButton();

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, fabricantesList, tablaFabricantes);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("id");
        columnBinding.setColumnClass(Short.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("nombre");
        columnBinding.setColumnClass(base.tipoDato.Texto.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaFabricantes);

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonNuevo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        deshabilitar();
    }//GEN-LAST:event_botonEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonNuevo;
    private java.util.List<Fabricante> fabricantesList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFabricantes;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
