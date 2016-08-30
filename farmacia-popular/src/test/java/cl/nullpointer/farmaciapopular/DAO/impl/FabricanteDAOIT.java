package cl.nullpointer.farmaciapopular.DAO.impl;

import base.tipoDato.Texto;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import java.util.List;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test de integración para la clase DAO de fabricante.
 *
 * @author Omar Paché
 */
public class FabricanteDAOIT {

    private static final Logger LOG = Logger.getLogger(FabricanteDAOIT.class);

    public FabricanteDAOIT() {
    }

    /**
     * Comprobar la obtención de un listado de fabricantes desde la BD.
     */
    @Test
    public void test_obtenetListadoFabricantes_OK() {
        LOG.info("Comprobando la obtención de un listado de fabricantes.");

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        List<Fabricante> fabricanteList = (List<Fabricante>) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getFabricanteDAO().getAllFabricantesHabilitados();
        });

        assertNotNull(fabricanteList);
        LOG.info("Fabricantes encontrados en BD: " + fabricanteList.size());
    }

    /**
     * Comprobar obtención registros actuales de fabricantes.
     */
    @Test
    public void test_contar_OK() {
        LOG.info("Comprobando la obtención de registros actuales de fabricantes.");

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        short registrosActuales = (short) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getFabricanteDAO().contar();
        });

        assertTrue(registrosActuales >= 0);
        LOG.info("Fabricantes encontrados en BD: " + registrosActuales);
    }

    /**
     * Comprobar inserción de fabricante.
     */
    @Test
    public void test_insertar_OK() {
        LOG.info("Comprobando inserción de un fabricante.");

        Texto nombre = new Texto("Bayer");
        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);
        fabricante.setHabilitado((short) 1);
        fabricante.insertar();
    }
}
