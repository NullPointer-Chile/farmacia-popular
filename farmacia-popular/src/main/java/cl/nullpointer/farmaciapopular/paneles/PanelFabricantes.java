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
 * Panel que lista los fabricantes existentes y permite su creación, modificación o eliminación.
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
        botonNuevo.setToolTipText("Crear nuevo fabricante (Ctrl+N)");
        botonModificar.setToolTipText("Mdificar fabricante seleccionado (Ctrl+M)");
        botonEliminar.setToolTipText("Eliminar fabricante seleccionado (Ctrl+E)");

        tablaFabricantes.getColumnModel().getColumn(COLUMNA_ID).setPreferredWidth(50);
        tablaFabricantes.getColumnModel().getColumn(COLUMNA_NOMBRE).setPreferredWidth(250);
        centrarColumna(tablaFabricantes, COLUMNA_ID);

        habilitarOrdenamiento(textFiltro);
    }

    /**
     * Activar los atajos de teclado.
     */
    private void activarAtajosTeclado() {
        AtajoDeTeclado atajo = new AtajoDeTeclado(this);

        // Atajo Salir
        atajo.agregarAtajoSalirDialog();

        // Atajo nuevo
        atajo.agregarAtajoBotonNuevo(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonNuevoActionPerformed(evt);
            }
        });

        // Atajo Modificar
        atajo.agregarAtajoBotonModificar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        // Atajo eliminar
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
        if (getFabricanteSeleccionado() == null) {
            return;
        }

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

    /**
     * Deshabilita el fabricante seleccionado en la tabla.
     */
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
     * Obtener el objeto Fabricante a partir de la fila seleccionada en la tabla principal.
     *
     * @return el Fabricante seleccionado
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
        rowSorterToStringConverter1 = new base.utilidades.RowSorterToStringConverter();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFabricantes = new javax.swing.JTable();
        botonEliminar = new javax.swing.JButton();
        botonNuevo = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        textFiltro = new javax.swing.JTextField();
        lblFiltro = new javax.swing.JLabel();

        rowSorterToStringConverter1.setTable(tablaFabricantes);

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
        tablaFabricantes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaFabricantesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFabricantes);

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quitar chica.png"))); // NOI18N
        botonEliminar.setText("Eliminar"); // NOI18N
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar chica.png"))); // NOI18N
        botonNuevo.setText("Nuevo"); // NOI18N
        botonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoActionPerformed(evt);
            }
        });

        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar chica.png"))); // NOI18N
        botonModificar.setText("Modificar"); // NOI18N
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tablaFabricantes, org.jdesktop.beansbinding.ELProperty.create("${rowSorter}"), textFiltro, org.jdesktop.beansbinding.BeanProperty.create("text"), "queestasbuncansodemidime"); // NOI18N
        binding.setConverter(rowSorterToStringConverter1);
        bindingGroup.addBinding(binding);

        lblFiltro.setText("Filto:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonEliminar, botonModificar, botonNuevo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonNuevo)
                    .addComponent(botonModificar))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonEliminar, botonModificar, botonNuevo});

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

    private void tablaFabricantesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaFabricantesKeyPressed
        if (sePresionoTeclaEnter(evt)) {
            botonModificarActionPerformed(null);
        }
    }//GEN-LAST:event_tablaFabricantesKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonNuevo;
    private java.util.List<Fabricante> fabricantesList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private base.utilidades.RowSorterToStringConverter rowSorterToStringConverter1;
    private javax.swing.JTable tablaFabricantes;
    private javax.swing.JTextField textFiltro;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
