# my-Retail 
This is a RESTful service that provides API to get the product details from an external API and get the price details from another pricing api and provide integrated results to the users. <br />
It also provides API to change the price of the product. <br />

*Technologies used:* 
	Spring boot (v2.1.3.RELEASE)
	JUnit 4.12 
	swagger 2.8.0
	Apache Maven 3.6.0
	
*Prerequisites:* 
	Java 8 
	Postman 
	mongodb
	
*Execution Procedure:*
   	
	Case 1 : Local Mongo DB Setup with no authentication needed. 
		a. Use host :localhost and port : 27017.
		b. Create new Database : myRetail (use myRetail)		
    
	Case 2 : Mango DB needs authentication 
		a. Go to Resource directory --> application.properties 
		b. Uncomment spring.data.mongodb.username & spring.data.mongodb.password
		c. Update host and port information if needed.
		d. Go to project directory : testProject (Location of pom file) in command prompt
		    run : mvn package or mvn install
		
    Next Step : Go to project directory : testProject (Location of pom file) in command promp
		Run following command : java -jar target/myproject-0.0.1-SNAPSHOT.jar
					
    Test Application :
    	To Get the list of product with prices :
     	   	Enter the url : .  http://localhost:8080/product/13860428  in a web browser.  
	To update price 
		a.select PUT method and enter url http://localhost:8080/product/13860428  in address bar of postman
		b.The request to the PUT method must be sent in JSON format in message body. 
		  You can change "current_price" in below JSON
		  {"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"value":10000.0,"currency_code":"USD"}}
			
    Alternatively use Swagger
	Enter url http://localhost:8080/swagger-ui.html#/  in browser
	1.GET Product Details :
	   a.Open Product-rest-controller
	   b.Open GET and click try out
	   c.Enter product id : 13860428 or something else
	   d.Click execute
	   e.You can verify the result in response body
	2.PUT product price			
	   a.Under Product-rest-controller click PUT method and click try out
	   b.Enter product Id and copy response from previous call and patse in product detail. 
		{
			"id": 13860428,
			"name": "The Big Lebowski (Blu-ray)",
			"current_price": {
				"value": 10000,
				"currency_code": "USD"
			}
		}
	   c.Click Execute  button
	   d.Call GET API to see if changes are reflecting fine.
Note:If you hit http://localhost:8080/product/13860428/price --> you will get price info for productId
         		
  		
