package cl.nullpointer.farmaciapopular.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author omar
 */
@Embeddable
public class FabricantePKEntity implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    public FabricantePKEntity() {
    }

    public FabricantePKEntity(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        int hash = 0;
        hash += (int) id;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FabricantePKEntity)) {
            return false;
        }
        FabricantePKEntity other = (FabricantePKEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return !((this.nombre == null && other.nombre != null) 
                || (this.nombre != null && !this.nombre.equals(other.nombre)));
    }

    @Override
    public String toString() {
        return "cl.nullpointer.farmaciapopular.DAO.FabricantePK[ id=" + id + ", nombre=" + nombre + " ]";
    }
    
}
