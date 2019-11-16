package com.example.seminar_2;

public class Drona {
    private double greutate;
    private int nrElice;
    private String producator;
    private boolean areCamera;
    private String culoare;

    public double getGreutate() {
        return greutate;
    }

    public void setGreutate(double greutate) {
        this.greutate = greutate;
    }

    public int getNrElice() {
        return nrElice;
    }

    public void setNrElice(int nrElice) {
        this.nrElice = nrElice;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
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
        this.culoare = culoare;
    }

    public Drona(double greutate, int nrElice, String producator, boolean areCamera, String culoare) {
        this.greutate = greutate;
        this.nrElice = nrElice;
        this.producator = producator;
        this.areCamera = areCamera;
        this.culoare = culoare;
    }

    public Drona() {
        this.greutate = 0;
        this.nrElice = 0;
        this.producator = "";
        this.areCamera = true;
        this.culoare = "Alb";
    }

    @Override
    public String toString() {
        return "com.example.seminar_2.Drona{" +
                "greutate=" + greutate +
                ", nrElice=" + nrElice +
                ", producator='" + producator + '\'' +
                ", areCamera=" + areCamera +
                ", culoare='" + culoare + '\'' +
                '}';
    }
}