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

    public static final short HABILITADO = 1;
    public static final short DESHABILITADO = 0;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private short id;
    private Texto nombre;
    private short habilitado;

    public Fabricante() {
    }

    /**
     * Obtener el identificador del fabricante.
     *
     * @return el ID del fabricante.
     */
    public short getId() {
        return id;
    }

    /**
     * Asignar un ID al fabricante.
     *
     * @param id identificador del fabricante
     * @throws IllegalArgumentException si el ID es invalido (igual o menos a 0)
     */
    public void setId(short id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error. Id fabricante menor o igual a cero");
        } else {
            this.id = id;
        }
    }

    /**
     * Obtener el nombre el nombre del fabricante.
     *
     * @return El nombre del fabricante
     */
    public Texto getNombre() {
        return nombre;
    }

    /**
     * Asignar un nombre al fabricante.
     *
     * @param nombre el nombre como tipo Texto
     * @return el resultado de la operacion. Error o sin error
     */
    public ResultadoMetodo setNombre(Texto nombre) {
        if (nombre == null) {
            return ResultadoMetodoImpl.setError("Nombre fabricante nulo.");
        } else if (nombre.validarLargo(4, 30).isError()) {
            return ResultadoMetodoImpl.setError(nombre.validarLargo(4, 30).getMensaje());
        } else {
            this.nombre = nombre;
            return ResultadoMetodoImpl.setSinError();
        }
    }

    /**
     * Obtener la variable habilitado.
     *
     * @return
     */
    public short getHabilitado() {
        return habilitado;
    }

    /**
     * Asignar la variable habilitado.
     *
     * @param habilitado el estado de la variable habilitado. 1:habilidato 0:deshabilitado
     */
    public void setHabilitado(short habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Insertar un fabricante.
     *
     * @return el resultado de la operación, error o sin error
     * @throws NullPointerException si el nombre es nulo
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
     * Actualiza un fabricante.
     *
     * @return el resultado de la operación, error o sin error
     */
    public ResultadoMetodo actualizar() {
        if (id == 0) {
            throw new IllegalArgumentException("ID invalido.");
        }
        if (nombre == null) {
            throw new NullPointerException("Nombre nulo.");
        } else {
            ProcedimientoTransaccionalDAO procedimiento = new DAOManager();

            return (ResultadoMetodo) procedimiento.transaccion((DAOManager DAOManager) -> {
                DAOManager.getFabricanteDAO().actualizar(this);
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

    /**
     * Obtener el nombre del fabricante.
     *
     * @return el nombre del fabricante
     */
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
