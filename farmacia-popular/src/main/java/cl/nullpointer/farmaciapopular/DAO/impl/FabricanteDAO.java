package cl.nullpointer.farmaciapopular.DAO.impl;

import base.tipoDato.Texto;
import base.validacion.ResultadoMetodo;
import base.validacion.impl.ResultadoMetodoImpl;
import cl.nullpointer.farmaciapopular.dominio.Fabricante;
import cl.nullpointer.farmaciapopular.entidades.FabricanteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 * Clase encargada de comunicarse con la capa de datos, relacionados con los Fabricantes.
 *
 * @author Omar Paché
 */
public class FabricanteDAO extends EclipseLinkDAO {

    public static final Logger LOG = Logger.getLogger(FabricanteDAO.class);

    public FabricanteDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Obtener listado con todos los fabricantes desde la BD.
     *
     * @return listado de todos los fabricantes encontrados
     */
    public List<Fabricante> getAllFabricantesHabilitados() {
        LOG.debug("Ejecutando consulta para obtener fabricantes.");

        String consulta = "SELECT fabricante FROM FabricanteEntity fabricante "
                + "WHERE fabricante.habilitado = :habilitado";

        crearQueryTipica(consulta);
        setParametro("habilitado", Fabricante.HABILITADO);

        List<Fabricante> fabricanteList = new ArrayList<>();

        for (FabricanteEntity fabricanteEntity : (List<FabricanteEntity>) getResultList()) {
            fabricanteList.add(construirFabricante(fabricanteEntity));
        }

        return fabricanteList;
    }

    /**
     * Construye objetos Fabricante desde la entidad fabricante.
     *
     * @param fabricanteEntity una entidad de tipo fabricante
     * @return un objeto que representa al Fabricante
     */
    private Fabricante construirFabricante(FabricanteEntity fabricanteEntity) {
        Fabricante fabricante = new Fabricante(
                new Texto(fabricanteEntity.getNombre())
        );
        fabricante.setId(fabricanteEntity.getId());
        fabricante.setHabilitado(fabricanteEntity.getHabilitado());

        return fabricante;
    }

    /**
     * Cuenta los fabricantes existentes en BD.
     *
     * @return la cantidad de fabricantes entontrados
     */
    public short contar() {
        LOG.debug("Obteniendo registros actuales de fabricantes.");

        String consulta = " SELECT COUNT(fabricante) FROM FabricanteEntity fabricante";
        crearQueryTipicaSoloLectura(consulta);
        Long registrosActuales = (Long) getSingleResult();

        return registrosActuales.shortValue();
    }

    /**
     * Inserta un nuevo registro de fabricante en BD.
     *
     * @param fabricante el objeto fabricante a insertar
     * @return
     */
    public ResultadoMetodo insertar(Fabricante fabricante) {
        insertar(toEntity(fabricante));
        return ResultadoMetodoImpl.setSinError();
    }

    /**
     * Deshabilitar un fabricante.
     *
     * @param fabricante el objeto fabricante a deshabilitar
     * @return
     */
    public ResultadoMetodo deshabilitar(Fabricante fabricante) {
        super.actualizar(toEntity(fabricante));
        return ResultadoMetodoImpl.setSinError();
    }

    /**
     * Convierte un objeto Fabricante en su respectiva entidad.
     *
     * @param fabricante el objeto Fabricante
     * @return la entidad fabricante
     */
    private FabricanteEntity toEntity(Fabricante fabricante) {
        FabricanteEntity fabricanteEntity = new FabricanteEntity(
                fabricante.getId(),
                fabricante.getNombre().toString()
        );
        fabricanteEntity.setHabilitado(fabricante.getHabilitado());

        return fabricanteEntity;
    }
}
