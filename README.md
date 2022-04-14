# OpCl-JAVA-P5
A local web API, reading a specific json files to process informations using Endpoints. This API respects SOLID princips and REST architecture.

##  Run API

Default Controller URL `http://localhost:8080/`.   


## Json input file
> example  

datafile.json :

``` 
  {
    "persons": [
        { "firstName":"John", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"jaboyd@email.com" },
        { "firstName":"Jacob", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6513", "email":"drk@email.com" },
        { "firstName":"Tenley", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"tenz@email.com" },
        { "firstName":"Roger", "lastName":"Boyd", "address":"29 15th St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"jaboyd@email.com" },
        { "firstName":"Felicia", "lastName":"Boyd", "address":"29 15th St", "city":"Culver", "zip":"97451", "phone":"841-874-6544", "email":"jaboyd@email.com" },
      ],
    "firestations": [
	{ "address":"1509 Culver St", "station":"3" },
        { "address":"29 15th St", "station":"2" },
	],
    "medicalrecords": [
        { "firstName":"John", "lastName":"Boyd", "birthdate":"03/06/1984", "medications":["aznol:350mg", "hydrapermazol:100mg"], "allergies":["nillacilan"] },
        { "firstName":"Jacob", "lastName":"Boyd", "birthdate":"03/06/1989", "medications":["pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"], "allergies":[] },
        { "firstName":"Tenley", "lastName":"Boyd", "birthdate":"02/18/2012", "medications":[], "allergies":["peanut"] },
        { "firstName":"Roger", "lastName":"Boyd", "birthdate":"09/06/2017", "medications":[], "allergies":[] },
        { "firstName":"Felicia", "lastName":"Boyd","birthdate":"01/08/1986", "medications":["tetracyclaz:650mg"], "allergies":["xilliathal"] },
}
```  
  
## Endpoints

### GET  

**http://localhost:8080/firestation?stationNumber={station_number}**
  
>Cette url doit renvoyer une liste des personnes couvertes par la caserne de pompiers correspondante.
>Donc si le numéro de la station = 1, il faut retourner les habitants couverts par la station numéro 1. La liste
>doit comporter les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. En outre,
> elle doit fournir le décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou plus
>moins) dans la zone de service.
  
**http://localhost:8080/childAlert?address={address}**
  
>Cette URL doit renvoyer une liste d'enfants (toute personne âgée de 18 ans ou moins) vivant à cette adresse.
>La liste doit inclure le prénom et le nom de chaque enfant, son âge et une liste des autres
> membres du ménage. S'il n'y a pas d'enfant, cette URL peut renvoyer une chaîne vide
  
**http://localhost:8080/phoneAlert?firestation={firestation_number}**
  
Cette url doit renvoyer une liste des numéros de téléphone des résidents desservis par la caserne de pompiers.
> pompiers. Nous l'utiliserons pour envoyer des SMS d'urgence à des foyers spécifiques.
  
**http://localhost:8080/fire?address={address}**
  
>Cette url doit renvoyer la liste des habitants habitant à l'adresse indiquée ainsi que le numéro de la caserne
> des pompiers qui la desservent. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
> médical (médicaments, posologie et allergies) de chaque personne

**http://localhost:8080/flood/stations?stations={list_of_station_numbers}**
  
>Cette url doit renvoyer une liste de tous les foyers desservis par la caserne. Cette liste doit inclure les
>personnes par adresse. Il doit également inclure le nom, le numéro de téléphone et l'âge des habitants, et
>inclure leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
  
**http://localhost:8080/personInfo?firstName={firstName(not_required)}&lastName={lastName}**
  
>Cette url doit renvoyer le nom, l'adresse, l'âge, l'adresse e-mail et les antécédents médicaux (médicaments,
>dosage, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
> tous apparaissent.

**http://localhost:8080/communityEmail?city={city}**

>Cette url doit renvoyer les adresses email de tous les habitants de la ville
  
### ADD / PUT / DELETE


**/person**

```
{ "firstName":"Didier", "lastName":"Jean", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"2000006", "email":"jaboyd@email.com" }
```  
Créer/modifier ou supprimer une personne.

**/firestation**

```  
{ "address":"29 15th St", "station":"2" }
```  

Créer/modifier ou supprimer une caserne.

**/medicalRecord**

```  
{ "firstName":"Didier", "lastName":"Jean", "birthdate":"03/06/1984", "medications":["aznol:350mg", "hydrapermazol:100mg"], "allergies":["nillacilan"] }
```  

Créer/modifier ou supprimer un dossier médical.
  
    

