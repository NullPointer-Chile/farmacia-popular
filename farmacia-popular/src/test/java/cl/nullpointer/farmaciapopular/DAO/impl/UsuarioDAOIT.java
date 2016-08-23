package cl.nullpointer.farmaciapopular.DAO.impl;

import cl.nullpointer.farmaciapopular.DAO.ProcedimientoNoTransaccionalDAO;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class UsuarioDAOIT {
    
    private static final Logger LOG = Logger.getLogger(UsuarioDAOIT.class);
    
    public UsuarioDAOIT() {
    }

    /**
     * Comprobar la obtención de un listado de usuarios.
     */
    @Test
    public void test_obtenetListadoUsuarios_OK() {
        LOG.info("Comprobando la obtención de un listado de usuarios.");

        ProcedimientoNoTransaccionalDAO consulta = new DAOManager();

        List<Usuario> usuarioList = (List<Usuario>) consulta
                .ejecutar((DAOManager DAOManager) -> {
            return DAOManager.getUsuarioDAO().getUsuariosHabilitados();
        });

        assertNotNull(usuarioList);
    }
    
}
