package cards.mana.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RedeemAction {

    @JsonProperty
    private int points;

    @JsonProperty
    private double value;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
