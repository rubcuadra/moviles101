package mx.itesm.csf.app1.Models;

import android.os.Bundle;
import android.os.Parcel;
import java.io.Serializable;

/**
 * Created by rubcuadra on 3/9/17.
 */

public class Card implements Serializable
{
    private String auto;
    private String precio;
    private String image;
    private Integer id;
    private Integer clave_marca;

    public Card(Parcel in)
    {
        auto = in.readString();
        precio = in.readString();
        image = in.readString();
    }

    public Card(){}

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String marca) {
        this.precio = marca;
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

    public static Bundle asBundle(Card a)
    {
        Bundle b = new Bundle();
        b.putString("auto",a.getAuto());
        b.putString("precio",a.getPrecio());
        b.putString("image",a.getImage());
        b.putInt("Clave_auto",a.getId());
        b.putInt("clave_marca",a.getClave_marca());
        return b;
    }

    public static Card fromBundle(Bundle b)
    {
        Card a = new Card();
        a.setAuto( b.getString("auto") );
        a.setPrecio( b.getString("precio") );
        a.setImage( b.getString("image") );
        a.setClave_marca( b.getInt("clave_marca") );
        a.setId( b.getInt("Clave_auto") );
        return a;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return  this.id+"\n"+
                this.auto+"\n"+
                this.precio +"\n"+
                this.image+"\n";
    }

    public Integer getClave_marca() {
        return clave_marca;
    }

    public void setClave_marca(Integer clave_marca) {
        this.clave_marca = clave_marca;
    }
}

