package cl.nullpointer.farmaciapopular.dominio;

/**
 * Clase que modela al fabricante de un producto.
 *
 * @author Omar Paché
 */
public class Fabricante {

    private int id;
    private String nombre;

    public Fabricante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
