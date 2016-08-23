package cl.nullpointer.farmaciapopular.DAO.impl;

import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import cl.nullpointer.farmaciapopular.entidades.FabricanteEntity;
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
     * Obtener listado con todos los fabricantes.
     *
     * @return listado de todos los fabricantes encontrados
     */
    public List<Fabricante> getAllFabricantes() {
        LOG.debug("Ejecutando consulta para obtener fabricantes.");

        String consulta = "SELECT fabricante FROM FabricanteEntity fabricante";

        crearQueryTipica(consulta);

        List<Fabricante> fabricanteList = new ArrayList<>();

        for (FabricanteEntity fabricanteEntity : (List<FabricanteEntity>) getResultList()) {
            Fabricante fabricante = new Fabricante(fabricanteEntity.getId(), fabricanteEntity.getNombre());
            fabricanteList.add(fabricante);
        }
        return fabricanteList;
    }
}
