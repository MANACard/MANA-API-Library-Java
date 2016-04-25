
# Mana API Library

Kjo komponentë ju mundëson qasje më të lehtë në API-n e 
kartelës MANA, duke pwrdorur gjuhwn programuese Java.


## Konfigurimi
Pwr tw vendosur komunikim me API-n tonw, fillimisht ju duhet njw API key dhe njw Auth ID. Pastaj, diku gjatw startimit tw aplikacionit, duhet ta konfiguroni MANA-n qw tw pwrdorw API key dhe Auth ID. Preferohet, qw kwto dyja t'i ruani nw ndonjw file konfigurimi apo databazw, nw mwnyrw qw tw jenw tw ndryshme pwr #cdo pikw tw shitjes, ku e instaloni aplikacionin tuaj.

```
        Mana.getInstance().setApiKey("API_KEY_KETU");
        Mana.getInstance().setAuthId("AUTH_ID_KETU");
```

Nwse jeni nw ambient testues, atwherw, konfiguroni edhe URL tw API-t, si mwposhtw: 

`Mana.getInstance().setSandbox(true);
`

## Kartela e klientit
Pwr tw marrw tw dhwnat e klientit, duhet tw pwrdorni klaswn `Card`, dhe ju duhet numri i barkodit tw kartelws sw klientit. Tw dhwnat e njw kartele (klienti) mund tw merren si mwposhtw:

```
	Card card = Card.get("MANAxxxxx")
```

Kjo metodw gjeneron kwto lloj exceptions, nw raste tw rrjedhave alternative: 

- AuthenticationFailedException - autentikimi ka dwshtuar, API Key ose Auth ID janw gabim
- CardNotFoundException - kartela me kwtw numwr nuk ekziston
- ManaException - gabim i pwrgjithshwm (probleme me komunikimin me serverin, etj.)

Shumica e metodave (getters-setters) tw klasws `Card` janw vetw-shpjeguese, ndwrsa tw tjerat kanw kuptim si mwposhtw: 

- getMinimunRedeemablePoints - Tregon minimumin e pikwve qw mund tw shfrytwzohen, momentalisht **100**.
- getMonetaryValuePerPoint - Tregon se sa wshtw vlera monetare e njw pike, pwr momentin **0.01 cent**.

## Shitjet
Pwr #cdo shitje me kartelwn MANA, duhet tw pwrdorni klaswn Sale. Mwposhtw, mund tw gjeni njw pjesw kodi, tw komentuar nw secilin hap.

```java
        Sale sale = new Sale();
        
        sale.setCardBarcode("MANAxxxxxxx"); // Ketu shkruhet barkodi i karteles se klientit

        // Inicializohet lista me artikujt e shitur
        List<SalesLineItem> items = new ArrayList<SalesLineItem>();

        // Per cdo artikull te shitur, krijohet nje instance e klases SalesLineItem
        SalesLineItem item = new SalesLineItem();

        // Barkodi apo cfaredo kodi qe ne menyre unike e identifikon produktin ne sistemin tuaj.
        item.setBarcode("XX");

        // Cmimi i shitjes PER SASI te shitur, pas zbritjeve (nese ka) te aplikuara
        item.setPrice(15.3);

        // Sasia e shitur
        item.setQuantity(1);

        items.add(item);

        sale.setItems(items);

        /**
         * SHFRYTEZIMI I PIKEVE: Pjesa e meposhtme duhet te shtohet vetem nese klienti deshiron te shfrytezoje pike
         */

        sale.getRedeem().setPoints(150); // Numri i pikeve qe deshiron ta shfrytezoje klienti
        /**
         * SHFRYTEZIMI I PIKEVE
         */

        sale.save();
```



