package cl.nullpointer.farmaciapopular.DAO.impl;

import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Omar Paché
 */
public class FechaHoraDAOIT {
    private static final Logger LOG = Logger.getLogger(FechaHoraDAOIT.class);
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    public FechaHoraDAOIT() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Comprobar que la fecha hora obtenida desde el servidor sea nula.
     */
    @Test
    public void test_comprobarFechaDesdeServidor_OK() {
        LOG.info("Comprobando que la fecha obtenida desde el servidor sea nula.");
        
        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        LocalDateTime fechaHoraResultado = (LocalDateTime) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getFechaHoraDAO().getFechaHoraNow();
        });

        LOG.info(fechaHoraResultado);
        assertNotNull(fechaHoraResultado);
    }
}
