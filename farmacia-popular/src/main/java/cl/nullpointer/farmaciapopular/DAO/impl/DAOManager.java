package cl.nullpointer.farmaciapopular.DAO.impl;

import base.utilidades.Global;
import base.utilidades.Utils;
import base.validacion.impl.ResultadoMetodoImpl;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoDAO;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoTransaccionalDAO;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.RollbackException;
import org.apache.log4j.Logger;

/**
 *
 * @author Omar Paché
 */
public class DAOManager implements ProcedimientoNoTransaccionalDAO, ProcedimientoTransaccionalDAO {

    public static final Logger LOG = Logger.getLogger(DAOManager.class);

    protected final EntityManager entityManager;
    protected FechaHoraDAO fechaHoraDAO = null;

    public DAOManager() {
        entityManager = Global.emf.createEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT);

    }

    public FechaHoraDAO getFechaHoraDAO() {
        if (fechaHoraDAO == null) {
            fechaHoraDAO = new FechaHoraDAO(entityManager);
        }
        return fechaHoraDAO;
    }

    @Override
    public Object ejecutar(ProcedimientoDAO procedimientoDAO) {
        LOG.debug("ejecutando procedimiento no transaccional...");
        try {
            Object ret = procedimientoDAO.ejecutar(this);
            LOG.debug("procedimiento ejecutado correctamente...");
            return ret;
        } catch (Exception exception) {
            return ResultadoMetodoImpl.setError(exception.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Object transaccion(ProcedimientoDAO procedimientoDAO) {
        LOG.debug("iniciando transacción...");
        entityManager.getTransaction().begin();
        try {
            Object retorno = procedimientoDAO.ejecutar(this);
            entityManager.getTransaction().commit();
            LOG.debug("transacción ejecutada correctamente...");
            return retorno;
        } catch (RollbackException rollbackException) {
            LOG.debug(rollbackException.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return ResultadoMetodoImpl.setError(rollbackException.getMessage());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            Utils.crearLogExcepcion(e);
            return ResultadoMetodoImpl.setError(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
