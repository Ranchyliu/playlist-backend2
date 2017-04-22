# Playlist-Backend2 Database and RESTful API

The database and REST API is deployed on Google Cloud Platform.
The backend is support by Google Cloud Endpoints using
Java on Google App Engine Standard.

## Begin start

0. Install [cURL](https://curl.haxx.se/download.html) for testing purposes.

0. [Download the Google Cloud SDK](https://cloud.google.com/sdk/docs/quickstarts).

0. Update the Cloud SDK and set the default project to your project ID by invoking the following commands:
        
        gcloud components update
        gcloud config set project [PROJECT-ID]
    Replace [PROJECT-ID] with your project ID.

0. If you don't have Java 8, [download and install](http://www.java.com/en/download/manual.jsp) it.

0. If you don't have Maven 3.3.9 or higher, [download](http://maven.apache.org/download.cgi) and [install](http://maven.apache.org/install.html) it.

## Building the sample project

To build the project:

    mvn clean package

## Generating the openapi.json file

To generate the required configuration file `openapi.json`:

    mvn exec:java -DGetSwaggerDoc

## Deploying the sample API to App Engine

To deploy the sample API:

0. Invoke the `gcloud` command to deploy the API configuration file:

         gcloud service-management deploy openapi.json

0. Deploy the API implementation code by invoking:

         mvn appengine:deploy

    The first time you upload a sample app, you may be prompted to authorize the
    deployment. Follow the prompts: when you are presented with a browser window
    containing a code, copy it to the terminal window.

0. Wait for the upload to finish.

## Sending a request to the sample API

After you deploy the API and its configuration file, you can send requests
to the API.

To send a request to the API, from a command line, invoke the following `cURL`
command:
        
     curl -X GET \
        https://playlist-backend2.appspot.com/_ah/api/users/v1/getInfo\?email\=rq@gatech.edu

     curl \
         -H "Content-Type: application/json" \
         -X POST \
         -d '{"email":"aa@gatech.edu"}' \
         https://playlist-backend2.appspot.com/_ah/api/users/v1/register

You will get a 200 response with the following data:

    {
     "message": "200"
    }

## CI configuration
[codeship link](https://app.codeship.com/projects/212556/configure_tests)
updated...
......v10
