package eu.ase.ro;

import android.os.Parcel;
import android.os.Parcelable;

public class Cazare implements Parcelable {
    private String denumire;
    private String tip;
    private int nrLocuri;
    private String adresa;

    public Cazare(String denumire, String tip, int nrLocuri, String adresa) {
        this.denumire = denumire;
        this.tip = tip;
        this.nrLocuri = nrLocuri;
        this.adresa = adresa;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Cazare(Parcel in) {
        denumire = in.readString();
        tip = in.readString();
        nrLocuri = in.readInt();
        adresa = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeString(tip);
        dest.writeInt(nrLocuri);
        dest.writeString(adresa);
    }

    public static final Creator<Cazare> CREATOR = new Creator<Cazare>() {
        @Override
        public Cazare createFromParcel(Parcel in) {
            return new Cazare(in);
        }

        @Override
        public Cazare[] newArray(int size) {
            return new Cazare[size];
        }
    };

    @Override
    public String toString() {
        return "Cazare{" +
                "denumire='" + denumire + '\'' +
                ", tip='" + tip + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
