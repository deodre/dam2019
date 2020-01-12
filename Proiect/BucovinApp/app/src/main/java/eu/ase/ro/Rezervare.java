package dam.ase.ro;

import java.util.Date;

public class Rezervare {

    private String nume;
    private String prenume;
    private Date checkin;
    private Date checkout;
    private boolean familieCopil;

    public Rezervare(String nume, String prenume, Date checkin, Date checkout, boolean familieCopil) {
        this.nume = nume;
        this.prenume = prenume;
        this.checkin = checkin;
        this.checkout = checkout;
        this.familieCopil = familieCopil;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public boolean isFamilieCopil() {
        return familieCopil;
    }

    public void setFamilieCopil(boolean familieCopil) {
        this.familieCopil = familieCopil;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", familieCopil=" + familieCopil +
                '}';
    }
}
