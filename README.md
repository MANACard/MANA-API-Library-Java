
# Mana API Library

Kjo komponentë ju mundëson qasje më të lehtë në API-n e 
kartelës MANA, duke përdorur gjuhën programuese Java.


## Konfigurimi
Për të vendosur komunikim me API-n tonë, fillimisht ju duhet një API key dhe një Auth ID. Pastaj, diku gjatë startimit të aplikacionit, duhet ta konfiguroni MANA-n që të përdorë API key dhe Auth ID. Preferohet, që këto dyja t'i ruani në ndonjë file konfigurimi apo databazë, në mënyrë që të jenë të ndryshme për çdo pikë të shitjes, ku e instaloni aplikacionin tuaj.

```java
        Mana.getInstance().setApiKey("API_KEY_KETU");
        Mana.getInstance().setAuthId("AUTH_ID_KETU");
```

Nëse jeni në ambient testues, atëherë, konfiguroni edhe URL të API-t, si mëposhtë: 

`Mana.getInstance().setSandbox(true);
`

## Kartela e klientit
Për të marrë të dhënat e klientit, duhet të përdorni klasën `Card`, dhe ju duhet numri i barkodit të kartelës së klientit. Të dhënat e një kartele (klienti) mund të merren si mëposhtë:

```java
	Card card = Card.get("MANAxxxxx")
```

Kjo metodë gjeneron këto lloj exceptions, në raste të rrjedhave alternative: 

- AuthenticationFailedException - autentikimi ka dështuar, API Key ose Auth ID janë gabim
- CardNotFoundException - kartela me këtë numër nuk ekziston
- ManaException - gabim i përgjithshëm (probleme me komunikimin me serverin, etj.)

Shumica e metodave (getters-setters) të klasës `Card` janë vetë-shpjeguese, ndërsa të tjerat kanë kuptim si mëposhtë: 

- getMinimunRedeemablePoints - Tregon minimumin e pikëve që mund të shfrytëzohen, momentalisht **100**.
- getMonetaryValuePerPoint - Tregon se sa është vlera monetare e një pike, për momentin **0.01 cent**.

## Shitjet
### Regjistrimi i shitjeve

Për çdo shitje me kartelën MANA, duhet të përdorni klasën Sale. Mëposhtë, mund të gjeni një pjesë kodi, të komentuar në secilin hap.

```java
Sale sale = new Sale();

// Kjo fushë është opcionale. Me këtë garantohet uniciteti i shitjes në databazën tonë, në mënyrë që të mos regjistrohen shitje duplikate. Nëse nuk e plotësoni këtë fushë, libraria gjeneron një ID automatikisht në prapavijë.
sale.setId("XYZ");

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

Metoda `save()` gjenerohen exceptions si më poshtë:

- MorePointsRequiredException - Nëse numri i pikëve që kërkon të shfrytëzohet është më i vogël se vlera e lejuar, momentalisht **100**.
- SaleAlreadyCancelledException - Nuk aplikohet në rastin tuaj
- UnauthorizedCancellationException - Nuk aplikohet në rastin tuaj
- CardNotFoundException - Kartela nuk është gjetur
- ValidationException - Nuk keni përfshirë të gjitha fushat (që janë të kërkuara) si lista e produkteve, etj.
- SaleAlreadySubmittedException - Shitja me këtë ID tashmë është regjistruar (për të parandaluar shitjet duplikate)
- CardNotActiveException - Kartela nuk është aktive dhe rrjedhimisht nuk mund të regjistrohen shitjet për atë kartelë
- NotEnoughPointsException - Klienti kërkon të shfrytëzojë më shumë pikë se sa që ka në dispozicion
- AuthenticationFailedException - Autentikimi ka dështuar.



###Anulimi i shitjeve
Për të anuluar një shitje, fillimisht duhet ta ngarkoni atë nga API, duke përdorur metodën statike `get` të klasës Sale, si mëposhtë: 

```java
SaleResult sale = Sale.get(111111222222L) // 111111222222 është ID fiktive
```
Pastaj, në objektin që kthehet, mund ta thërrisni metodën `cancel`, si mëposhtë: 

```java
sale.cancel()
```

Metoda `cancel` gjeneron exceptions si mëposhtë: 
- SaleNotFoundException - Shitja nuk është gjetur
- AuthenticationFailedException - Autentikimi ka dështuar, OSE nuk keni të drejtë ta anuloni këtë shitje. Vetëm një API Key nga i gjithë partneri ka të drejtë të anulojë shitje.

