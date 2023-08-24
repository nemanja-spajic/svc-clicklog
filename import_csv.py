import csv
import psycopg2
import pytz
from datetime import datetime

# Open the CSV file
with open('click_log.csv', 'r') as csvfile:
    reader = csv.DictReader(csvfile)

    # Connect to the PostgreSQL database
    conn = psycopg2.connect(database="svc_clicklog", user="svc_clicklog", password={PUT_PASSWORD_HERE}, host="localhost", port="5432")
    cursor = conn.cursor()

    # Iterate through each row in the CSV
    for row in reader:
        # Convert Unix timestamp to datetime
        unix_timestamp = int(row['timestamp'])
        timestamp = datetime.fromtimestamp(unix_timestamp)
        utc_timezone = pytz.timezone('UTC')
        timestamp_utc = timestamp.astimezone(utc_timezone)
        country = row['country']
        if country == '':
            country = None
        state = row['state']
        if state == '':
            state = None
        city = row['city']
        if city == '':
            city = None

        # Insert data into the database
        cursor.execute("INSERT INTO advertisement_logs (timestamp, type, campaign, banner, content_unit, network, browser, operating_system, country, state, city) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", (timestamp_utc, row['type'],row['campaign'],row['banner'],row['content_unit'],row['network'],row['browser'],row['operating_system'],country,state,city))

    # Commit changes and close connection
    conn.commit()
    conn.close()