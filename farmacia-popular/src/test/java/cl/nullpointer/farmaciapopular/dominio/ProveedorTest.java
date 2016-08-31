package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class ProveedorTest {

    public ProveedorTest() {
    }

    /**
     * Crear proveedor con identificador menor a cero. Debería lanzar excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setIdMenorACero() {
        short id = new Short("-1");
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
    }

    /**
     * Crear proveedor con identificador igual a cero. Debería lanzar excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_CrearProveedor_idIgualACero() {
        short id = new Short("0");
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
    }

    /**
     * Crear proveedor con nombre nulo. Debería lanzar excepción nullpointer.
     */
    @Test(expected = NullPointerException.class)
    public void test_CrearProveedor_NombreNulo() {
        Texto nombre = null;
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
    }

    /**
     * Crear proveedor con largo nombre menor a 4. Debería ser error.
     */
    @Test
    public void test_CrearProveedor_NombreCorto() {
        Texto nombre = new Texto("per");
        Proveedor proveedor = new Proveedor();
        Assert.assertTrue(proveedor.setNombre(nombre).isError());
    }

    /**
     * Crear proveedor con largo nombre mayor a 30. Debería ser error.
     */
    @Test
    public void test_CrearProveedor_NombreLargo() {
        Texto nombre = new Texto("cuando el sol sale en la montaña las ovejas comen pasto");
        Proveedor proveedor = new Proveedor();
        Assert.assertTrue(proveedor.setNombre(nombre).isError());
    }

    /**
     * Crear proveedor con todo correcto.
     */
    @Test
    public void test_CrearProveedor_OK() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setHabilitado(true);
        assertEquals(1, proveedor.getId());
        assertEquals("ABAB", proveedor.getNombre().toString());
        assertEquals(true, proveedor.estaHabilitado());
    }

    /**
     * Crear proveedor y deshabilitarlo.
     */
    @Test
    public void test_DeshabilitarProveedor_OK() {
        short id = new Short("1");
        Texto nombre = new Texto("Abab");
        Proveedor proveedor = new Proveedor();
        proveedor.setHabilitado(false);
        assertEquals(false, proveedor.estaHabilitado());
    }

    /**
     * Insertar proveedor con nombre nulo.
     */
    @Test(expected = NullPointerException.class)
    public void test_insertar_nombreNulo() {
        Texto nombre = null;
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.insertarEnBD();
    }

    /**
     * Actualiza proveedor con id 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_actualizar_id0() {
        short id = 0;
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.actualizarEnBD();
    }

    /**
     * Actualiza proveedor con nombre nulo.
     */
    @Test(expected = NullPointerException.class)
    public void test_actualizar_nombreNull() {
        short id = 1;
        Texto nombre = null;
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.actualizarEnBD();
    }

}
