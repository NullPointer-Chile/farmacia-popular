package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class UsuarioTest {

    public UsuarioTest() {
    }

    /**
     * Crear usuario con identificador menor a cero. Debería lanzar excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setIdMenorACero() {
        short id = new Short("-1");
        Usuario usuario = new Usuario();
        usuario.setId(id);
    }

    /**
     * Crear usuario con identificador igual a cero. Debería lanzar excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearUsuario_idIgualACero() {
        short id = new Short("0");
        Usuario usuario = new Usuario();
        usuario.setId(id);
    }

    /**
     * Crear usuario con nombre nulo. Debería lanzar excepción nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void test_CrearUsuario_NombreNulo() {
        Texto nombre = null;
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
    }

    /**
     * Crear usuario con largo nombre menor a 4. Debería ser error.
     */
    @Test
    public void test_CrearUsuario_NombreCorto() {
        Texto nombre = new Texto("per");
        Usuario usuario = new Usuario();
        Assert.assertTrue(usuario.setNombre(nombre).isError());
    }

    /**
     * Crear usuario con largo nombre mayor a 30. Debería ser error.
     */
    @Test
    public void test_CrearUsuario_NombreLargo() {
        Texto nombre = new Texto("cuando el sol sale en la montaña las ovejas comen pasto");
        Usuario usuario = new Usuario();
        Assert.assertTrue(usuario.setNombre(nombre).isError());
    }

    /**
     * Crear usuario con contraseña nula. Debería lanzar excepción nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void test_CrearUsuario_ContraseñaNula() {
        String contraseña = null;
        Usuario usuario = new Usuario();
        usuario.setContraseña(contraseña);
    }

    /**
     * Crear usuario con largo contraseña menor a 4. Debería ser error.
     */
    @Test
    public void test_CrearUsuario_ContraseñaCorta() {
        String contraseña = "tin";
        Usuario usuario = new Usuario();
        Assert.assertTrue(usuario.setContraseña(contraseña).isError());
    }

    /**
     * Crear usuario con largo contraseña mayor a 8. Debería ser error.
     */
    @Test
    public void test_CrearUsuario_ContraseñaLarga() {
        String contraseña = "tin pun ron";
        Usuario usuario = new Usuario();
        Assert.assertTrue(usuario.setContraseña(contraseña).isError());
    }

    /**
     * Crear usuario con contraseña con espacios a los lados. Debería quitarlos.
     */
    @Test
    public void test_CrearUsuario_ContraseñaEspacios() {
        String contraseña = " tinigol  ";
        Usuario usuario = new Usuario();
        usuario.setContraseña(contraseña);
        assertEquals("tinigol", usuario.getContraseña());
    }

    /**
     * Crear usuario con todo correcto.
     */
    @Test
    public void test_CrearUsuario_OK() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        String contraseña = "1234";
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.setHabilitado(true);
        assertEquals(1, usuario.getId());
        assertEquals("ABAB", usuario.getNombre().toString());
        assertEquals("1234", usuario.getContraseña());
        assertEquals(true, usuario.estaHabilitado());
    }

    /**
     * Crear usuario y deshabilitarlo.
     */
    @Test
    public void test_DeshabilitarUsuario_OK() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        String contraseña = "1234";
        Usuario usuario = new Usuario();
        usuario.setHabilitado(false);
        assertEquals(false, usuario.estaHabilitado());
    }

    /**
     * Insertar usuario con nombre nulo.
     */
    @Test(expected = NullPointerException.class)
    public void test_insertar_nombreNulo() {
        Texto nombre = null;
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.insertarEnBD();
    }

    /**
     * Insertar usuario con contraseña nula.
     */
    @Test(expected = NullPointerException.class)
    public void test_insertar_contraseñaNula() {
        Texto nombre = new Texto("Abab");
        String contraseña = null;
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.insertarEnBD();
    }

    /**
     * Actualiza usuario con id 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_actualizar_id0() {
        short id = 0;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.actualizarEnBD();
    }

    /**
     * Actualiza usuario con nombre nulo.
     */
    @Test(expected = NullPointerException.class)
    public void test_actualizar_nombreNull() {
        short id = 1;
        Texto nombre = null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.actualizarEnBD();
    }

    /**
     * Actualiza usuario con constraseña nula.
     */
    @Test(expected = NullPointerException.class)
    public void test_actualizar_contraseñaNull() {
        short id = 1;
        Texto nombre = new Texto("Abab");
        String contraseña = null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.actualizarEnBD();
    }

    /**
     * Valida contraseña con contraseña nula. Excepción nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void test_validarContraseña_contraseñaNull() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        String contraseña = "1234";
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.setHabilitado(true);

        usuario.validarContraseña(null);
    }

    /**
     * Valida contraseña con contraseña incorrecta. Lanza error.
     */
    @Test
    public void test_validarContraseña_contraseñaIncorrecta() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        String contraseña = "1234";
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.setHabilitado(true);

        Assert.assertTrue(usuario.validarContraseña("mala".toCharArray()).isError());
    }
    
    /**
     * Valida contraseña con contraseña correcta.
     */
    @Test
    public void test_validarContraseña_contraseñaCorrecta() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        String contraseña = "1234";
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContraseña(contraseña);
        usuario.setHabilitado(true);

        Assert.assertFalse(usuario.validarContraseña("1234".toCharArray()).isError());
    }

}
