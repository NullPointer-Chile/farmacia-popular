package cl.nullpointer.farmaciapopular.main;

import base.utilidades.Aplicacion;
import base.utilidades.Utils;
import cl.nullpointer.farmaciapopular.paneles.PanelPrincipal;
import com.pagosoft.plaf.PlafOptions;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

/**
 * Clase principal de la aplicación.
 *
 * @author Omar Paché
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    private static PanelPrincipal ventanaPrincipal;

    public static PanelPrincipal getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public static Aplicacion getAplicacion() {
        return new Aplicacion();
    }

    public static void main(String args[]) {

        // Establecer apariencia de la aplicación
        PlafOptions.setAsLookAndFeel();
        PlafOptions.updateAllUIs();

        setUIFont(new javax.swing.plaf.FontUIResource("UnDotum", Font.PLAIN, 13));

        // Comprobar que la aplicación no se encuentra corriendo
        if (!Utils.comprobarInstancia()) {
            JOptionPane.showMessageDialog(null, "La aplicación ya está ejecutándose en otra ventana",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Utils.cerrarAplicacion();
        }

        LOG.info("Iniciando Aplicacion");
        // Mostrar S.O.
        LOG.info("S.O. cliente: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " "
                + System.getProperty("os.arch"));
        // Mostrar usuario S.0.
        LOG.info("Nombre usuario S.O: " + System.getProperty("user.name"));
        // Mostrar version java
        LOG.info("Version JRE: " + System.getProperty("java.version") + " de " + System.getProperty("java.vendor"));
        // Obtiene los valores del archivo de propiedades
        Utils.leerArchivoPropiedades();

        // Levantar panel principal
        ventanaPrincipal = new PanelPrincipal();
        ventanaPrincipal.setTitle("Farmacia Popular");
        ventanaPrincipal.setIconImage(Utils.getIcono());
        ventanaPrincipal.setMinimumSize(new Dimension(800, 600));
        ventanaPrincipal.setResizable(true);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
