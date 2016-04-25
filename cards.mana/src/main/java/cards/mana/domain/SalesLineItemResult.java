package cards.mana.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesLineItemResult {

    @JsonProperty("product_title")
    private String productTitle;

    @JsonProperty("product_barcode")
    private String productBarcode;

    @JsonProperty("actual_rewarded_points")
    private double rewardedPoints;

    @JsonProperty("rewarded_value")
    private double rewardedValue;

    @JsonProperty("mana_commission")
    private double manaCommission;

    @JsonProperty("item_identifier")
    private double itemIdentifier;

    @JsonProperty
    private int vat;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public double getRewardedPoints() {
        return rewardedPoints;
    }

    public void setRewardedPoints(double rewardedPoints) {
        this.rewardedPoints = rewardedPoints;
    }

    public double getRewardedValue() {
        return rewardedValue;
    }

    public void setRewardedValue(double rewardedValue) {
        this.rewardedValue = rewardedValue;
    }

    public double getManaCommission() {
        return manaCommission;
    }

    public void setManaCommission(double manaCommission) {
        this.manaCommission = manaCommission;
    }

    public double getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(double itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
}
