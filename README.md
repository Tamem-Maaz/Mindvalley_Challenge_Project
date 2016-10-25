# Mindvalley_Challenge_Project
Challenge project for android position in the Mindvalley company

#### Evaluation Criteria for this Assignment:
 
1. ###### Readability:
	Class and method names should clearly show their intent and responsibility.
 
1. ###### Maintainability:
    “SOLID” Principles and design pattern.
    MVC model
    We want to see how you use and integrate 3rd party libraries
1. ###### Scalability:
    Your software should easily accommodate possible future requirement changes (example: if you are asked to change to xml-based api instead of json)

1. ###### Testability:
Please Unit Test your classes. In our assignment, you can just test all non-UI classes.

* * *

#### Test Start:
- Imagine you are on the Pinterest Android team and you are working with some colleagues on the pinboard (the scrolling wall of images), you split up the tasks among each other and your task is to create an image loading library that will be used to asynchronously download the images for the pins on the pinboard when they are needed. The library will also be useful for all other parts of the app where asynchronous remote image loading is required. The images are available on a publicly accessible URL (like a CDN). The library should be general purpose and not assume anything about the use case, the pinboard is an example but other parts of the app that show images will also use it (e.g. a user's profile pic on the profile screen).
 
- One of your colleagues will also want to use the library for loading JSON documents, and you just know that your boss and colleagues will love your library so much that they will ask you to support other datatypes in the future as well, so your design should not limit support to a particular datatype.
 
- The purpose of the library is to abstract the downloading and caching of remote resources (images, JSON, XML, etc.) so that client code can easily "swap" a URL for a Drawable, Map, etc. without worrying about any of the details. Resources which are reused often should not be continually re-downloaded and should be cached, but the library cannot use infinite memory.

##### Requirements:
- images and JSON should be cached efficiently (in-memory only, no need for caching to disk)
- the cache should have a configurable max capacity and should evict images not recently used
- an image load may be cancelled
- the same image may be requested by multiple sources simultaneously (even before it has loaded), and if one of the sources cancels the load, it should not affect the remaining requests
- multiple distinct resources may be requested in parallel
- you can work under the assumption that the same URL will always return the same resource
- the library should be easy to integrate into the Pinterest app but also any future Android projects of the company
- you are supposed to build a solid structure and use the needed programming design patterns.
- Adding Material design UI elements (Ripple, Fab button, Animations) is an advantage.
- Adding "pull to refresh” is an advantage.

#### Test End:

* * *

## Sample Code:
```
MProviderMDownloadDataType  mProvider = MProviderMDownloadDataType.getInstance();

MDownloadDataType mDataTypeJson = new MDownloadDataTypeJson(baseURL, new IMDownloadDataType() {
            @Override
            public void onStart(MDownloadDataType mDownloadDataType) {
                // Some Code Here
            }

            @Override
            public void onSuccess(final MDownloadDataType mDownloadDataType) {
                // Some Code Here
            }

            @Override
            public void onFailure(MDownloadDataType mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
                // Some Code Here
            }

            @Override
            public void onRetry(MDownloadDataType mDownloadDataType, int retryNo) {
                // Some Code Here
            }
        });
mProvider.getRequest(mDataTypeJson);
```
## Photos From APP
| Photo 1 | Photo 2 | Photo 3 |
|--------|--------|--------|
|<img src="/Photos/1 (1).jpg?raw=true" width="220" height="400">|<img src="/Photos/1 (2).jpg?raw=true" width="220" height="400">|<img src="/Photos/1 (3).jpg?raw=true" width="220" height="400">|
