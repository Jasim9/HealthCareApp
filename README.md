HealthCareApp
=============

Civic Hackathon

Android App Name: Smart Care

Android project folder: dAlert (ignore other files they correspond to the older version)

This is an android app aimed for citizens of Pakistan (Lahore region for now) which aims to provide them with valuable
information about infectious disease breakouts such as Dengue Fever and Malaria. 
Application notifies the users if they are in an area of high danger. 
The welcome screen of the application has the option for the user to select the disease he is interested in knowing about.
Currently, we support two diseases, Malaria and Dengue. But the plans are underway to extend this app to 18 different 
diseases which would be done in collaboration with the Punjab IT board that collects data from hospitals about them.
Our current data set is obtained from a public PITB portal. 
Once a disease is selected by the user, the map button, shows occurrences of disease on a map.
The User is notified if there’s a high occurrence of the disease of interest around him. 
We use Android’s Maps API and heat maps for the presentation of data.  
Calculation of danger is performed if the number of disease occurrences in a 1-5 KM radius around the user is more 
than a given threshold. 
The upcoming versions of the App would also support SMS alerts if the app user is travelling through a 
highly affected area. 
