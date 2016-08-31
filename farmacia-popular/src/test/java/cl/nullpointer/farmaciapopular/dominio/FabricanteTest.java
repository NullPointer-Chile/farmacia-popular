package cl.nullpointer.farmaciapopular.dominio;

import base.tipoDato.Texto;
import base.validacion.ResultadoMetodo;
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
    public void crearFabricante_nombreNulo() {
        LOG.info("Fallando la creacion de un fabricante por nombre nulo");

        Texto nombre = null;
        Fabricante fabricante = new Fabricante();
        ResultadoMetodo resultado = fabricante.setNombre(nombre);
        Assert.assertTrue(resultado.isError());
    }

    /**
     * Probar el fallo al crear un Fabricante por nombre demasiado corto.
     */
    public void crearFabricante_nombreDemasiadoCorto() {
        LOG.info("Fallando la creacion de un fabricante por nombre demasiado corto");

        Texto nombre = new Texto("a");
        Fabricante fabricante = new Fabricante();

        ResultadoMetodo resultado = fabricante.setNombre(nombre);
        Assert.assertTrue(resultado.isError());
    }

    /**
     * Probar el fallo al crear un Fabricante por nombre demasiado largo.
     */
    public void crearFabricante_nombreDemasiadoLargo() {
        LOG.info("Fallando la creacion de un fabricante por nombre demasiado largo");

        Texto nombre = new Texto("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Fabricante fabricante = new Fabricante();

        ResultadoMetodo resultado = fabricante.setNombre(nombre);
        Assert.assertTrue(resultado.isError());
    }

}
