package eu.ase.ro;

import android.widget.ArrayAdapter;

public class Rate {

    private String currency;
    private float value;
    private int multiplier;

    public Rate(String currency, float value, int multiplier) {
        this.currency = currency;
        this.value = value;
        this.multiplier = multiplier;
    }

    public Rate(String currency, float value) {
        this.currency = currency;
        this.value = value;
        this.multiplier = 1;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                ", multiplier=" + multiplier +
                '}';
    }
}
