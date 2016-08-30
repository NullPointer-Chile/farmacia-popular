package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.AtajoDeTeclado;
import base.paneles.PanelBase;
import base.validacion.ResultadoMetodo;
import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import cl.nullpointer.farmaciapopular.main.Main;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NOMBRE = 1;

    /**
     * Crear un nuevo Panel Fabricantes.
     */
    public PanelFabricantes() {
        initComponents();
        setLookAndFeel();
        activarAtajosTeclado();
        refrescarTabla();
    }

    /**
     * Establecer propiedades de componentes graficos.
     */
    private void setLookAndFeel() {
        botonNuevo.setToolTipText("Crear nuevo fabricante");
        botonModificar.setToolTipText("Mdificar fabricante seleccionado");
        botonEliminar.setToolTipText("Eliminar fabricante seleccionado");
        tablaFabricantes.getColumnModel().getColumn(COLUMNA_ID).setPreferredWidth(50);
        tablaFabricantes.getColumnModel().getColumn(COLUMNA_NOMBRE).setPreferredWidth(250);
        centrarColumna(tablaFabricantes, COLUMNA_ID);
    }

    /**
     * Activar los atajos de teclado.
     */
    private void activarAtajosTeclado() {
        AtajoDeTeclado atajo = new AtajoDeTeclado(this);
        atajo.agregarAtajoSalirDialog();

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

    /**
     * Se refresca la tabla principal obteniendo el listado de todos los fabricantes.
     */
    private void refrescarTabla() {
        fabricantesList.clear();
        fabricantesList.addAll(Fabricante.getAll());
    }

    /**
     * Abrir panel para crear nuevo usuario.
     */
    private void abrirPanelNuevo() {
        JFrame mainFrame = Main.getVentanaPrincipal();
        JDialog dialogFabricante;
        dialogFabricante = new JDialog(mainFrame);
        dialogFabricante.setName("dialogFabricante");
        dialogFabricante.setTitle("Nuevo Fabricante");
        dialogFabricante.setModal(true);
        dialogFabricante.setResizable(false);
        PanelFabricante panelFabricante = new PanelFabricante();
        dialogFabricante.setContentPane(panelFabricante);
        dialogFabricante.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogFabricante, true);
        refrescarTabla();
    }

    /**
     * Abrir panel para modificar fabricante seleccionado.
     */
    private void abrirPanelModificar() {
        JFrame mainFrame = Main.getVentanaPrincipal();
        JDialog dialogFabricante;
        dialogFabricante = new JDialog(mainFrame);
        dialogFabricante.setName("dialogFabricante");
        dialogFabricante.setTitle("Modificar Fabricante");
        dialogFabricante.setModal(true);
        dialogFabricante.setResizable(false);
        PanelFabricante panelFabricante = new PanelFabricante(getFabricanteSeleccionado());
        dialogFabricante.setContentPane(panelFabricante);
        dialogFabricante.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Main.getAplicacion().show(dialogFabricante, true);
        refrescarTabla();
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
        botonModificar = new javax.swing.JButton();

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
        tablaFabricantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFabricantesMouseClicked(evt);
            }
        });
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

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonNuevo)
                    .addComponent(botonModificar))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed
        abrirPanelNuevo();
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        deshabilitar();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        abrirPanelModificar();
    }//GEN-LAST:event_botonModificarActionPerformed

    private void tablaFabricantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFabricantesMouseClicked
        if (hizoDobleClic(evt)) {
            abrirPanelModificar();
        }
    }//GEN-LAST:event_tablaFabricantesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonNuevo;
    private java.util.List<Fabricante> fabricantesList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFabricantes;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
