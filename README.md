This is the repository for a coding interview.

Features implemented,

- Dockerization with docker compose
- Spring Boot 2
- Layered code
- Stateless application to ease scaling
- Toyed with Spring Boot's async capabilities, little impact on code readability
- Hyper Media enabled Restful service using Spring HATEOAS, again little imapct on code readibility
- Redis as fast database given the scope of the assignment
- Angular UI with NGRX store and effects
- UI and API hosted using different web servers within docker
- UI and API talks through reverse proxy (written in ngnix.conf)
- enable gzip in ngnix to fast transport the long string (65000 characters long)
- PrimeNG style UI components to use date range
- initial data load with multiple threads

Features that could have been implemented,

- UI test cases
- TestContainers to ease testing
- Java 8 async for all layers to make it truely non-blocking
- Better UI styling using CSS or LESS
- Better UX, like persisting search results when fliping between list and details views
- Angular Interceptor to handle status code like 500, 404, 400
- lazy loading of search results with limit and offset

Design Approach

- use Redis for fast data retrieval
- break Event object into summary and details (the 65000 character long string)
- store summary and details in separate redis hash
- enable gzip to fast transport long text (65000 characters zipped into 80k)
- search only the summaries (small and light)
- fetch details long text only in details view
- rely on PrimeNG UI components to provide fontend pagination and date range search


Before Installation,

1: Make sure you have below software installed,
 - docker
 - docker-compose 
 - git
 - JDK 8
 
2: This application makes use of port, 8080, 9090, please make sure these ports are free before proceeding with installation.


Installation Steps,

step 1,

git clone https://github.com/ajanytime/mini-event.git

step 2,

copy data file to  ${your workspace}/mini_event/mini-event-api/src/main/resources/
(data file is currently named as 'assignment_data_full.json', to change data file name, open application.properties and modify value 'data.file')

step 3,

Unix/Linux/MacOS
1: ./run.sh  
2: use http://localhost:9090 to access UI once done.

Windows
1: run gradlew.bat clean build 
2: docker-compose up
3: use http://localhost:9090 to access UI once done.


Cheers.
