# svc-clicklog

Welcome to the `svc-clicklog` Readme! This document provides an overview of the service and its API endpoint for
fetching the number of clicks for a campaign. The service is designed to provide flexibility in fetching click
statistics based on time intervals.

## API Endpoint

The `svc-clicklog` exposes the following API endpoint:

### Fetch Clicks for Campaign

**Endpoint: ```/api/campaign/{campaignId}/clicks```**

**HTTP Method**: ``GET``

This endpoint allows you to retrieve the number of clicks for a campaign, with the option to filter by time intervals.

#### Parameters

* `startAt` (optional): The start time for the time range in the format `yyyy-MM-dd HH:mm:ss`. If provided, the endpoint
  will return the number of clicks from this point till the end.
* `endAt` (optional): The end time for the time range in the format `yyyy-MM-dd HH:mm:ss`. If provided, the endpoint
  will return the number of clicks from the beginning till this point.
* If both ``startAt`` and ``endAt`` are provided, the endpoint will return the number of clicks that occurred within the
  specified time range.

#### Example Usage

1. Fetch all clicks:

```
GET /api/campaign/{campaignId}/clicks
```

2. Fetch clicks from a specific start time till the end:

```
GET /api/campaign/{campaignId}/clicks?startAt=2023-08-01 00:00:00 
```

3. Fetch clicks from the beginning till a specific end time:

```
GET /api/campaign/{campaignId}/clicks?endAt=2023-08-15 23:59:59 
```

4. Fetch clicks within a specific time range:

```
GET /api/campaign/{campaignId}/clicks?startAt=2023-08-01 00:00:00&endAt=2023-08-15 23:59:59 
```

#### Response

The response from the API will include the following information:

* HTTP Status Code: 200 OK
* Content-Type: application/json
  ```{
  {
      "campaignId": "your_campaign_id",
      "clicks": 123 
  }  
  ``` 

## Running the Service Locally

To run the `svc_clicklog` locally, follow these steps:

### 1. Set Up the Database

Before starting the service, you'll need a PostgreSQL database named `svc_clicklog` with a user `svc_clicklog`. If
you're using a different password, make sure to update it in the `application.yml` file. Alternatively, you can create
an
`application-default.yml` file to override the default configuration.

### 2. Prepare CSV Data

You'll need a `click_log.csv` file containing the data you want to use for testing. Before running the service, ensure
you modify the `import_csv.py` script to match your local environment's password. This script will populate the
necessary table in your database.

### 3. Run the Python Script

Execute the `import_csv.py` Python script to populate the table in your local database with the data from the
`click_log.csv` file.

```bash
python import_csv.py
```

### 4. Build and Run the Service

Now, you can build and run the `svc_clicklog`:

1. Open a terminal in the project's root directory.
2. Build the service using Maven:

```bash 
Copy code mvn clean install
```

3. Run the service using Maven:

```bash 
mvn spring-boot:run
```

### 5. Access the API

With the service running locally, you can access the API using the provided endpoint to fetch click statistics. Refer to
the API Endpoint section in the README for details on how to make API requests.

## Conclusion

The `svc-clicklog` API provides a convenient way to retrieve click statistics for campaigns with flexible time filtering
options. Use the provided endpoint to fetch the number of clicks based on your requirements. If you have any questions
or need further assistance, please don't hesitate to reach out to our support team.

Thank you for using the `svc-clicklog`!