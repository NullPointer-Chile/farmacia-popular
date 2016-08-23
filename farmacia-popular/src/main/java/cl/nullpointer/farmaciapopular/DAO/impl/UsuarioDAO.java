package cl.nullpointer.farmaciapopular.DAO.impl;

import base.tipoDato.Texto;
import cl.nullpointer.farmaciapopular.dominio.Usuario;
import cl.nullpointer.farmaciapopular.entidades.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class UsuarioDAO extends EclipseLinkDAO {

    public static final Logger LOG = Logger.getLogger(UsuarioDAO.class);

    public UsuarioDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Obtener listado con todos los usuarios habilitados.
     *
     * @return
     */
    public List<Usuario> getUsuariosHabilitados() {

        LOG.debug("Ejecutando consulta para obtener usuarios habilitados.");

        String consulta = " SELECT usuario FROM UsuarioEntity usuario"
                + " WHERE usuario.habilitado=1";

        crearQueryTipica(consulta);

        List<Usuario> usuarioList = new ArrayList<>();

        for (UsuarioEntity usuarioEntity : (List<UsuarioEntity>) getResultList()) {
            usuarioList.add(fabricarUsuario(usuarioEntity));
        }

        return usuarioList;
    }

    /**
     * Fabrica objetos usuario desde la entidad usuario. Sólo usuarios
     * habilitados.
     *
     * @param usuarioEntity
     * @return
     */
    private Usuario fabricarUsuario(UsuarioEntity usuarioEntity) {
        Usuario usuario = new Usuario(
                usuarioEntity.getId(),
                new Texto(usuarioEntity.getNombre()),
                usuarioEntity.getContraseña()
        );

        return usuario;
    }
}
