# Theatre-Booking-System-V3
A program to browse, search and book theatre tickets.
Uses Java (inc Swing and JDBC) and MySQL 

## Project Outline
![image](https://user-images.githubusercontent.com/112823669/219088192-d931be0d-175e-4c42-a788-81e5b471ed53.png)

[view the full pdf](https://github.com/J-Mint/Theatre-Booking-System-V3/files/10745071/java_final_project_spec.pdf)

## Features
![image](https://user-images.githubusercontent.com/112823669/219087110-5fcbe827-45b8-493d-a394-8e9f22977dd3.png)


# This project is a work in progress.

An out of date video demo from 09/02/2023 (much progress has been made since then)

https://user-images.githubusercontent.com/112823669/218261108-b97a4934-b796-4759-9951-de5fd0377b64.mp4

## To do:
* Admin menu options screen
* SQL Triggers and stored functions
* Testing
* Exception handling
* tidy-up constructors (improve readbility), rename variables, add comments, make certain variables private in scope.
* refeactor initial java code (remove certain parent/ child classes)
* Format (DOUBLE) price so that it is to 2 decimal 
* Update SQL queries
* change the basket database to have user id, rather than a collection of multiple baskets
* Checkout compeltion method
* update display frame
* upload the Project spec
* upload ERD and UML diagram
* Improve this readme file
* Upload SQL script




## Diagrams

## What went well

## Issues

## Improvements

## Flow
1) Welcome screen

![image](https://user-images.githubusercontent.com/112823669/218526974-a0e77228-5322-4118-8171-85bdf25c8d07.png)

2) Browse screen (Results are from querying the databases. user is able to refine the results using the search bar at the top of the window which performs a different query)

![image](https://user-images.githubusercontent.com/112823669/218527129-375e0eb6-16ac-4f3b-8dc0-b3f6c2b76b94.png)

3) Detailed performance screen (shows more info about a specific show and ggives the user a chance to make sure that they selected the correct show before going through the booking proces)

![image](https://user-images.githubusercontent.com/112823669/218527786-1501f86d-97dd-4bfc-9af2-2803360010af.png)

4) (if not already logged in) The user is taken to the login screen. The user is required to log in so that they only see the basket that is theirs. 

![image](https://user-images.githubusercontent.com/112823669/218527996-696760dc-4c39-4823-a51b-b462152065d0.png)

5) (if the user does not have an account) The user is able to register a new account

![image](https://user-images.githubusercontent.com/112823669/218528623-86f57b9c-c6c5-4fd0-bb23-9f53b25016ab.png)

6) The user is then shown all the seats for the show they clicked on earlier. Seats that are already booked, or that are in someone else's basket,  cannot be selected.

![image](https://user-images.githubusercontent.com/112823669/218529018-84a9caec-614a-4420-95cd-9d353bbb14eb.png)

7) Upon selecting a seat, the user is prompted to select whether the seat is for a standard ticket or concession (where tickets have a 25% discount for under 16s, etc).

![image](https://user-images.githubusercontent.com/112823669/218529278-5b5f7afa-ad1f-41ba-9476-f8dec8642a5b.png)

8) Once all the required seats have been added, the user is taken to the basket screen where they have to option to book more tickets, remove a ticket or checkout.

![image](https://user-images.githubusercontent.com/112823669/218529679-c7d404e4-9aac-49fc-9c3c-64a98c392b39.png)

9) On the checkout screen, the user is asked for the relevant details to complete the purchase of tickets. IF the show is with 7 days of purchase, the user will not have the option of having the tickets posted to them (and the radio button is disabled).

![image](https://user-images.githubusercontent.com/112823669/218530224-3a7a51e3-0814-4b84-9236-e17a530688b0.png)


![image](https://user-images.githubusercontent.com/112823669/218529925-a7bcf0f9-2b71-46b5-a1ec-c025aa84f16b.png)


