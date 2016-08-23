package cl.nullpointer.farmaciapopular.DAO.impl;

import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Omar Paché
 */
public class FabricanteDAO extends EclipseLinkDAO {

    public static final Logger LOG = Logger.getLogger(FabricanteDAO.class);

    public FabricanteDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Obtener listado con todos los farbicantes.
     * 
     * @return 
     */
    public List<Fabricante> getAllFabricantes() {
        LOG.debug("Ejecutando consulta para obtener alumnos.");

        String consulta = " SELECT fabricante FROM FabricanteEntity fabricante";

        crearQueryTipica(consulta);

        List<Fabricante> fabrocanteList = new ArrayList<>();

//        for (FabricanteEntity alumnoEntity : (List<FabricanteEntity>) getResultList()) {
//            Fabricante fabricante = new Fabricante(id, nombre);
//            fabrocanteList.add(fabricante);
//        }
        return fabrocanteList;
    }
}
