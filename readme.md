# Pets Manager

To build the project use maven

	- mvn clean install

To execute 

	- java -jar target/petsManager-1.0-SNAPSHOT.jar PATH/pets.csv name=princess


In order to keep it simple, the project was developed without own Exception
PetService receive a IPetRepository, which in a real project will be managed through dependency injection
