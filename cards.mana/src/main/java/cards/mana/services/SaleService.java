package cards.mana.services;

import cards.mana.Mana;
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

public class SaleService implements ISaleService {
    public SaleResult submit(Sale s) throws ManaException, NotEnoughPointsException, MorePointsRequiredException, CardNotActiveException, SaleAlreadySubmittedException, UnauthorizedCancellationException, SaleAlreadyCancelledException, ValidationException, CardNotFoundException, AuthenticationFailedException {
        String url = Mana.getInstance().getApiUrl() + "/sales";

        HttpRequestWithBody request = Unirest.post(url);

        AuthenticationHelper.injectHeaders(request);
        request.header("Accept", "application/json");
        request.header("Content-Type", "application/json");
        request.header("Mana-Transaction-Id", s.getId());

        SaleResult saleResult = null;
        ObjectMapper mapper = new ObjectMapper();


        try {
            HttpResponse<String> response = request.body(s).asString();


            if (response.getStatus() == 422){

                ErrorResult errorResult = mapper.readValue(response.getBody(), ErrorResult.class);

                int code = errorResult.getErrors().get(0).getCode();

                if (code == 440){
                    throw new NotEnoughPointsException();
                }else if (code == 441){
                    throw new MorePointsRequiredException();
                }else if (code == 442){
                    throw new CardNotActiveException();
                }else if (code == 443){
                    throw new SaleAlreadySubmittedException();
                }else if (code == 445){
                    throw new UnauthorizedCancellationException();
                }else if (code == 446){
                    throw new SaleAlreadyCancelledException();
                }else{
                    throw new ValidationException();
                }
            }else if (response.getStatus() == 404){
                throw new CardNotFoundException();
            }else if (response.getStatus() == 401){
                throw new AuthenticationFailedException();
            }else if (response.getStatus() == 201){
                saleResult = mapper.readValue(response.getRawBody(), SaleResult.class);
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


        return saleResult;
    }

    public SaleResult get(long id) throws ManaException, SaleNotFoundException, AuthenticationFailedException {
        String url = Mana.getInstance().getApiUrl() + "/sales/" + id;


        GetRequest request = Unirest.get(url);

        AuthenticationHelper.injectHeaders(request);
        request.header("Accept", "application/json");
        request.header("Content-Type", "application/json");

        SaleResult saleResult = null;
        ObjectMapper mapper = new ObjectMapper();


        try {
            HttpResponse<String> response = request.asString();


            if (response.getStatus() == 404){
                throw new SaleNotFoundException();
            }else if (response.getStatus() == 401) {
                throw new AuthenticationFailedException();
            }else if (response.getStatus() == 200){
                saleResult = mapper.readValue(response.getBody(), SaleResult.class);
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


        return saleResult;
    }
    public void cancel(long id) throws SaleNotFoundException, AuthenticationFailedException, ManaException {
        String url = Mana.getInstance().getApiUrl() + "/sales/" + id + "/cancel";

        HttpRequestWithBody request = Unirest.post(url);

        AuthenticationHelper.injectHeaders(request);
        request.header("Accept", "application/json");
        request.header("Content-Type", "application/json");

        SaleResult saleResult = null;
        ObjectMapper mapper = new ObjectMapper();


        try {
            HttpResponse<String> response = request.asString();


            if (response.getStatus() == 404){
                throw new SaleNotFoundException();
            }else if (response.getStatus() == 401){
                throw new AuthenticationFailedException();
            }else{
                throw new ManaException("Communication error, status code: " + response.getStatus());
            }
        } catch (UnirestException e) {
            ManaException ex = new ManaException();
            ex.setStackTrace(e.getStackTrace());

            throw ex;
        }
    }
}
