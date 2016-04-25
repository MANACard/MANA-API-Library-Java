package cards.mana.helpers;

import cards.mana.Mana;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AuthenticationHelper {
    public static HttpRequest injectHeaders(HttpRequest request){
        return request.basicAuth(Mana.getInstance().getAuthId(), Mana.getInstance().getApiKey());
    }
}
