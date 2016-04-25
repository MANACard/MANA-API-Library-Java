package cards.mana.domain;

import cards.mana.Mana;
import cards.mana.exceptions.AuthenticationFailedException;
import cards.mana.exceptions.CardNotFoundException;
import cards.mana.exceptions.ManaException;
import cards.mana.services.CardService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    @JsonProperty("client_first_name")
    private String clientFirstName;

    @JsonProperty("client_last_name")
    private String clientLastName;

    @JsonProperty
    private String barcode;

    @JsonProperty("available_points")
    private int points;

    @JsonProperty("minimum_redeemable_points")
    private int minimumRedeemablePoints;

    @JsonProperty("monetary_value_per_point")
    private double monetaryValuePerPoint;

    @JsonProperty("active")
    private boolean active;


    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public int getMinimumRedeemablePoints() {
        return minimumRedeemablePoints;
    }

    public void setMinimumRedeemablePoints(int minimumRedeemablePoints) {
        this.minimumRedeemablePoints = minimumRedeemablePoints;
    }

    public double getMonetaryValuePerPoint() {
        return monetaryValuePerPoint;
    }

    public void setMonetaryValuePerPoint(double monetaryValuePerPoint) {
        this.monetaryValuePerPoint = monetaryValuePerPoint;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static Card get(String barcode) throws AuthenticationFailedException, ManaException, CardNotFoundException {
        return Mana.getInstance().getCardService().get(barcode);
    }
}
