# my-Retail 
This is a RESTful service that provides API to get the product details from an external API and get the price details from another pricing api and provide integrated results to the users. <br />
It also provides API to change the price of the product. <br />

*Technologies used:*  <br />
	Spring boot (v2.1.3.RELEASE) <br />
	JUnit 4.12  <br />
	swagger 2.8.0 <br />
	Apache Maven 3.6.0 <br />
	
*Prerequisites:*  <br />
	Java 8  <br />
	Postman  <br />
	mongodb <br />
	
*Execution Procedure:*   <br />
        Case 1 : Local Mongo DB Setup with no authentication needed.  <br />
	     &nbsp;a. Use host :localhost and port : 27017.  <br />
	     &nbsp;b. Create new Database : myRetail (use myRetail)  <br /><br />		
        Case 2 : Mango DB needs authentication <br />
            &nbsp;a.	Go to Resource directory --> application.properties <br />
            &nbsp;b.	Uncomment spring.data.mongodb.username & spring.data.mongodb.password<br />
            &nbsp;c.	Update host and port information if needed.<br />
            &nbsp;d.	Go to project directory : testProject (Location of pom file) in command prompt<br />
		         &nbsp;&nbsp;&nbsp;&nbsp;   run : mvn package or mvn install
		
         Next Step : Go to project directory : testProject (Location of pom file) in command promp
                      Run following command : java -jar target/myproject-0.0.1-SNAPSHOT.jar
					
        Test Application :
	        To Get the list of product with prices
     		  Enter the url : .  http://localhost:8080/product/13860428  in the web browser to get the product details.  
	        To update price 
                   select PUT method and enter url http://localhost:8080/product/13860428  in address bar of postman
		   The request to the PUT method must be sent in JSON format in message body. 
		   You can change "current_price" tag in below  
		   JSON : {"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"value":10000.0,"currency_code":"USD"}}
			
         Alternatively use Swagger
          Enter url http://localhost:8080/swagger-ui.html#/  in browser
		
            a.	GET Product Details :
              1.Open Product-rest-controller
              2.Open GET and click try out
              3.Enter product id : 13860428 or something else
              4.Click execute
              5.You can verify the result in response body
            b.	PUT product price			
			         1. Under Product-rest-controller click PUT method and click try out
               2. Enter product Id and copy response from previous call and patse in product detail. 
                {
                  "id": 13860428,
                  "name": "The Big Lebowski (Blu-ray)",
                  "current_price": {
                    "value": 10000,
                    "currency_code": "USD"
                  }
                }
			          3. Click Execute  button
			          4. Call GET API to see if changes are reflecting fine.
			
Note**	if you hit http://localhost:8080/product/13860428/price --> you will get price info for productId       		
  		

		
