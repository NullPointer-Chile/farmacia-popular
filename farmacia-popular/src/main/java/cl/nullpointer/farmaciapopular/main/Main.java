package cl.nullpointer.farmaciapopular.main;

import base.utilidades.Aplicacion;
import base.utilidades.Utils;
import cl.nullpointer.farmaciapopular.paneles.PanelPrincipal;
import com.pagosoft.plaf.PlafOptions;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
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

        UIDefaults defaults = UIManager.getDefaults();
        Enumeration newKeys = defaults.keys();
        
        defaults.put("Panel.background", new ColorUIResource(Color.white));

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
}
