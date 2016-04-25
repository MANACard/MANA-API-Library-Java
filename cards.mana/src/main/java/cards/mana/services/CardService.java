package cards.mana.services;

import cards.mana.Mana;
import cards.mana.domain.Card;
import cards.mana.domain.ErrorResult;
import cards.mana.domain.Sale;
import cards.mana.domain.SaleResult;
import cards.mana.exceptions.*;
import cards.mana.helpers.AuthenticationHelper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.IOException;

public class CardService implements ICardService {

    public Card get(String barcode) throws ManaException, AuthenticationFailedException, CardNotFoundException {
        String url = Mana.getInstance().getApiUrl() + "/cards/" + barcode;


        GetRequest request = Unirest.get(url);

        AuthenticationHelper.injectHeaders(request);
        request.header("Accept", "application/json");
        request.header("Content-Type", "application/json");

        Card card = null;
        ObjectMapper mapper = new ObjectMapper();


        try {
            HttpResponse<String> response = request.asString();


            if (response.getStatus() == 404){
                throw new CardNotFoundException();
            }else if (response.getStatus() == 401) {
                throw new AuthenticationFailedException();
            }else if (response.getStatus() == 200){
                card = mapper.readValue(response.getBody(), Card.class);
            }else{
                throw new ManaException("Communication error, status code: " + response.getStatus());
            }
        } catch (UnirestException e) {
            ManaException ex = new ManaException();
            ex.setStackTrace(e.getStackTrace());

            throw ex;
        } catch (JsonParseException e) {
            throw new ManaException("JSON Response could not be parsed: " + e.getMessage());
        } catch (JsonMappingException e) {
            throw new ManaException("JSON Response could not be mapped: " + e.getMessage());
        } catch (IOException e) {
            throw new ManaException("General I/O Exception: " + e.getMessage());
        }


        return card;
    }
}
