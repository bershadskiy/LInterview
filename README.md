# Assumptions
I believe, that this exercise has nothing about user's data input handling, so I skipped that part.

I assume, that I will be able to add some additional logic for request.

I considering, that reports adding is only important operation with employees reporting.

Person 'address' field can be replaced with 'name'

# Files
Classes structure is placed inside 'entity' package for purposes of access control example.

## Interfaces

### IReporter
Contains Reports related actions

### IManager
Contains subordinates related actions

### IEmploymentManager
Contains employment management actions

## Classes

### Person
Base class. Represents a person, who want to apply

### ReporterAbstract
Contains Reports management implementation

### Employee
Represents an employed person with boss/subordinates management

### Corp
Contains employees storage and management logic

## Test
Contains automated test for Person and Corp classes