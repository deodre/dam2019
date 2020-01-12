package eu.ase.ro;

import java.util.Date;

public class Rezervare {

    private String nume;
    private String prenume;
    private String checkin;
    private String checkout;
    private boolean familieCopil;

    public Rezervare(String nume, String prenume, String checkin, String checkout, boolean familieCopil) {
        this.nume = nume;
        this.prenume = prenume;
        this.checkin = checkin;
        this.checkout = checkout;
        this.familieCopil = familieCopil;
    }

    public Rezervare() {
        this.nume = null;
        this.prenume = null;
        this.checkin = null;
        this.checkout = null;
        this.familieCopil = false;
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

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
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
