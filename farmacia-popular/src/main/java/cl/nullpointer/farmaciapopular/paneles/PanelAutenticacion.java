package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.AtajoDeTeclado;
import base.paneles.PanelBase;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import java.awt.event.ActionEvent;
import java.beans.Beans;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class PanelAutenticacion extends PanelBase {

    private boolean usuarioAutenticado = false;

    public PanelAutenticacion() {
        initComponents();

        activarAtajosTeclado();

        listaUsuarios.clear();
        listaUsuarios.addAll(Usuario.getUsuariosHabilitadosBD());
    }

    /**
     * Activar los atajos de teclado.
     */
    private void activarAtajosTeclado() {
        AtajoDeTeclado atajo = new AtajoDeTeclado(this);
        atajo.agregarAtajoSalirDialog();

        atajo.agregarAtajoBotonEnterPanel(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonAutenticarActionPerformed(evt);
            }
        });
    }

    public boolean estaUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaUsuarios = Beans.isDesignTime() ? Collections.emptyList() : ObservableCollections.observableList(new ArrayList<Usuario>());
        comboUsuarios = new javax.swing.JComboBox<>();
        textContraseña = new javax.swing.JPasswordField();
        botonAutenticar = new javax.swing.JButton();

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, listaUsuarios, comboUsuarios, "comboUsuarios"); // NOI18N
        bindingGroup.addBinding(jComboBoxBinding);

        comboUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsuariosActionPerformed(evt);
            }
        });
        comboUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUsuariosKeyPressed(evt);
            }
        });

        botonAutenticar.setText("Autenticar"); // NOI18N
        botonAutenticar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAutenticarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboUsuarios, 0, 185, Short.MAX_VALUE)
                    .addComponent(textContraseña)
                    .addComponent(botonAutenticar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAutenticar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAutenticarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAutenticarActionPerformed
        Usuario usuario = (Usuario) comboUsuarios.getSelectedItem();
        if (usuario.validarContraseña(textContraseña.getPassword()).isError()) {
            JOptionPane.showMessageDialog(
                    this,
                    usuario.validarContraseña(textContraseña.getPassword()).getMensaje(),
                    "ATENCION", JOptionPane.OK_OPTION);
        } else {
            usuarioAutenticado = true;
            cerrarVentana();
        }
    }//GEN-LAST:event_botonAutenticarActionPerformed

    private void comboUsuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUsuariosKeyPressed
        if (sePresionoTeclaEnter(evt)) {
            dejarFocoEn(textContraseña);
        }
    }//GEN-LAST:event_comboUsuariosKeyPressed

    private void comboUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsuariosActionPerformed
        if (evt.getModifiers() == 16) {
            dejarFocoEn(textContraseña);
        }
    }//GEN-LAST:event_comboUsuariosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAutenticar;
    private javax.swing.JComboBox<Usuario> comboUsuarios;
    private java.util.List<Usuario> listaUsuarios;
    private javax.swing.JPasswordField textContraseña;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
