## Reading Is Good

### Create a Book
````
curl --location --request POST 'localhost:8180/book/createBook' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQzNDcyMDk5LCJleHAiOjE2NDQwNzY4OTl9.lsNU1CxSMdjv7d_DvlAFWLzz08Frsa3piCbvyiicvc_wh7KDMX0_h31wDUUNb3MVjTIFjjjR324Rqx2p6I8qnw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"deneme4",
    "stock":1,
    "price":30
}
````

Response: I got the following response because I created it from the same book. 
````
{
    "message": "Book already defined.",
    "httpStatus": "BAD_REQUEST",
    "timestamp": "2022-01-30T21:00:43.3830283Z"
}
````
This response will be returned if the new book is created successfully.

````
{
    "success": true,
    "message": "Book created successfully."
} 
````

Sign-Up and Sign-in Post Requests must be the same. Example request:

### Sign up   ````localhost:8180/api/auth/signup````
````
curl --location --request POST 'localhost:8180/api/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Selim",
    "username": "selim",
    "email": "s.s@com",
    "password": "selim"
}
````

### Sign In ````localhost:8180/api/auth/signin ````
````
curl --location --request POST 'localhost:8180/api/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "selim",
    "password": "selim"
}
````
I got the following response because I opened it in the same user
````
{
    "success": false,
    "message": "Username is already taken!"
}
````

### Update Stock ````localhost:8180/book/updateStock````
````
curl --location --request POST 'localhost:8180/book/updateStock' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQzNDcyMDk5LCJleHAiOjE2NDQwNzY4OTl9.lsNU1CxSMdjv7d_DvlAFWLzz08Frsa3piCbvyiicvc_wh7KDMX0_h31wDUUNb3MVjTIFjjjR324Rqx2p6I8qnw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"deneme3",
    "stock":30
}
````

### Create Order ````localhost:8180/order/createOrder````
````
curl --location --request POST 'localhost:8180/order/createOrder' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQzNDcyMDk5LCJleHAiOjE2NDQwNzY4OTl9.lsNU1CxSMdjv7d_DvlAFWLzz08Frsa3piCbvyiicvc_wh7KDMX0_h31wDUUNb3MVjTIFjjjR324Rqx2p6I8qnw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bookName": [
        "deneme3"
    ],
    "quantity": [
        1
    ]
}
````

Response 
````
{
    "success": true,
    "message": "Order created successfully."
}
````

### Query Order
````localhost:8180/order/queryOrder?orderId=9````

curl --location --request GET 'localhost:8180/order/queryOrder?orderId=9' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQzNDcyMDk5LCJleHAiOjE2NDQwNzY4OTl9.lsNU1CxSMdjv7d_DvlAFWLzz08Frsa3piCbvyiicvc_wh7KDMX0_h31wDUUNb3MVjTIFjjjR324Rqx2p6I8qnw' '''

Response
```` 
{
    "id": 9,
    "userId": 1,
    "date": "2022-01-30T19:41:55.840+00:00",
    "orderedBook": [
        {
            "id": 11,
            "bookName": "deneme",
            "amount": 2
        }
    ]
}
````