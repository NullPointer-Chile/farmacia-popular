package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.PanelBase;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.TableColumn;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class PanelUsuarios extends PanelBase {

    public PanelUsuarios() {
        initComponents();

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        list = java.beans.Beans.isDesignTime() ? Collections.emptyList() : ObservableCollections.observableList(new ArrayList());
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();

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
        jScrollPane1.setViewportView(masterTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Usuario> list;
    private javax.swing.JTable masterTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void refrescar() {
        list.clear();
        list.addAll(Usuario.getUsuariosHabilitadosBD());
    }

}
