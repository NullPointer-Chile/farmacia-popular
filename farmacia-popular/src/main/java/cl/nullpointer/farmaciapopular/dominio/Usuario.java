package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class Usuario {

    private final short id;
    private Texto nombre;
    private String contraseña;
    private boolean habilitado;

    public Usuario(short id, Texto nombre, String contraseña) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id usuario menor o igual a cero");
        } else {
            this.id = id;
            setNombre(nombre);
            setContraseña(contraseña);
            habilitado = true;
        }
    }

    public short getId() {
        return id;
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
        } else if (contraseña.length() < 4) {
            throw new IllegalArgumentException("Contraseña muy corta.");
        } else if (contraseña.length() > 8) {
            throw new IllegalArgumentException("Contraseña muy larga.");
        } else {
            this.contraseña = contraseña;
        }
    }

    public void deshabilitar() {
        this.habilitado = false;
    }

    public boolean estaHabilitado() {
        return habilitado;
    }

}
