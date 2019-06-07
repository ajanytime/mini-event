this is the repository for a coding interview

Features implemented,

Features that could have been implemented,



Before Installation,

1: Make sure you have below software installed on your system,
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

./run.sh (if you're on *nix systems) and go for a coffee, use http://localhost:9090 to access UI after everything finishes

if you use Windows based system (what??), do the following,

1: run gradlew.bat clean build 
2: docker-compose up

