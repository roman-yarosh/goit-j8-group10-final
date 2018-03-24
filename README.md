# GoIT Java 8 Group-10 Final Project.

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/0k6r/GoITFinalProject/master/LICENSE)

## Table of contents

- [About](#about)
- [Demo](#demo)
- [Technical task](#technical-task)
- [Documentation](#documentation)
- [License](#license)

## About

We are group 10 from GoIT Java-8 course.
Our team consists of tree java developers: Roman Yarosh, Anastasiia Degtjarova, Konstantin Petrov

#### Project start date 19.02.2018

## Demo

[Our github repository url for PostgreSQL](https://github.com/roman-yarosh/goit-j8-group10-final)

[Our github repository url for MySQL](https://github.com/0k6r/GoITFinalProject)

## Technical task

### Необходимо разработать REST сервер, который имеет следующий функционал:

* Приложение хранит информацию о сотрудниках компании и позволяет вести учет рабочего времени.
* В базе данных должны храниться данные о:
1. Сотрудниках,
2. Должностях сотрудников,
3. Отделе в котором работает сотрудник,
4. Событие (рабочий день, техническая учеба),
5. Статусе сотрудника (больничный, работает, отпуск)
6. Должны быть реализованы 3 уровня доступа к системе:
    * Администратор -  доступ ко всему функционалу (управление ролями, назначение модераторов и т.д.)
    * Модератор - управление данными о сотрудниках (создание, редактирование и т.д.) и рабочих часах
    * Сотрудник - только чтение данных о себе и своем рабочем времени.

- [ ] У каждого сотрудника должна быть часовая ставка. Первого числа каждого месяца, система должна авооматичкски подсчитывать зарплату сотрудника и сохранять ее.Расчетный лист должен автоматически отправляться сотруднику.
- [ ] Необходимо реализовать возможность получения данных о зп сотрудника за любой период.
- [ ] Событие - модератор создает событие
- [ ] Указывает тип события
- [ ] Указывает список сотрудников, которые принимают участие и количество часов для каждого из сотрудников.
- [ ] Данные об отработанных часах суммируются в месячные отработанные часы.

**Технологически стек:**Java, SQL, Spring (MVC, DATA, SECURITY),spring boot, Maven, MySQL, Git, Swagger, Liquidbase.Приложение должно быть развернуто на удаленном сервере (Heroku, AWS, etc.).Результатом выполнения задачи должен быть отдельный репозиторий с описание задачи, документацией по проекту, UML диаграммой проекта и ссылкой на сервер.

По желанию, разрешается реализовать клиентскую часть приложения - front end с использованием любой frontend технологии.

На выполнение проекта - 3 недели!

## Documentation

Spring Security credential data for login our REST server (user [user role]):
                        
                    roman     [ROLE_ADMIN], 
                    kostya    [ROLE_ADMIN], 
                    nastya    [ROLE_ADMIN],
                    user      [ROLE_USER],
                    moderator [ROLE_MODERATOR]
               and password for all users: 123

You can get information from our REST server using next api links:
   
    Information about personal salary:
    https://goit-j8-group10-final.herokuapp.com/api/personalSalaries/{username}/(from year)/{from month}/{to year}/{to month},
    where    
        {username} is username for current user,
        (from year) is year from what we start to show salary,
        {from month} is month from what we start to show salary,
        {to year} is year till what we stop to show salary,
        {to month} is month till what we stop to show salary
        
    Information about all or exact employee: 
        https://goit-j8-group10-final.herokuapp.com/api/employees/
        https://goit-j8-group10-final.herokuapp.com/api/employees/{employee id}
        To save employee use api/employees/, pass employee as parameter and RequestMethod.POST
        To delete employee use api/employees/{employeeId} and RequestMethod.DELETE
        
    Information about about all or exact department: 
            https://goit-j8-group10-final.herokuapp.com/api/departments/
            https://goit-j8-group10-final.herokuapp.com/api/departments/{department id}
            To save department use api/departments/, pass department as parameter and RequestMethod.POST
            To delete department use api/departments/{departmentId} and RequestMethod.DELETE
            
    Information about about all or exact event: 
            https://goit-j8-group10-final.herokuapp.com/api/events/
            https://goit-j8-group10-final.herokuapp.com/api/events/{event id}
            To save event use api/events/, pass event as parameter and RequestMethod.POST
            To delete event use api/events/{eventId} and RequestMethod.DELETE
            
    Information about about all or exact event type: 
            https://goit-j8-group10-final.herokuapp.com/api/eventTypes/
            https://goit-j8-group10-final.herokuapp.com/api/eventTypes/{eventType id}
            To save event type use api/eventTypes/, pass event type as parameter and RequestMethod.POST
            To delete event type use api/eventTypes/{eventTypeId} and RequestMethod.DELETE
            
    Information about about all or exact position: 
            https://goit-j8-group10-final.herokuapp.com/api/positions/
            https://goit-j8-group10-final.herokuapp.com/api/positions/{position id}
            To save position use api/positions/, pass position as parameter and RequestMethod.POST
            To delete position use api/positions/{positionId} and RequestMethod.DELETE
            
    Information about about all or exact salary: 
            https://goit-j8-group10-final.herokuapp.com/api/salaries/
            https://goit-j8-group10-final.herokuapp.com/api/salaries/{salary id}
            To save salary use api/salaries/, pass salary as parameter and RequestMethod.POST
            To delete salary use api/salaries/{salaryId} and RequestMethod.DELETE
            
    Information about about all or exact status: 
            https://goit-j8-group10-final.herokuapp.com/api/statuses/
            https://goit-j8-group10-final.herokuapp.com/api/statuses/{status id}
            To save status use api/statuses/, pass status as parameter and RequestMethod.POST
            To delete status use api/statuses/{statusId} and RequestMethod.DELETE
                
    Information about about all or exact user: 
            https://goit-j8-group10-final.herokuapp.com/api/users/
            https://goit-j8-group10-final.herokuapp.com/api/users/{user id}
            To save user use api/users/, pass employee as user and RequestMethod.POST
            To delete user use api/users/{userId} and RequestMethod.DELETE
                   
    Information about about all or exact role: 
            https://goit-j8-group10-final.herokuapp.com/api/roles/
            https://goit-j8-group10-final.herokuapp.com/api/roles/{role id}
            To save role use api/roles/, pass role as parameter and RequestMethod.POST
            To delete role use api/roles/{roleId} and RequestMethod.DELETE
    
    All model classes you can get on Git repository from package:
            goit-j8-group10-final/src/main/java/ua/goit/finall/model/ 

## License

MIT
