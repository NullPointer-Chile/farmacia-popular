package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.AtajoDeTeclado;
import base.paneles.PanelBase;
import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Omar Paché
 */
public class PanelFabricante extends PanelBase {

    private Fabricante fabricante;

    public PanelFabricante() {
        initComponents();
        setLookAndFeel();
        activarAtajosTeclado();
    }

    public PanelFabricante(Fabricante fabricante) {
        initComponents();
        setLookAndFeel();
        activarAtajosTeclado();

        this.fabricante = fabricante;
        textNombre.setText(fabricante.getNombre().toString());
    }

    /**
     * Establecer propiedades de componentes graficos.
     */
    private void setLookAndFeel() {
//        setPlaceholder(textNombre, "Nombre del fabricante.");
    }

    /**
     * Activar los atajos de teclado.
     */
    private void activarAtajosTeclado() {
        AtajoDeTeclado atajo = new AtajoDeTeclado(this);

        // Salir con Escape
        atajo.agregarAtajoSalirDialog();

        // Guardar con Ctrl + G
        atajo.agregarAtajoBotonGuardar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                botonGuardarActionPerformed(evento);
            }
        });
    }

    private void guardar() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();

        lblNombre.setText("Nombre:");

        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });

        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonGuardar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        letraAmayuscula(evt);
    }//GEN-LAST:event_textNombreKeyTyped

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_botonGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
