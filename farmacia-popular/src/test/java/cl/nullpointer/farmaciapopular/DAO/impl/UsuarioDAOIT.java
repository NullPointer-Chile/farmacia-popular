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
            return DAOManager.getUsuarioDAO().contarRegistros();
        });

        assertTrue(registrosActuales >= 0);
    }

    /**
     * Comprobar si usuario que debería existir existe.
     */
    @Test
    public void test_existe_OK() {
        LOG.info("Comprobando si usuario que debería existir existe.");

        Usuario usuario = new Usuario();
        usuario.setId(new Short("1"));

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        boolean existe = (boolean) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getUsuarioDAO().existe(usuario);
        });

        assertTrue(existe);
    }

    /**
     * Comprobar si usuario que no debería existir no existe.
     */
    @Test
    public void test_existe_NOK() {
        LOG.info("Comprobando si usuario que no debería existir no existe.");

        Usuario usuario = new Usuario();
        usuario.setId(new Short("1890"));

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        boolean existe = (boolean) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getUsuarioDAO().existe(usuario);
        });

        assertFalse(existe);
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
        usuario.insertarEnBD();
    }

    /**
     * Comprobar actualizacion de usuario que existe.
     */
    @Test
    public void test_actualizar_OK() {
        LOG.info("Comprobando actualización de usuario que existe.");
        short id = 4;
        Texto nombre = new Texto("Mireya Robotechini");
        String contraseña = "8834";
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.setHabilitado(true);
        usuario.actualizarEnBD();
        assertFalse(usuario.actualizarEnBD().isError());
    }

    /**
     * Comprobar actualizacion de usuario que no existe.
     */
    @Test
    public void test_actualizar_NOK() {
        LOG.info("Comprobando actualización de usuario que no existe.");
        short id = 12345;
        Texto nombre = new Texto("Mireya Robotechini");
        String contraseña = "8834";
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.setHabilitado(true);
        assertTrue(usuario.actualizarEnBD().isError());
    }

}
