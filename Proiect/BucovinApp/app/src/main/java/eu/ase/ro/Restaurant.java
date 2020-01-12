package dam.ase.ro;

public class Restaurant {
    private String denumire;
    private String program;
    private String adresa;

    public Restaurant(String denumire, String program, String adresa) {
        this.denumire = denumire;
        this.program = program;
        this.adresa = adresa;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
