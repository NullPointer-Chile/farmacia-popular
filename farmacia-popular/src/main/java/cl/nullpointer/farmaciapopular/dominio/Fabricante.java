package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import base.validacion.ResultadoMetodo;
import base.validacion.impl.ResultadoMetodoImpl;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.impl.DAOManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Clase que modela al fabricante de un producto.
 *
 * @author Omar Paché
 */
public class Fabricante {

    private static final Logger LOG = Logger.getLogger(Fabricante.class);

    public static short HABILITADO = 1;
    public static short DESHABILITADO = 0;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private short id;
    private Texto nombre;
    private short habilitado;

    public Fabricante() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Texto getNombre() {
        return nombre;
    }

    public void setNombre(Texto nombre) {
        if (nombre == null) {
            throw new NullPointerException("Nombre fabricante nulo.");
        } else if (nombre.validarLargo(4, 30).isError()) {
            throw new IllegalArgumentException(nombre.validarLargo(4, 30).getMensaje());
        } else {
            this.nombre = nombre;
        }
    }

    public short getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(short habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Insertar un fabricante.
     *
     * @return el resultado de la operación, error o sin error
     */
    public ResultadoMetodo insertar() {
        if (nombre == null) {
            throw new NullPointerException("Nombre nulo.");
        } else {
            ProcedimientoTransaccionalDAO procedimiento = new DAOManager();

            return (ResultadoMetodo) procedimiento.transaccion((DAOManager DAOManager) -> {
                short registrosActuales = DAOManager.getFabricanteDAO().contar();
                this.setId((short) (registrosActuales + 1));
                DAOManager.getFabricanteDAO().insertar(this);
                return ResultadoMetodoImpl.setSinError();
            });
        }
    }

    /**
     * Deshabilitar un fabricante.
     *
     * @param fabricante fabricante a deshabilitar.
     * @return error o sin error dependiendo el resultado de la operación.
     */
    public static ResultadoMetodo deshabilitar(Fabricante fabricante) {
        LOG.debug("Deshabilitando fabricante");

        // Setear propiedad de habilitado en 0
        fabricante.setHabilitado((short) 0);

        ProcedimientoTransaccionalDAO procedimiento = new DAOManager();
        ResultadoMetodo resultado = (ResultadoMetodo) procedimiento.transaccion((DAOManager daoManager) -> {
            return daoManager.getFabricanteDAO().deshabilitar(fabricante);
        });

        return resultado;

    }

    /**
     * Se obtiene listado de todos los fabricantes.
     *
     * @return listado de Fabricantes
     */
    public static List<Fabricante> getAll() {
        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        return (List< Fabricante>) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getFabricanteDAO().getAllFabricantesHabilitados();
        });
    }

    @Override
    public String toString() {
        return nombre.toString();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
