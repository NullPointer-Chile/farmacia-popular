package cl.nullpointer.farmaciapopular.entidades;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.persistence.Transient;

/**
 * Clase de entidad base para que las demas hereden de esta
 *
 * @author Omar Paché
 */
public class TblBase {
    
    public static final long serialVersionUID = 1L;
    
    @Transient
    public final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
