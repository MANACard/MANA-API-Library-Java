import cards.mana.Mana;
import cards.mana.domain.Card;
import cards.mana.domain.Sale;
import cards.mana.domain.SaleResult;
import cards.mana.domain.SalesLineItem;
import cards.mana.exceptions.*;
import cards.mana.services.SaleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomor on 24/04/16.
 */
public class Test {

    public static void main(String[] args){
        Mana.getInstance().setApiUrl("http://localhost:3000/api/v1");
        Mana.getInstance().setApiKey("6");
        Mana.getInstance().setAuthId("3");


        try {
            Card card = Card.get("");

            System.out.println (card.getClientFirstName());
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (ManaException e) {
            e.printStackTrace();
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        }
//        Sale s = new Sale();
//        s.setCardBarcode("MANA7126085");
//
//        List<SalesLineItem> items = new ArrayList<SalesLineItem>();
//
//        SalesLineItem item = new SalesLineItem();
//        item.setBarcode("123");
//        item.setPrice(230.5);
//        item.setQuantity(1);
//
//
//        items.add(item);
//        s.setItems(items);
//
//        try {
//            SaleResult sale = SaleService.get(348148675468L);
//
//        } catch (CardNotFoundException e) {
//            e.printStackTrace();
//        } catch (ManaException e) {
//            e.printStackTrace();
//        }
//        try {
//            s.save();
//        } catch (ManaException e) {
//            e.printStackTrace();
//        } catch (MorePointsRequiredException e) {
//            e.printStackTrace();
//        } catch (SaleAlreadyCancelledException e) {
//            e.printStackTrace();
//        } catch (CardNotFoundException e) {
//            e.printStackTrace();
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        } catch (SaleAlreadySubmittedException e) {
//            e.printStackTrace();
//        } catch (UnauthorizedCancellationException e) {
//            e.printStackTrace();
//        } catch (CardNotActiveException e) {
//            e.printStackTrace();
//        } catch (NotEnoughPointsException e) {
//            e.printStackTrace();
//        }
    }
}
