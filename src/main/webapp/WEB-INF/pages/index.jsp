<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
    <p><h1>Welcome!</h1></p>
    <p><a href="/employeeInfo">employeeInfo</a></p>
    <p><a href="/login">login</a></p>
    <p><a href="/logout">logout</a></p>
    <pre>Database security user [user role]:
                            roman [ROLE_ADMIN],
                            kostya [ROLE_ADMIN],
                            nastya [ROLE_ADMIN],
                            user [ROLE_USER],
                            moderator [ROLE_MODERATOR]
                            and password for all users: 123</pre>

    <p><a href="https://github.com/roman-yarosh/goit-j8-group10-final/blob/master/README.md">Documentation for API</a></p>
    <p>Examples of how to use our API:</p>
    <p>Get personal salary for logged in user for specified period:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/personalSalaries/${name}/2018/1/2018/3">http://goit-j8-group10-final.herokuapp.com/api/personalSalaries/${name}/2018/1/2018/3</a></p>
<br>
    <p>Get all employees:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/employees/">http://goit-j8-group10-final.herokuapp.com/api/employees/</a></p>
    <p>Get specified employee by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/employees/1">http://goit-j8-group10-final.herokuapp.com/api/employees/1</a></p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/employees/2">http://goit-j8-group10-final.herokuapp.com/api/employees/2</a></p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/employees/3">http://goit-j8-group10-final.herokuapp.com/api/employees/3</a></p>
    <p>To save employee use api/employees/, pass employee as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/employees/{employeeId} and RequestMethod.DELETE</p>
<br>
    <p>Get all departments:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/departments/">http://goit-j8-group10-final.herokuapp.com/api/departments/</a></p>
    <p>Get specified department by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/departments/1">http://goit-j8-group10-final.herokuapp.com/api/departments/1</a></p>
    <p>To save employee use api/departments/, pass department as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/departments/{departmentId} and RequestMethod.DELETE</p>
<br>
    <p>Get all events:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/events/">http://goit-j8-group10-final.herokuapp.com/api/events/</a></p>
    <p>Get specified event by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/events/1">http://goit-j8-group10-final.herokuapp.com/api/events/1</a></p>
    <p>To save employee use api/events/, pass event as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/events/{eventId} and RequestMethod.DELETE</p>
<br>
    <p>Get all eventTypes:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/eventTypes/">http://goit-j8-group10-final.herokuapp.com/api/eventTypes/</a></p>
    <p>Get specified event type by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/eventTypes/1">http://goit-j8-group10-final.herokuapp.com/api/eventTypes/1</a></p>
    <p>To save employee use api/eventTypes/, pass event type as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/eventTypes/{eventTypeId} and RequestMethod.DELETE</p>
<br>
    <p>Get all positions:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/positions/">http://goit-j8-group10-final.herokuapp.com/api/positions/</a></p>
    <p>Get specified position by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/positions/1">http://goit-j8-group10-final.herokuapp.com/api/positions/1</a></p>
    <p>To save employee use api/positions/, pass position as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/positions/{positionId} and RequestMethod.DELETE</p>
<br>
    <p>Get all salaries:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/salaries/">http://goit-j8-group10-final.herokuapp.com/api/salaries/</a></p>
    <p>Get specified salary by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/salaries/1">http://goit-j8-group10-final.herokuapp.com/api/salaries/1</a></p>
    <p>To save employee use api/salaries/, pass salary as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/salaries/{salaryId} and RequestMethod.DELETE</p>
<br>
    <p>Get all statuses:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/statuses/">http://goit-j8-group10-final.herokuapp.com/api/statuses/</a></p>
    <p>Get specified status by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/statuses/1">http://goit-j8-group10-final.herokuapp.com/api/statuses/1</a></p>
    <p>To save employee use api/statuses/, pass status as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/statuses/{statusId} and RequestMethod.DELETE</p>
<br>
    <p>Get all users:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/users/">http://goit-j8-group10-final.herokuapp.com/api/users/</a></p>
    <p>Get specified user by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/users/1">http://goit-j8-group10-final.herokuapp.com/api/users/1</a></p>
    <p>To save employee use api/users/, pass user as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/users/{userId} and RequestMethod.DELETE</p>
<br>
    <p>Get all roles:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/roles/">http://goit-j8-group10-final.herokuapp.com/api/roles/</a></p>
    <p>Get specified role by id:</p>
    <p><a href="http://goit-j8-group10-final.herokuapp.com/api/roles/1">http://goit-j8-group10-final.herokuapp.com/api/roles/1</a></p>
    <p>To save employee use api/roles/, pass role as parameter  and RequestMethod.POST</p>
    <p>To delete employee use api/roles/{roleId} and RequestMethod.DELETE</p>
</body>

</html>