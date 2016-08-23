package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
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
    public void test_CrearUsuario_idMenorACero() {
        short id = new Short("-1");
        Texto nombre = new Texto("Adalberto de la Hoya");
        String contraseña = "pascualina";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con identificador igual a cero. Debería lanzar excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearUsuario_idIgualACero() {
        short id = new Short("0");
        Texto nombre = new Texto("Adalberto de la Hoya");
        String contraseña = "pascualina";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con nombre nulo. Debería lanzar excepción nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void test_CrearUsuario_NombreNulo() {
        short id = new Short("1");
        Texto nombre = null;
        String contraseña = "pascualina";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con largo nombre menor a 4. Debería lanzar excepción
     * illegalargument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearUsuario_NombreCorto() {
        short id = new Short("1");
        Texto nombre = new Texto("per");
        String contraseña = "pascualina";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con largo nombre mayor a 30. Debería lanzar excepción
     * illegalargument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearUsuario_NombreLargo() {
        short id = new Short("1");
        Texto nombre = new Texto("cuando el sol sale en la montaña las ovejas comen pasto");
        String contraseña = "pascualina";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con contraseña nula. Debería lanzar excepción nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void test_CrearUsuario_ContraseñaNula() {
        short id = new Short("1");
        Texto nombre = new Texto("Adalberto de la Hoya");
        String contraseña = null;
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con largo contraseña menor a 4. Debería lanzar excepción
     * illegalargument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearUsuario_ContraseñaCorta() {
        short id = new Short("1");
        Texto nombre = new Texto("Adalberto de la Hoya");
        String contraseña = "tin";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con largo contraseña mayor a 8. Debería lanzar excepción
     * illegalargument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearUsuario_ContraseñaLarga() {
        short id = new Short("1");
        Texto nombre = new Texto("Adalberto de la Hoya");
        String contraseña = "tin pun ron";
        Usuario usuario = new Usuario(id, nombre, contraseña);
    }

    /**
     * Crear usuario con todo correcto.
     */
    @Test
    public void test_CrearUsuario_OK() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        String contraseña = "1234";
        Usuario usuario = new Usuario(id, nombre, contraseña);
        assertEquals("ABAB", usuario.getNombre().toString());
    }

}
