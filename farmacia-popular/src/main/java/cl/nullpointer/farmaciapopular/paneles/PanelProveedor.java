package cl.nullpointer.farmaciapopular.paneles;

import base.paneles.AtajoDeTeclado;
import base.paneles.PanelBase;
import base.tipoDato.Texto;
import cl.nullpointer.farmaciapopular.dominio.Proveedor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class PanelProveedor extends PanelBase {

    private final Proveedor proveedor;
    private final boolean nuevo;

    public PanelProveedor(Proveedor proveedor, boolean nuevo) {
        this.proveedor = proveedor;
        this.nuevo = nuevo;

        initComponents();
        activarAtajosTeclado();

        setPlaceholder(textNombre, "Nombre del proveedor");

        if (!nuevo) {
            textNombre.setText(proveedor.getNombre().toString());
        }
    }

    /**
     * Activar los atajos de teclado.
     */
    private void activarAtajosTeclado() {
        AtajoDeTeclado atajo = new AtajoDeTeclado(this);

        atajo.agregarAtajoSalirDialog();

        atajo.agregarAtajoBotonGuardar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        atajo.agregarAtajoBotonEnterPanel(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                KeyboardFocusManager manager = KeyboardFocusManager
                        .getCurrentKeyboardFocusManager();
                if ("botonGuardar".equals(manager.getFocusOwner().getName())) {
                    botonGuardarActionPerformed(evt);
                } else {
                    manager.focusNextComponent();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();

        jLabel1.setText("Nombre:");

        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tick chica.png"))); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.setName("botonGuardar"); // NOI18N
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(textNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        if (proveedor.setNombre(new Texto(textNombre.getText())).isError()) {
            pintarRojoText(textNombre);
            dejarFocoEn(textNombre);
        } else {
            textNombre.setText(proveedor.getNombre().toString());
            pintarTextSinError(textNombre);

            if (nuevo) {
                proveedor.insertarEnBD();
            } else {
                proveedor.actualizarEnBD();
            }

            cerrarVentana();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        letraAmayuscula(evt);
        limitarLargo(evt, textNombre, Proveedor.LARGO_MAXIMO_NOMBRE);
    }//GEN-LAST:event_textNombreKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
