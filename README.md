#  Springen
You can find demo [here](https://youtu.be/IGTRRLgFVmo).
## Description
Springen is created for the purpose of the Domain-Specific Languages course at the Faculty of Technical Sciences, University of Novi Sad.

This DSL describe spring-boot application with [CRUD operations](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) for any Business Object Model.

Springen DSL contains entities with simple and complex data types, relations, packages and configuration parameters. 

Springen, when is generated from this language, has three layer architecture. For presentation layer springen generates jsp pages, for business logic generates spring-boot application, and for data layer can be used a different database. In configuration can be set a parameters for database. Also, in configuration can be set parameters for dependency injection type such as [constructor](https://www.baeldung.com/constructor-injection-in-spring), [setter](https://www.javatpoint.com/spring-tutorial-dependency-injection-by-setter-method) and property.

Also, Springen has [gRPC](https://grpc.io/).

## Example

This is example for meta model and model.
There are links for [language](https://github.com/vujasinovic/springen/blob/development/meta/entity.tx) and [example of language](https://github.com/vujasinovic/springen/blob/development/model/model.ent) in our repository.

### Meta model

![Image of meta model](https://github.com/vujasinovic/springen/blob/import/images/metamodel.svg)

### Model example

![image of model](https://github.com/vujasinovic/springen/blob/import/images/model_example.svg)

### Generated jsp pages

New form

![image of new form](https://github.com/vujasinovic/springen/blob/import/images/new_form.PNG)
Overview

![image of overview_all](https://github.com/vujasinovic/springen/blob/import/images/overview_all.PNG)
Detail overview

![image of overview_single](https://github.com/vujasinovic/springen/blob/import/images/overview_single.PNG)

## Technologies used
* Python 3.6+
* [textX](https://textx.github.io/textX/stable/)
* Jinja2 template engine

## Contributors
* [Aleksandar Vujasinovic](https://github.com/vujasinovic)
* [Pavle Jankovic](https://github.com/pavle-j4nk)
* [Luka Jovanovic](https://github.com/lukajvnv)
* [Viktor Djuka](https://github.com/djuka10)
* [Srdjan Popovic](https://github.com/srdjan14)
