# RSO: Shipping microservice

## Prerequisites

```bash
docker run -d --name shippings -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=shipping -p 5433:5432 postgres:latest
```

## Run application in Docker

```bash
docker run -p 8086:8086 bozen/shippings
```

## Travis status 
[![Build Status](https://travis-ci.org/cloud-computing-project/payments.svg?branch=master)](https://travis-ci.org/cloud-computing-project/payments)
