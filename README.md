# extension-repository

![alt text](https://github.com/busarova/extension-repository/blob/master/src/main/resources/static/pics/presentation_pic2.png)

Final Project Assignment in Telerik Academy by Tick42.

Installation instructions at the bottom.

*Used Technologies: Java, Spring MVC, Hibernate, MariaDB, Thymeleaf, Bootstrap, Javascript, CSS, HTML*

## Functionalities:

- Public/User/Admin part
- Register/Login 
- Search
- Approve func (extensions must be approved by admin before published)
- Upload/Download extension file (stored in database)
- User Page (profile pic, owned extensions etc.)
- Search by tags
- Upload logo
- Validations
- Optimistic Locking
- Connects with GitHub API and takes data (last commit date, pull requests, open issues)
- Integration with continuous integration server (CircleCi)
- RestApi (public part)

## Public:

- Sort Repositories by Name, Downloads, Last Commit Date, Upload Date
- Inspect Repository Details
- Search by name
- Download Extension File
- Tag palace (showing all tags, search by tags etc.)

## User:

- User Page
- Create extension
- See extensions he created
- Edit extensions he created (edit, delete, update)
- Upload/Update extension file, if owner of extension
- See status of extension (approved or not)
- Upload profile pic
- Upload extension logo

## Admin:

- All user functionalities
- Approve extensions (once approved extensions are published)
- Edit all extensions (edit, delete, update)
- See all users
- Disable/Enable Users (cannot login until enabled)
- Could refresh synchronisation for all or single extension with github
- Displaying last successful sync


## Installation

Clone the project and build it as a Gradle project.In the main directory you will find extension_repository.sql.This is the database.Build it in your MySql client.

Make user: dev_user with password dev_user  or change it from application.properties.

The program runs on port 8080.  So run it and open http://localhost:8080/ in your browser. 

Enjoy!


![alt text](https://github.com/busarova/extension-repository/blob/master/src/main/resources/static/pics/presentation_pic1.png)
![alt text](https://github.com/busarova/extension-repository/blob/master/src/main/resources/static/pics/presentation_pic2.png)
![alt text](https://github.com/busarova/extension-repository/blob/master/src/main/resources/static/pics/presentation_pic3.png)
![alt text](https://github.com/busarova/extension-repository/blob/master/src/main/resources/static/pics/presentation_pic4.png)
![alt text](https://github.com/busarova/extension-repository/blob/master/src/main/resources/static/pics/presentation_pic5.png)
