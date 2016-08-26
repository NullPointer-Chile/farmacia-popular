package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import base.validacion.ResultadoMetodo;
import base.validacion.impl.ResultadoMetodoImpl;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.impl.DAOManager;
import java.util.List;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class Usuario {

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

    public final void setNombre(Texto nombre) {
        if (nombre == null) {
            throw new NullPointerException("Nombre usuario nulo.");
        } else if (nombre.validarLargo(4, 30).isError()) {
            throw new IllegalArgumentException(nombre.validarLargo(4, 30).getMensaje());
        } else {
            this.nombre = nombre;
        }
    }

    public String getContraseña() {
        return contraseña;
    }

    public final void setContraseña(String contraseña) {
        if (contraseña == null) {
            throw new NullPointerException("Contraseña nula.");
        } else {
            contraseña = contraseña.trim();
            if (contraseña.length() < 4) {
                throw new IllegalArgumentException("Contraseña muy corta.");
            } else if (contraseña.length() > 8) {
                throw new IllegalArgumentException("Contraseña muy larga.");
            } else {
                this.contraseña = contraseña;
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

}
