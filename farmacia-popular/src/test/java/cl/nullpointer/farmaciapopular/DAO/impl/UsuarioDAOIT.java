package cl.nullpointer.farmaciapopular.DAO.impl;

import base.tipoDato.Texto;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class UsuarioDAOIT {

    private static final Logger LOG = Logger.getLogger(UsuarioDAOIT.class);

    public UsuarioDAOIT() {
    }

    /**
     * Comprobar la obtención de un listado de usuarios.
     */
    @Test
    public void test_obtenerListadoUsuarios_OK() {
        LOG.info("Comprobando la obtención de un listado de usuarios.");

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        List<Usuario> usuarioList = (List<Usuario>) consulta
                .ejecutar((DAOManager DAOManager) -> {
                    return DAOManager.getUsuarioDAO().getUsuariosHabilitados();
                });

        assertNotNull(usuarioList);
    }

    /**
     * Comprobar obtención registros actuales de usuario.
     */
    @Test
    public void test_obtenerRegistrosActuales_OK() {
        LOG.info("Comprobando la obtención de registros actuales de usuario.");
        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        short registrosActuales = (short) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getUsuarioDAO().obtenerRegistrosActuales();
        });

        assertTrue(registrosActuales >= 0);
    }

    /**
     * Comprobar inserción de usuario.
     */
    @Test
    public void test_insertar_OK() {
        LOG.info("Comprobando inserción de usuario.");
        Texto nombre = new Texto("Mireya Robotech");
        String contraseña = "6969";
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.insertar();
    }

}
