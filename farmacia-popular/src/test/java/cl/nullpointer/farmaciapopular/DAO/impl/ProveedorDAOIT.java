package cl.nullpointer.farmaciapopular.DAO.impl;

import base.tipoDato.Texto;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.dominio.Proveedor;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class ProveedorDAOIT {

    private static final Logger LOG = Logger.getLogger(ProveedorDAOIT.class);

    public ProveedorDAOIT() {
    }

    /**
     * Comprobar la obtención de un listado de proveedores.
     */
    @Test
    public void test_obtenerListadoProveedores_OK() {
        LOG.info("Comprobando la obtención de un listado de proveedores.");

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        List<Proveedor> proveedorList = (List<Proveedor>) consulta
                .ejecutar((DAOManager DAOManager) -> {
                    return DAOManager.getProveedorDAO().getProveedoresHabilitados();
                });

        assertNotNull(proveedorList);
    }

    /**
     * Comprobar obtención registros actuales de proveedor.
     */
    @Test
    public void test_obtenerRegistrosActuales_OK() {
        LOG.info("Comprobando la obtención de registros actuales de proveedor.");
        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        short registrosActuales = (short) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getProveedorDAO().contarRegistros();
        });

        assertTrue(registrosActuales >= 0);
    }

    /**
     * Comprobar si proveedor que debería existir existe.
     */
    @Test
    public void test_existe_OK() {
        LOG.info("Comprobando si proveedor que debería existir existe.");

        Proveedor proveedor = new Proveedor();
        proveedor.setId(new Short("1"));

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        boolean existe = (boolean) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getProveedorDAO().existe(proveedor);
        });

        assertTrue(existe);
    }

    /**
     * Comprobar si proveedor que no debería existir no existe.
     */
    @Test
    public void test_existe_NOK() {
        LOG.info("Comprobando si proveedor que no debería existir no existe.");

        Proveedor proveedor = new Proveedor();
        proveedor.setId(new Short("1890"));

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        boolean existe = (boolean) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getProveedorDAO().existe(proveedor);
        });

        assertFalse(existe);
    }

    /**
     * Comprobar inserción de proveedor.
     */
    @Test
    public void test_insertar_OK() {
        LOG.info("Comprobando inserción de proveedor.");
        Texto nombre = new Texto("Mireya Robotech");
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.insertarEnBD();
    }

    /**
     * Comprobar actualizacion de proveedor que existe.
     */
    @Test
    public void test_actualizar_OK() {
        LOG.info("Comprobando actualización de proveedor que existe.");
        short id = 1;
        Texto nombre = new Texto("Mireya Robotechens");
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setHabilitado(true);
        proveedor.actualizarEnBD();
        assertFalse(proveedor.actualizarEnBD().isError());
    }

    /**
     * Comprobar actualizacion de proveedor que no existe.
     */
    @Test
    public void test_actualizar_NOK() {
        LOG.info("Comprobando actualización de proveedor que no existe.");
        short id = 12345;
        Texto nombre = new Texto("Mireya Robotechini");
        String contraseña = "8834";
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setHabilitado(true);
        assertTrue(proveedor.actualizarEnBD().isError());
    }

}
