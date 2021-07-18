# Cumple Bianchi Project

## Goal
The goal of this project is the realization of a simple gymkhana.
The types of steps are:
+ QUESTION
+ FACT
+ GIFT
+ COMPLETED

And the input types are:
+ DATE
+ TEXT
+ OPTION


## Tech

+ This project uses [Quarkus](https://quarkus.io/) and ([Kotlin](https://quarkus.io/guides/kotlin)).
+ Is deployed on each change on develop by **GitHub actions** 
+ Follow DDD pattern
+ JWT security

## Deploy and run
### Running the application in dev mode
*From quarkus docs*

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```