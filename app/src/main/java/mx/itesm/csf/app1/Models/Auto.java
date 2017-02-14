package mx.itesm.csf.app1.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by rubcuadra on 2/9/17.
 */

public class Auto implements Serializable,Parcelable
{
    private String auto;
    private String marca;
    private String image;
    private Integer id;
    private String color;
    private Integer puertas;
    private Integer anio;

    public Auto(Parcel in)
    {
        auto = in.readString();
        marca = in.readString();
        image = in.readString();
    }

    public Auto(){}

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

    public static Bundle asBundle(Auto a)
    {
        Bundle b = new Bundle();
        b.putString("auto",a.getAuto());
        b.putString("marca",a.getMarca());
        b.putString("image",a.getImage());
        return b;
    }

    public static Auto fromBundle(Bundle b)
    {
        Auto a = new Auto();
        a.setAuto( b.getString("auto") );
        a.setMarca( b.getString("marca") );
        a.setImage( b.getString("image") );
        return a;
    }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(auto);
        dest.writeString(marca);
        dest.writeString(image);
    }

    public static final Parcelable.Creator<Auto> CREATOR = new Parcelable.Creator<Auto>()
    {
        @Override
        public Auto createFromParcel(Parcel source)
        {
            return new Auto(source);
        }
        @Override
        public Auto[] newArray(int size)
        {
            return new Auto[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public String toString()
    {
        return  this.id+"\n"+
                this.auto+"\n"+
                this.marca+"\n"+
                this.color+"\n"+
                this.image+"\n"+
                this.puertas+"\n"+
                this.anio+"\n";
    }
}
