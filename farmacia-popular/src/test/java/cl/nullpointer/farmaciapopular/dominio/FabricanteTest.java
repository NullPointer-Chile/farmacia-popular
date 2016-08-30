package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitario para la clase Fabricante.
 *
 * @author Omar Paché
 */
public class FabricanteTest {

    private static final Logger LOG = Logger.getLogger(FabricanteTest.class);

    public FabricanteTest() {
    }

    /**
     * Crear fabricante con su respectivo nombre
     */
    @Test
    public void test_crearFabricante_OK() {
        LOG.info("Probando la creacion de un fabricante");

        Texto nombre = new Texto("Samsung");
        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);
        Assert.assertEquals(nombre, fabricante.getNombre());
    }

    /**
     * Probar el fallo al crear un Fabricante por nombre nulo.
     */
    @Test(expected = NullPointerException.class)
    public void crearFabricante_nombreNulo() {
        LOG.info("Fallando la creacion de un fabricante por nombre nulo");

        Texto nombre = null;
        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);
    }

    /**
     * Probar el fallo al crear un Fabricante por nombre demasiado corto.
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearFabricante_nombreDemasiadoCorto() {
        LOG.info("Fallando la creacion de un fabricante por nombre demasiado corto");

        Texto nombre = new Texto("a");
        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);
    }

    /**
     * Probar el fallo al crear un Fabricante por nombre demasiado largo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearFabricante_nombreDemasiadoLargo() {
        LOG.info("Fallando la creacion de un fabricante por nombre demasiado largo");

        Texto nombre = new Texto("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);
    }

}
