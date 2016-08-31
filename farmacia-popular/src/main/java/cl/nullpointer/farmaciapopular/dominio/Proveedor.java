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

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class Proveedor {

    public static final int LARGO_MINIMO_NOMBRE = 4;
    public static final int LARGO_MAXIMO_NOMBRE = 30;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private short id;
    private Texto nombre;
    private boolean habilitado;

    public Proveedor() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id proveedor menor o igual a cero");
        } else {
            this.id = id;
        }
    }

    public Texto getNombre() {
        return nombre;
    }

    public ResultadoMetodo setNombre(Texto nombre) {
        if (nombre == null) {
            throw new NullPointerException("Nombre proveedor nulo.");
        } else {
            ResultadoMetodo resultadoMetodo = nombre.validarLargo(LARGO_MINIMO_NOMBRE, LARGO_MAXIMO_NOMBRE);
            if (resultadoMetodo.isError()) {
                return resultadoMetodo;
            } else {
                this.nombre = nombre;
                return ResultadoMetodoImpl.setSinError();
            }
        }
    }

    public boolean estaHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     *
     * @return
     */
    public static List<Proveedor> getProveedoresHabilitadosBD() {
        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        return (List<Proveedor>) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getProveedorDAO().getProveedoresHabilitados();
        });
    }

    /**
     *
     * @return
     */
    public ResultadoMetodo insertarEnBD() {
        if (nombre == null) {
            throw new NullPointerException("Nombre nulo.");
        } else {
            ProcedimientoTransaccionalDAO procedimiento = new DAOManager();
            return (ResultadoMetodo) procedimiento.
                    transaccion((DAOManager DAOManager) -> {
                        short registrosActuales = DAOManager.getProveedorDAO().contarRegistros();
                        this.setId((short) (registrosActuales + 1));
                        this.setHabilitado(true);
                        DAOManager.getProveedorDAO().insertar(this);
                        return ResultadoMetodoImpl.setSinError();
                    });
        }
    }

    /**
     *
     * @return
     */
    public ResultadoMetodo actualizarEnBD() {
        if (id == 0) {
            throw new IllegalArgumentException("Id igual a cero.");
        } else if (nombre == null) {
            throw new NullPointerException("Nombre nulo.");
        } else {
            ProcedimientoTransaccionalDAO procedimiento = new DAOManager();
            return (ResultadoMetodo) procedimiento.
                    transaccion((DAOManager DAOManager) -> {
                        DAOManager.getProveedorDAO().actualizar(this);
                        return ResultadoMetodoImpl.setSinError();
                    });
        }
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
