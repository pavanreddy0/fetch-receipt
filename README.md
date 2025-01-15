# Fetch - Receipt Processor Challenge


# Prerequisites
Make sure you have the following installed on your machine:

-> Docker: Follow the official Docker installation guide for your operating system.

# Setup
1. Clone the repository  
``` 
git clone git@github.com:pavanreddy0/fetch-receipt.git 
```
2. Build the Docker image - **docker-compose up**

   NOTE:_ Please make sure No service is running on port 8080. _

# Running the Application
1. Start the application using Docker Compose
   **docker-compose up**
   This command will start the application container along with any necessary dependencies defined in the docker-compose.yml file.

2. Access the application


## Host url
```
http://127.0.0.1:8080
```

# sample API calls

### POST API
```
curl --location 'http://127.0.0.1:8080/receipts/process' \
--header 'Content-Type: application/json' \
--data '{
    "retailer": "Target",
    "purchaseDate": "2022-01-01",
    "purchaseTime": "13:01",
    "items": [
        {
            "shortDescription": "Mountain Dew 12PK",
            "price": "6.498"
        },
        {
            "shortDescription": "Emils Cheese Pizza",
            "price": "12.25"
        },
        {
            "shortDescription": "Knorr Creamy Chicken",
            "price": "1.26"
        },
        {
            "shortDescription": "Doritos Nacho Cheese",
            "price": "3.35"
        },
        {
            "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
            "price": "12.00"
        }
    ],
    "total": "35.35"
}'
```
### GET API
```
curl --location 'http://127.0.0.1:8080/receipts/{id}/points'
```

stop the application and the associated containers, use the following command
**docker-compose down**