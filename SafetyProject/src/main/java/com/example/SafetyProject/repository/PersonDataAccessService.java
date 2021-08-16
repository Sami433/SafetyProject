package com.example.SafetyProject.repository;

/*@Repository("json")
public class PersonDataAccessService {
    private final DataHandler dataHandler;

    public PersonDataAccessService(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
    public List<Person> selectPeople(String lastName) {
        return dataHandler.getData().getPersons();
    }

    public List<String> selectPeopleByCity(String city) {
            return dataHandler.getData().getPersons().stream()
                    .filter(person -> person.getCity().equals(city))
                    .map(person -> person.getEmail())
                    .collect(Collectors.toList());
        }


}
*/