package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.PanelBase;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import java.beans.Beans;
import java.util.ArrayList;
import java.util.Collections;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class PanelAutenticacion extends PanelBase {

    public PanelAutenticacion() {
        initComponents();
        
        listaUsuarios.clear();
        listaUsuarios.addAll(Usuario.getUsuariosHabilitadosBD());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaUsuarios = Beans.isDesignTime() ? Collections.emptyList() : ObservableCollections.observableList(new ArrayList<Usuario>());
        comboUsuarios = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, listaUsuarios, comboUsuarios, "comboUsuarios"); // NOI18N
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboUsuarios, 0, 185, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Usuario> comboUsuarios;
    private javax.swing.JPasswordField jPasswordField1;
    private java.util.List<Usuario> listaUsuarios;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
