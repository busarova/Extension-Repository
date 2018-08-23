# extension-repository

Final Project Assignment in Telerik Academy by Tick42.

Installation instructions at the bottom.

*Used Technologies: Java, Spring MVC, Hibernate, MariaDB, Thymeleaf, Bootstrap, CSS, HTML*

## Funcionalities:

- Public/User/Admin part
- Register/Login 
- Search
- Approve func (extensions must be approved by admin before published)
- Upload/Download extension file (stored in database)

## Public:

- See Repositories grouped by Featured, Popular, New
- Sort Repositories by Name, Downloads, Last Commit Date, Upload Date
- Inspect Repository Details
- Search by name
- Download Extension File

## User:

- User Page
- Create extension
- See extensions he created
- Edit extensions he created (edit, delete, update)
- Upload/Update extension file, if owner of extension
- See status of extension (approved or not)

## Admin:

- All user funcionalities
- Approve extensions (once approved extensions are published)
- Edit all extensions (edit, delete, update)
- See all users
- Disable/Enable Users (cannot login until enabled)


## Installation

Clone the project and build it as a Gradle project.In the main directory you will find extension_repository.sql.This is the database.Build it in your MySql client.

Make user: dev_user with password dev_user  or change it from application.properties.

The program runs on port 8080.  So run it and open http://localhost:8080/ in your browser. 

Enjoy!
