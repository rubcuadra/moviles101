package mx.itesm.csf.app1.Models;

import java.io.Serializable;

/**
 * Created by rubcuadra on 2/9/17.
 */

public class Auto implements Serializable
{
    private String auto;
    private String marca;
    private String image;


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
