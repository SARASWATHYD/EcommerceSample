# EcommerceSample
 Ecommerce API provide endpoint for Placing order and getting order in an eCommerce application

## Prerequisites
- Java 17
- Spring Boot 3.1.0
- Static Data considered as DB
- Build tool Maven 3.9.2

## Getting Started
1. Clone the repository using: git clone <repository-url>
2. Build the project using Maven ```mvn clean install```
3. Run the application ```java -jar Ecommerce-<REPLACE VERSION ID>-SNAPSHOT.jar```

## API Documentation

**URL**   `api/v1/ecommerce`
**METHOD** : `POST`

### Purchase Order Example

**Request Body** : 
```
{
    "type": 3,
    "length": 100,
    "order": {
        "id":1,
        "products": [
            {
                "id":5,
                "quantity" :4
            }
        ]
    }
}
```

**Response** :
```
{
    "id": 2,
    "products": [
        {
            "id": 5,
            "name": "Building Microservices",
            "price": 15,
            "quantity": 4,
            "productType": "BOOK"
        }
    ]
}
```

### Get order by Id

**Request payload**

```
{
    "type": 2,
    "length": 100,
    "order": {
        "id":1    
    }
}
```

**Response**

```
{
    "id": 1,
    "products": [
        {
            "id": 5,
            "name": "Building Microservices",
            "price": 15,
            "quantity": 4,
            "productType": "BOOK"
        }
    ]
}
```


### Get All orders

**Request payload**

```
{
    "type": 1,
    "length": 24324
}
```

**Response**

```
{
    "orders": [
        {
            "id": 1,
            "products": [
                {
                    "id": 5,
                    "name": "Building Microservices",
                    "price": 15,
                    "quantity": 4,
                    "productType": "BOOK"
                }
            ]
        },
        {
            "id": 2,
            "products": [
                {
                    "id": 5,
                    "name": "Building Microservices",
                    "price": 15,
                    "quantity": 4,
                    "productType": "BOOK"
                }
            ]
        },
        {
            "id": 3,
            "products": [
                {
                    "id": 1,
                    "name": "Steve Jobs",
                    "price": 30,
                    "quantity": 4,
                    "productType": "BOOK"
                }
            ]
        }
    ]
}
```


