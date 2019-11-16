package eu.ase.ro;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Calatorie implements Parcelable {
    private String destinatie;
    private int nrZile;
    private Date dataPlecare;

    public Calatorie(String destinatie, int nrZile, Date dataPlecare) {
        this.destinatie = destinatie;
        this.nrZile = nrZile;
        this.dataPlecare = dataPlecare;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public int getNrZile() {
        return nrZile;
    }

    public void setNrZile(int nrZile) {
        this.nrZile = nrZile;
    }

    public Date getDataPlecare() {
        return dataPlecare;
    }

    public void setDataPlecare(Date dataPlecare) {
        this.dataPlecare = dataPlecare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Calatorie(Parcel in) {
        destinatie = in.readString();
        nrZile = in.readInt();
        dataPlecare = new Date(in.readLong());
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destinatie);
        dest.writeInt(nrZile);
        dest.writeLong(dataPlecare.getTime());
    }

    public static final Creator<Calatorie> CREATOR = new Creator<Calatorie>() {
        @Override
        public Calatorie createFromParcel(Parcel in) {
            return new Calatorie(in);
        }

        @Override
        public Calatorie[] newArray(int size) {
            return new Calatorie[size   ];
        }
    };

    @Override
    public String toString() {
        return "Calatorie{" +
                "destinatie='" + destinatie + '\'' +
                ", nrZile=" + nrZile +
                ", dataPlecare=" + dataPlecare +
                '}';
    }

}
