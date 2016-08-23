package cl.nullpointer.farmaciapopular.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Omar Paché
 */
@Entity
@Table(name = "fabricante")
@NamedQueries({
    @NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f")})
public class FabricanteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FabricantePKEntity fabricantePK;

    public FabricanteEntity() {
    }

    public FabricanteEntity(FabricantePKEntity fabricantePK) {
        this.fabricantePK = fabricantePK;
    }

    public FabricanteEntity(int id, String nombre) {
        this.fabricantePK = new FabricantePKEntity(id, nombre);
    }

    public FabricantePKEntity getFabricantePK() {
        return fabricantePK;
    }

    public void setFabricantePK(FabricantePKEntity fabricantePK) {
        this.fabricantePK = fabricantePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fabricantePK != null ? fabricantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FabricanteEntity)) {
            return false;
        }
        FabricanteEntity other = (FabricanteEntity) object;
        return !((this.fabricantePK == null && other.fabricantePK != null) 
                || (this.fabricantePK != null && !this.fabricantePK.equals(other.fabricantePK)));
    }

    @Override
    public String toString() {
        return "cl.nullpointer.farmaciapopular.DAO.Fabricante[ fabricantePK=" + fabricantePK + " ]";
    }
    
}
