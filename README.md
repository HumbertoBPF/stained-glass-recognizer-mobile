<h2>Stained Glass Recognizer API</h2>

<h3>Context</h3>

<p>The code in this repository was developed in a six-month project as part of a course 
(Pôle Project) of CentraleSupélec (University Paris-Saclay). I was a member of a group of 
four students and we developed an Android application capable of recognizing 
Stained Glass from churches.</p>

<p>The source code that you find here is the Android application where users can upload an
image and, after calling an API for image processing and classification (source code available
on https://github.com/HumbertoBPF/stained-glass-recognizer-api), check the results. The API 
compares the uploaded picture to a database of 1800 stained glass images and returns data of the 
most similar one.</p>

<h3>Android Application:</h3>

<p><strong>Important:</strong> to use some features of the app, you must launch the API first.</p>

<p>The Android application is composed of three screens:</p>

<ul>
    <li>
        An initial screen where users can select a picture from their cellphones. They can do so
by clicking on the "select an image" button or the placeholder picture. After picking 
an image, a preview will be displayed and you can upload the image (the upload will succeed only if
the API is up).
    </li>
    <li>
        A settings screen accessible via the gear icon on the initial screen. Here you can change 
the language of the application. The options available are English, French, Portuguese, and Spanish.
    </li>
    <li>
        A screen with the API results. Here, you will find details about the most similar stained 
glass in the database, such as the artist, the glass date, the church where it is exhibited, etc.
    </li>
</ul>

<h3>Installing the project</h3>

<p>Installing the project is simple. Follow the steps below:</p>

<ul>
    <li>Download the source code.</li>
    <li>Open the project on Android Studio (I used Android Studio Giraffe | 2022.3.1 Patch 2, 
but a more modern version should also work).</li>
    <li>The IDE will sync the configuration files and install the dependencies.</li>
    <li>Create a "secret.properties" file in the root of your project. This file must contain a 
variable named "apiUrl" whose value must be the URL of the API to be called by the app.</li>
    <li>You can launch the app on an emulated device or a physical device.</li>
</ul>