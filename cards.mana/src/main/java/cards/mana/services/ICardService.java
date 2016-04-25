package cards.mana.services;

import cards.mana.domain.Card;
import cards.mana.exceptions.AuthenticationFailedException;
import cards.mana.exceptions.CardNotFoundException;
import cards.mana.exceptions.ManaException;

/**
 * Created by tomor on 25/04/16.
 */
public interface ICardService {
    Card get(String barcode) throws ManaException, AuthenticationFailedException, CardNotFoundException;
}
