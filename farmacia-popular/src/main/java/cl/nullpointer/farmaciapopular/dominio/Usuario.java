package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import base.validacion.ResultadoMetodo;
import base.validacion.impl.ResultadoMetodoImpl;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.impl.DAOManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class Usuario {

    public static final int LARGO_MINIMO_NOMBRE = 4;
    public static final int LARGO_MAXIMO_NOMBRE = 30;
    public static final int LARGO_MINIMO_CONTRASENA = 4;
    public static final int LARGO_MAXIMO_CONTRASENA = 8;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private short id;
    private Texto nombre;
    private String contraseña;
    private boolean habilitado;

    public Usuario() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id usuario menor o igual a cero");
        } else {
            this.id = id;
        }
    }

    public Texto getNombre() {
        return nombre;
    }

    public final ResultadoMetodo setNombre(Texto nombre) {
        if (nombre == null) {
            throw new NullPointerException("Nombre usuario nulo.");
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

    public String getContraseña() {
        return contraseña;
    }

    public final ResultadoMetodo setContraseña(String contraseña) {
        if (contraseña == null) {
            throw new NullPointerException("Contraseña nula.");
        } else {
            contraseña = contraseña.trim();
            if (contraseña.length() < LARGO_MINIMO_CONTRASENA) {
                return ResultadoMetodoImpl.setError("Contraseña muy corta.");
            } else if (contraseña.length() > LARGO_MAXIMO_CONTRASENA) {
                return ResultadoMetodoImpl.setError("Contraseña muy larga.");
            } else {
                this.contraseña = contraseña;
                return ResultadoMetodoImpl.setSinError();
            }
        }
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean estaHabilitado() {
        return habilitado;
    }

    /**
     *
     * @return
     */
    public static List<Usuario> getUsuariosHabilitadosBD() {
        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();
        return (List< Usuario>) consulta.ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getUsuarioDAO().getUsuariosHabilitados();
        });
    }

    /**
     *
     * @return
     */
    public ResultadoMetodo insertarEnBD() {
        if (nombre == null) {
            throw new NullPointerException("Nombre nulo.");
        } else if (contraseña == null) {
            throw new NullPointerException("Contraseña nula.");
        } else {
            ProcedimientoTransaccionalDAO procedimiento = new DAOManager();
            return (ResultadoMetodo) procedimiento.
                    transaccion((DAOManager DAOManager) -> {
                        short registrosActuales = DAOManager.getUsuarioDAO().contarRegistros();
                        this.setId((short) (registrosActuales + 1));
                        this.setHabilitado(true);
                        DAOManager.getUsuarioDAO().insertar(this);
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
        } else if (contraseña == null) {
            throw new NullPointerException("Contraseña nula.");
        } else {
            ProcedimientoTransaccionalDAO procedimiento = new DAOManager();
            return (ResultadoMetodo) procedimiento.
                    transaccion((DAOManager DAOManager) -> {
                        DAOManager.getUsuarioDAO().actualizar(this);
                        return ResultadoMetodoImpl.setSinError();
                    });
        }
    }

    public ResultadoMetodo validarContraseña(char[] contraseña) {
        if (contraseña == null) {
            throw new NullPointerException("Contraseña nula.");
        } else if (Arrays.equals(getContraseña().toCharArray(), contraseña)) {
            return ResultadoMetodoImpl.setSinError();
        } else {
            return ResultadoMetodoImpl.setError("Contraseña Incorrecta.");
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
