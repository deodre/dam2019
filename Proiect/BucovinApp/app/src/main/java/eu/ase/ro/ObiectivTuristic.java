package eu.ase.ro;

import android.os.Parcel;
import android.os.Parcelable;

public class ObiectivTuristic implements Parcelable {

    private String denumire;
    private String tip;
    private int anul_constructiei;
    private double coordonateV;
    private double coordonateV1;

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

    public int getAnul_constructiei() {
        return anul_constructiei;
    }

    public void setAnul_constructiei(int anul_constructiei) {
        this.anul_constructiei = anul_constructiei;
    }

    public double getCoordonateV() {
        return coordonateV;
    }

    public void setCoordonateV(double coordonateV) {
        this.coordonateV = coordonateV;
    }

    public double getCoordonateV1() {
        return coordonateV1;
    }

    public void setCoordonateV1(double coordonateV1) {
        this.coordonateV1 = coordonateV1;
    }

    public ObiectivTuristic(String denumire, String tip, int anul_constructiei, double coordonateV, double coordonateV1) {
        this.denumire = denumire;
        this.tip = tip;
        this.anul_constructiei = anul_constructiei;
        this.coordonateV = coordonateV;
        this.coordonateV1 = coordonateV1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected ObiectivTuristic(Parcel in) {
        denumire = in.readString();
        tip = in.readString();
        anul_constructiei = in.readInt();
        coordonateV = in.readDouble();
        coordonateV1 = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeString(tip);
        dest.writeInt(anul_constructiei);
        dest.writeDouble(coordonateV);
        dest.writeDouble(coordonateV1);
    }

    public static final Creator<ObiectivTuristic> CREATOR = new Creator<ObiectivTuristic>() {
        @Override
        public ObiectivTuristic createFromParcel(Parcel in) {
            return new ObiectivTuristic(in);
        }

        @Override
        public ObiectivTuristic[] newArray(int size) {
            return new ObiectivTuristic[size];
        }
    };

    @Override
    public String toString() {
        return "ObiectivTuristic{" +
                "denumire='" + denumire + '\'' +
                ", tip='" + tip + '\'' +
                ", anul_constructiei=" + anul_constructiei +
                ", coordonateV=" + coordonateV +
                ", coordonateV1=" + coordonateV1 +
                '}';
    }
}
