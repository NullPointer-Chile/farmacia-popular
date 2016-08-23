package cl.nullpointer.farmaciapopular.DAO.impl;

import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import java.util.List;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Omar Paché
 */
public class FabricanteDAOIT {

    private static final Logger LOG = Logger.getLogger(FabricanteDAOIT.class);

    public FabricanteDAOIT() {
    }

    /**
     * Comprobar la obtención de un listado de fabricantes.
     */
    @Test
    public void test_obtenetListadoFabricantes_OK() {
        LOG.info("Comprobando la obtención de un listado de fabricantes.");

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        List<Fabricante> fabricanteList = (List<Fabricante>) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getFabricanteDAO().getAllFabricantes();
        });

        assertNotNull(fabricanteList);
        LOG.info("Fabricantes encontrados en BD: " + fabricanteList.size());
    }

}
