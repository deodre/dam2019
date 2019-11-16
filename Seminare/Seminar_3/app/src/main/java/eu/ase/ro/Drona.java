package eu.ase.ro;

import android.os.Parcel;
import android.os.Parcelable;

public class Drona implements Parcelable {
    private double greutate;
    private String producator;
    private int nrElice;
    private boolean areCamera;
    private String culoare;

    protected Drona(Parcel in) {
        greutate = in.readDouble();
        producator = in.readString();
        nrElice = in.readInt();
        areCamera = in.readByte() != 0;
        culoare = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(greutate);
        dest.writeString(producator);
        dest.writeInt(nrElice);
        dest.writeByte((byte) (areCamera ? 1 : 0));
        dest.writeString(culoare);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Drona> CREATOR = new Creator<Drona>() {
        @Override
        public Drona createFromParcel(Parcel in) {
            return new Drona(in);
        }

        @Override
        public Drona[] newArray(int size) {
            return new Drona[size];
        }
    };

    public double getGreutate() {
        return greutate;
    }

    public void setGreutate(double greutate) {
        this.greutate = greutate;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public int getNrElice() {
        return nrElice;
    }

    public void setNrElice(int nrElice) {
        this.nrElice = nrElice;
    }

    public boolean isAreCamera() {
        return areCamera;
    }

    public void setAreCamera(boolean areCamera) {
        this.areCamera = areCamera;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        culoare = culoare;
    }

    public Drona(double greutate, String producator, int nrElice, boolean areCamera, String culoare) {
        this.greutate = greutate;
        this.producator = producator;
        this.nrElice = nrElice;
        this.areCamera = areCamera;
        this.culoare = culoare;
    }

    @Override
    public String toString() {
        return "Drona{" +
                "greutate=" + greutate +
                ", producator='" + producator + '\'' +
                ", nrElice=" + nrElice +
                ", areCamera=" + areCamera +
                ", culoare='" + culoare + '\'' +
                '}';
    }
}
