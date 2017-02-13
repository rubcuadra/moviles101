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

    public Auto(Parcel in)
    {
        auto = in.readString();
        marca = in.readString();
        image = in.readString();
    }
    public Auto(String auto,String marca,String image)
    {
        this.auto = auto;
        this.marca = marca;
        this.image = image;
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
}
