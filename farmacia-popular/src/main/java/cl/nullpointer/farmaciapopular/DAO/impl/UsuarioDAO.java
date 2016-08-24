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
     * Inserta un nuevo registro de usuario en base de datos.
     *
     * @param usuario
     */
    public void insertar(Usuario usuario) {
        insertar(aEntity(usuario));
    }

    /**
     * Modifica un registro existente de usuario en base de datos.
     *
     * @param usuario
     */
    public void actualizar(Usuario usuario) {
        if(!existe(usuario)){
            throw new IllegalStateException("Usuario a actualizar no existe.");
        } else {
            actualizar(aEntity(usuario));
        }
    }

    /**
     * Cuenta los registros actuales de usuario en base de datos.
     *
     * @return
     */
    public short contarRegistros() {
        LOG.debug("Obteniendo registros actuales de usuario.");
        String consulta = " SELECT COUNT(usuario) FROM UsuarioEntity usuario";
        crearQueryTipicaSoloLectura(consulta);
        Long registrosActuales = (Long) getSingleResult();
        return registrosActuales.shortValue();
    }

    /**
     * Comprueba si un registro de usuario existe en BD.
     *
     * @param usuario
     * @return
     */
    public boolean existe(Usuario usuario) {
        LOG.debug("Comprobando si existe usuario con id: " + usuario.getId());
        String consulta = " SELECT COUNT(usuario) FROM UsuarioEntity usuario"
                + " WHERE usuario.id=:id";
        crearQueryTipicaSoloLectura(consulta);
        setParameter("id", usuario.getId());
        return (Long) getSingleResult() > 0;
    }

    /**
     * Fabrica objetos usuario desde la entidad usuario.
     *
     * @param usuarioEntity
     * @return
     */
    private Usuario fabricarUsuario(UsuarioEntity usuarioEntity) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioEntity.getId());
        usuario.setNombre(new Texto(usuarioEntity.getNombre()));
        usuario.setContraseña(usuarioEntity.getContraseña());
        usuario.setHabilitado(usuarioEntity.getHabilitado() == 1);
        return usuario;
    }

    private UsuarioEntity aEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre().toString(),
                usuario.getContraseña(),
                usuario.estaHabilitado() ? new Short("1") : new Short("0")
        );
    }
}
