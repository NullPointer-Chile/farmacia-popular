package cl.nullpointer.farmaciapopular.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Omar Paché
 */
@Entity
@Table(name = "fabricante")
public class FabricanteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private short id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    public FabricanteEntity() {
    }

    public FabricanteEntity(short id) {
        this.id = id;
    }

    public FabricanteEntity(short id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FabricanteEntity other = (FabricanteEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "cl.nullpointer.farmaciapopular.entidades.Fabricante[ id=" + id + " ]";
    }

}
