package cards.mana.domain;

import cards.mana.Mana;
import cards.mana.exceptions.AuthenticationFailedException;
import cards.mana.exceptions.ManaException;
import cards.mana.exceptions.SaleNotFoundException;
import cards.mana.services.SaleService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleResult {

    @JsonProperty
    private long id;

    @JsonProperty("available_points")
    private double availablePoints;

    @JsonProperty
    private double total;

    @JsonProperty("actual_rewarded_points")
    private double rewardedPoints;


    @JsonProperty("rewarded_value")
    private double rewardedValue;

    @JsonProperty("redeemed_points")
    private double redeemedPoints;

    @JsonProperty("redeemed_value")
    private double redeemedValue;

    @JsonProperty("discount_value")
    private double discountValue;

    @JsonProperty("sales_line_items")
    private List<SalesLineItemResult> salesLineItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(double availablePoints) {
        this.availablePoints = availablePoints;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public double getRedeemedPoints() {
        return redeemedPoints;
    }

    public void setRedeemedPoints(double redeemedPoints) {
        this.redeemedPoints = redeemedPoints;
    }

    public double getRedeemedValue() {
        return redeemedValue;
    }

    public void setRedeemedValue(double redeemedValue) {
        this.redeemedValue = redeemedValue;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public List<SalesLineItemResult> getSalesLineItems() {
        return salesLineItems;
    }

    public void setSalesLineItems(List<SalesLineItemResult> salesLineItems) {
        this.salesLineItems = salesLineItems;
    }

    public void cancel() throws SaleNotFoundException, ManaException, AuthenticationFailedException {
        Mana.getInstance().getSaleService().cancel(this.getId());
    }
}
