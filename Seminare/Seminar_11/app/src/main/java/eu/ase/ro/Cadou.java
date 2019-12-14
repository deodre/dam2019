package eu.ase.ro;

public class Cadou {

    private int id;
    private float pret;
    private boolean impachetat;
    private String denumire;
    private String destinatar;

    public Cadou(int id, float pret, boolean impachetat, String denumire, String destinatar) {
        this.id = id;
        this.pret = pret;
        this.impachetat = impachetat;
        this.denumire = denumire;
        this.destinatar = destinatar;
    }

    public Cadou() {
        this.id = 0;
        this.pret = 0;
        this.impachetat = false;
        this.denumire = "";
        this.destinatar = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDestinatar() {
        return destinatar;
    }

    public void setDestinatar(String destinatar) {
        this.destinatar = destinatar;
    }

    @Override
    public String toString() {
        return "Cadou{" +
                "id=" + id +
                "pret=" + pret +
                ", impachetat=" + impachetat +
                ", denumire='" + denumire + '\'' +
                ", destinatar='" + destinatar + '\'' +
                '}';
    }
}
