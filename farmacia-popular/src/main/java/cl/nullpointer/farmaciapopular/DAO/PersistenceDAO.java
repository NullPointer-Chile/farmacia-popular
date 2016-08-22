package cl.nullpointer.farmaciapopular.DAO;

/**
 *
 * @author Omar Paché
 */
public interface PersistenceDAO {

    void insertar(Object object);

    Object actualizar(Object object);

    void eliminar(Object object);
}
