package cl.nullpointer.farmaciapopular.DAO.impl;

import base.tipoDato.Texto;
import cl.nullpointer.farmaciapopular.dominio.Proveedor;
import cl.nullpointer.farmaciapopular.entidades.ProveedorEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class ProveedorDAO extends EclipseLinkDAO {

    public static final Logger LOG = Logger.getLogger(ProveedorDAO.class);

    public ProveedorDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Obtener listado con todos los proveedores habilitados.
     *
     * @return
     */
    public List<Proveedor> getProveedoresHabilitados() {

        LOG.debug("Ejecutando consulta para obtener proveedores habilitados.");

        String consulta = " SELECT proveedor FROM ProveedorEntity proveedor"
                + " WHERE proveedor.habilitado=1";

        crearQueryTipica(consulta);

        List<Proveedor> proveedorList = new ArrayList<>();

        for (ProveedorEntity proveedorEntity : (List<ProveedorEntity>) getResultList()) {
            proveedorList.add(fabricarProveedor(proveedorEntity));
        }

        return proveedorList;
    }

    /**
     * Inserta un nuevo registro de proveedor en base de datos.
     *
     * @param proveedor
     */
    public void insertar(Proveedor proveedor) {
        insertar(aEntity(proveedor));
    }

    /**
     * Modifica un registro existente de proveedor en base de datos.
     *
     * @param proveedor
     */
    public void actualizar(Proveedor proveedor) {
        if(!existe(proveedor)){
            throw new IllegalStateException("Proveedor a actualizar no existe.");
        } else {
            actualizar(aEntity(proveedor));
        }
    }

    /**
     * Cuenta los registros actuales de proveedor en base de datos.
     *
     * @return
     */
    public short contarRegistros() {
        LOG.debug("Obteniendo registros actuales de proveedor.");
        String consulta = " SELECT COUNT(proveedor) FROM ProveedorEntity proveedor";
        crearQueryTipicaSoloLectura(consulta);
        Long registrosActuales = (Long) getSingleResult();
        return registrosActuales.shortValue();
    }

    /**
     * Comprueba si un registro de proveedor existe en BD.
     *
     * @param proveedor
     * @return
     */
    public boolean existe(Proveedor proveedor) {
        LOG.debug("Comprobando si existe proveedor con id: " + proveedor.getId());
        String consulta = " SELECT COUNT(proveedor) FROM ProveedorEntity proveedor"
                + " WHERE proveedor.id=:id";
        crearQueryTipicaSoloLectura(consulta);
        setParametro("id", proveedor.getId());
        return (Long) getSingleResult() > 0;
    }

    /**
     * Fabrica objetos proveedor desde la entidad proveedor.
     *
     * @param proveedorEntity
     * @return
     */
    private Proveedor fabricarProveedor(ProveedorEntity proveedorEntity) {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(proveedorEntity.getId());
        proveedor.setNombre(new Texto(proveedorEntity.getNombre()));
        proveedor.setHabilitado(proveedorEntity.getHabilitado() == 1);
        return proveedor;
    }

    private ProveedorEntity aEntity(Proveedor proveedor) {
        return new ProveedorEntity(
                proveedor.getId(),
                proveedor.getNombre().toString(),
                proveedor.estaHabilitado() ? new Short("1") : new Short("0")
        );
    }
}
