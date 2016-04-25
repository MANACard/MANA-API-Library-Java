package cards.mana.services;

import cards.mana.domain.Sale;
import cards.mana.domain.SaleResult;
import cards.mana.exceptions.*;

/**
 * Created by tomor on 25/04/16.
 */
public interface ISaleService {

    SaleResult submit(Sale s) throws ManaException, NotEnoughPointsException, MorePointsRequiredException, CardNotActiveException, SaleAlreadySubmittedException, UnauthorizedCancellationException, SaleAlreadyCancelledException, ValidationException, CardNotFoundException, AuthenticationFailedException;

    SaleResult get(long id) throws ManaException, SaleNotFoundException, AuthenticationFailedException;

    void cancel(long id) throws SaleNotFoundException, AuthenticationFailedException, ManaException;
}
