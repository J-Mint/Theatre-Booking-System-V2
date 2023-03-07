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

## Diagrams
![image](https://user-images.githubusercontent.com/112823669/223406884-93bf81a7-1764-40fc-a5ec-1e146ab35e36.png)
TODO UML
## What went well
TODO 

## Improvements to make
* No written tests.
* Error/ Exception handling needs improving.
* UI could be improved (improved usability/ consistency).
* Methods could broken up into smaller methods.

## Flow
TODO UPDATE SCREEN SHOTS
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

6) After logging in, the user is then shown all the seats for the show they clicked on earlier. Seats that are already booked, or that are in someone else's basket, cannot be selected. (If the user that logged in was an admin, see step 11)

![image](https://user-images.githubusercontent.com/112823669/218529018-84a9caec-614a-4420-95cd-9d353bbb14eb.png)

7) Upon selecting a seat, the user is prompted to select whether the seat is for a standard ticket or concession (where tickets have a 25% discount for under 16s, etc).

![image](https://user-images.githubusercontent.com/112823669/218529278-5b5f7afa-ad1f-41ba-9476-f8dec8642a5b.png)

8) Once all the required seats have been added, the user is taken to the basket screen where they have to option to book more tickets, remove a ticket or checkout.

![image](https://user-images.githubusercontent.com/112823669/218529679-c7d404e4-9aac-49fc-9c3c-64a98c392b39.png)

9) On the checkout screen, the user is asked for the relevant details to complete the purchase of tickets. IF the show is with 7 days of purchase, the user will not have the option of having the tickets posted to them (and the radio button is disabled).

![image](https://user-images.githubusercontent.com/112823669/218530224-3a7a51e3-0814-4b84-9236-e17a530688b0.png)

10) A Purchase Order file is generated on completion of ticket purchase and the user is logged out and taken back to the Welcome Screen.

![image](https://user-images.githubusercontent.com/112823669/223398489-6fc80eac-bfd3-4ba0-a25d-c98577d78a21.png)

11) ADMIN LOG IN. Admin is taken to the admin menu screen

![image](https://user-images.githubusercontent.com/112823669/223399087-da15a57e-40ef-4a35-90f6-f8097d0db1d3.png)

12) Stage Performance Screen. Staging a show will automatically generate 200 seats that can be booked for the performance.

![image](https://user-images.githubusercontent.com/112823669/223399192-c50a35b1-629f-4141-91cb-86936f7d5ff8.png)


![image](https://user-images.githubusercontent.com/112823669/223400314-2626a3fa-d6f0-45db-958b-c904ee7a9226.png)

13) Add Show Screen

![image](https://user-images.githubusercontent.com/112823669/223399280-03c9a19c-58d4-4df8-ae9e-d9a46a8c7ba5.png)

14) Remove Show Screen

![image](https://user-images.githubusercontent.com/112823669/223399375-8fcbf6ac-7d93-4b62-8e2b-2f7f4d5e545c.png)

15) Remove Performance Screen

![image](https://user-images.githubusercontent.com/112823669/223399477-1b8f22ab-8921-46b3-a02b-bdd031e8168b.png)

16) Logout button will log the user out and take them to the Welcome Screen. Browse shows will take the user to the Show Browsing Screen. 
