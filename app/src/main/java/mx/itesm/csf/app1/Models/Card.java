package mx.itesm.csf.app1.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by rubcuadra on 3/9/17.
 */

public class Card implements Serializable,Parcelable
{
    private String auto;
    private String marca;
    private String image;
    private Integer id;
    private String color;
    private Integer puertas;
    private Integer anio;

    public Card(Parcel in)
    {
        auto = in.readString();
        marca = in.readString();
        image = in.readString();
        color = in.readString();
        puertas = in.readInt();
        anio = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(auto);
        dest.writeString(marca);
        dest.writeString(image);
        dest.writeString(color);
        dest.writeInt(puertas);
        dest.writeInt(anio);

    }

    public Card(){}

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

    public static Bundle asBundle(Card a)
    {
        Bundle b = new Bundle();
        b.putString("auto",a.getAuto());
        b.putString("marca",a.getMarca());
        b.putString("image",a.getImage());
        b.putString("color",a.getColor());
        b.putInt("anio",a.getAnio());
        b.putInt("puertas",a.getPuertas());
        return b;
    }

    public static Card fromBundle(Bundle b)
    {
        Card a = new Card();
        a.setAuto( b.getString("auto") );
        a.setMarca( b.getString("marca") );
        a.setImage( b.getString("image") );
        a.setColor( b.getString("color"));
        a.setPuertas(b.getInt("puertas"));
        a.setAnio(b.getInt("anio"));
        return a;
    }

    @Override
    public int describeContents() {return 0;}

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>()
    {
        @Override
        public Card createFromParcel(Parcel source)
        {
            return new Card(source);
        }
        @Override
        public Card[] newArray(int size)
        {
            return new Card[size];
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

