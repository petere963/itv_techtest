Supermarket Checkout Application
=================================
This service provides supermarket functionality as follows ;

- Set Item pricing, including bulk price overrides
- Item Scanning, which adds items to a basket, and maintains a running total
- Checkout, which provides the final total, and resets the basket.
- Pricing changes can be made at any poiiunt, and they are applied when the next basket scan starts (i.e. they do not affect any baskets already in progress)

The application is supplied as a Spring Boot Application, and can be built using maven - the maven pom.xml file is supplied. 
Some simple REST services are supplied to demonstrate the functions of the application as follows;

http://localhost:8080/pricing
Display the current pricing rules

http://localhost:8080/setPrice?sku=<sku>&unitPrice=<unitPrice>&specialPrice=<specialPrice>&specialPriceUnits=<specialPriceUnits>
Set a pricing Rule

http://localhost:8080/scanItem?sku=<sku>
Scan an Item

http://localhost:8080/status
Shows the current basket (also returned when item scanned)

http://localhost:8080/checkout
Check out process. The final total is displayed along with the basket. THe session is reset ready for the next customer.
 
Running from command line
-------------------------
java -jar target/itv.checkout-1.0-SNAPSHOT.jar

