package cards.mana.domain;
import cards.mana.Mana;
import cards.mana.exceptions.*;
import cards.mana.services.SaleService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sale {

    public Sale(){
        redeem = new RedeemAction();
    }

    @JsonIgnore
    private String id;

    @JsonProperty("card_barcode")
    private String cardBarcode;

    @JsonIgnore
    private double total;

    @JsonProperty
    private List<SalesLineItem> items;

    @JsonProperty
    private RedeemAction redeem;

    public String getCardBarcode() {
        return cardBarcode;
    }

    public void setCardBarcode(String cardBarcode) {
        this.cardBarcode = cardBarcode;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<SalesLineItem> getItems() {
        return items;
    }

    public void setItems(List<SalesLineItem> items) {
        this.items = items;
    }

    public RedeemAction getRedeem() {
        return redeem;
    }

    public String getId() {
        if(id == null){
            this.setId(java.util.UUID.randomUUID().toString());
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SaleResult save() throws ManaException, MorePointsRequiredException, SaleAlreadyCancelledException, CardNotFoundException, ValidationException, SaleAlreadySubmittedException, UnauthorizedCancellationException, CardNotActiveException, NotEnoughPointsException, AuthenticationFailedException {
        return Mana.getInstance().getSaleService().submit(this);
    }

    public static SaleResult get(long id) throws SaleNotFoundException, ManaException, AuthenticationFailedException {
        return Mana.getInstance().getSaleService().get(id);
    }
}
