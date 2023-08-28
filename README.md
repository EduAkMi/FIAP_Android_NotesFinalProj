# FIAP - Android Final Project
## Integrants
- Ana Clara Farah
- Eduardo Misina
- Felipe Rodrigues
- Gabriel Todaro

## Description
To consolidate our learning on the Android discipline from FIAP Mobile MBA, we created a Notes app. As the name suggests, this application provides an interface to create and edit notes, listing them as well as providing local and remote saving features. A simple login path was developed to integrate firebase Authentication into the app.

## How to use
The simplest way to use the app is to install the file ``app-debug.apk``, located inside the folder 'apk' on the root folder of the project, into your device.

The second way is to import the project and integrate it inside your own Firebase Console account, meaning you would need to replace the google-services file with your own.

The first screen after opening the app for the first time is the Login screen where user is guided with two options: enter your credentials to login or enter the create an account screen and proceed to create a new account. After one of the paths is concluded, the notes screen provides the options to either create a new note or signout. Creating a new note will automatically create a local note as well as create a new note on Firestore whenever the user exits the screen. After the new note is created, it will be displayed with all previously created notes. Selecting one note will return to the edit note screen with all information stored. User can also change the color as well as delete as specific note.
