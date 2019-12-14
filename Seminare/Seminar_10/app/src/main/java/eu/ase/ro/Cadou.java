package eu.ase.ro;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cadouri")
public class Cadou {

    @PrimaryKey
    @ColumnInfo(name = "ID")
    private int id;
    @NonNull
    private String denumire;
    private float pret;
    private boolean impachetat;

    public Cadou(int id, @NonNull String denumire, float pret, boolean impachetat) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
        this.impachetat = impachetat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(@NonNull String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public boolean isImpachetat() {
        return impachetat;
    }

    public void setImpachetat(boolean impachetat) {
        this.impachetat = impachetat;
    }

    @Override
    public String toString() {
        return "Cadou{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", impachetat=" + impachetat +
                '}';
    }
}
