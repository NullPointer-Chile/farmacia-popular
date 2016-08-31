package cl.nullpointer.farmaciapopular.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
@Entity
@Table(name = "proveedor")
public class ProveedorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private short habilitado;

    public ProveedorEntity() {
    }

    public ProveedorEntity(Short id) {
        this.id = id;
    }

    public ProveedorEntity(Short id, String nombre, short habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.habilitado = habilitado;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(short habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorEntity)) {
            return false;
        }
        ProveedorEntity other = (ProveedorEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "cl.nullpointer.farmaciapopular.entidades.Proveedor[ id=" + id + " ]";
    }
    
}
