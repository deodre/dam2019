package dam.ase.ro;

import android.os.Parcel;
import android.os.Parcelable;

public class ObiectivTuristic implements Parcelable {

    private String denumire;
    private String tip;
    private int anul_constructiei;

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

    public ObiectivTuristic(String denumire, String tip, int anul_constructiei) {
        this.denumire = denumire;
        this.tip = tip;
        this.anul_constructiei = anul_constructiei;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected ObiectivTuristic(Parcel in) {
        denumire = in.readString();
        tip = in.readString();
        anul_constructiei = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeString(tip);
        dest.writeInt(anul_constructiei);
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
                '}';
    }
}
