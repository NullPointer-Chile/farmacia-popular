package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import base.validacion.ResultadoMetodo;
import base.validacion.impl.ResultadoMetodoImpl;
import cl.nullpointer.farmaciapopular.DAO.ProcedimientoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.DAO.impl.DAOManager;

/**
 * Clase que modela al fabricante de un producto.
 *
 * @author Omar Paché
 */
public class Fabricante {

    private short id;
    private Texto nombre;

    public Fabricante(Texto nombre) {
        if (nombre == null) {
            throw new NullPointerException("Nombre fabricante nulo.");
        } else if (nombre.validarLargo(4, 30).isError()) {
            throw new IllegalArgumentException(nombre.validarLargo(4, 30).getMensaje());
        } else {
            this.nombre = nombre;
        }
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
}
