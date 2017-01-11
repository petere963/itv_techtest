Supermarket Checkout Application
=================================
This service provides supermarket functionality as follows ;

- Set Item pricing, including bulk price overrides
- Item Scanning, which adds items to a basket, and maintains a running total
- Checkout, which provides the final total, and resets the basket.
- Pricing changes can be made at any poiiunt, and they are applied when the next basket scan starts (i.e. they do not affect any baskets already in progress)

The application is supplied as a Spring Boot Application, and can be built using maven - the maven pom.xml file is supplied. 
Some simple REST services are supplied to demonstrate the functions of the application as follows;

- Display the current pricing rules
http://localhost:8080/pricing

- Set pricing Rules
—- Set a price without overrides
http://localhost:8080/setPrice?sku=A&unitPrice=20

—- Set a price with overrides
http://localhost:8080/setPrice?sku=A&unitPrice=20&specialPrice=35&specialPriceUnits=2

—- Scan an Item
http://localhost:8080/scanItem?sku=A

—- Display the current basket (also returned when item scanned)
http://localhost:8080/status

—- Check out process. The final total is displayed along with the basket. The session is reset ready for the next customer.
http://localhost:8080/checkout

 
Running from command line
-------------------------
java -jar target/itv.checkout-1.0-SNAPSHOT.jar

