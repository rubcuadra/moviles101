package mx.itesm.csf.app1.Models;

/**
 * Created by rubcuadra on 4/3/17.
 */

public class Marca
{
    private int id;
    private String name;

    public Marca(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
