package cards.mana;


import cards.mana.services.CardService;
import cards.mana.services.ICardService;
import cards.mana.services.ISaleService;
import cards.mana.services.SaleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;

public class Mana {
    private static Mana instance = new Mana();

    private String apiKey;
    private String authId;
    private String apiUrl = "https://app.mana.cards/api";
    private int apiVersion = 1;

    private ISaleService saleService;
    private ICardService cardService;

    private Mana() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static Mana getInstance() {
        return instance;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getApiUrl() {
        return apiUrl + "/v" + getApiVersion();
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setSandbox(boolean sandbox){
        if(sandbox){
            setApiUrl("https://sandbox.mana.cards/api");
        }else{
            setApiUrl("https://app.mana.cards/api");
        }
    }
    public ISaleService getSaleService() {

        if (saleService == null)
            setSaleService(new SaleService());

        return saleService;
    }

    public void setSaleService(ISaleService saleService) {
        this.saleService = saleService;
    }

    public ICardService getCardService() {
        if(cardService == null){
            setCardService(new CardService());
        }

        return cardService;
    }

    public void setCardService(ICardService cardService) {
        this.cardService = cardService;
    }
}
