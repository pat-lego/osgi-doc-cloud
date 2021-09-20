![Build Status](https://github.com/pat-lego/osgi-doc-cloud/workflows/Build%20and%20Test/badge.svg)
# OSGi Doc Cloud SDK

The Doc Cloud SDK provides many useful services that allows developers to manipulate PDF and Word documents in order to use them to other services (i.e. Adobe Sign). By having the SDK run in a long lived JVM we can leverage simple JS frameworks's (Vue.js) allowing us to communicate with the deployed API's, which could lead to a simple Adobe Sign contract rendering front end.

This project wraps the SDK in a lightweight OSGi bundle allowing developers to extend and leverage the necessary API's for their own usage.

## How to build

`mvn clean install`

## Example Setup

The Karaf Module is built to be deployed in Apache Karaf allowing developers to interact with it. I have developed it with Karaf 4.3.2, simply build the module and deploy it to Karaf. 

Make sure to place your `pdfservices-api-credentials.json` and the `private.key` file in the `osgi-doc-cloud/core/src/main/resources` folder in order to allow for successful authentication with the Doc Cloud API.

# Contributor

- [Patrique Legault](https://github.com/pat-lego)