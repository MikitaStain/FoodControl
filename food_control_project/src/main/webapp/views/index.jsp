<html lang = "en" xmlns:th="http://thymeleaf.org">

<head>
    <title>All people</title>
</head>
<body>

<div th:each="person : ${people}">
    <a th:href="@{/api/products/{id}(id=${products.getId()})}"
       th:text="${person.getName() + ', ' + person.getAge()}">user</a>
</div>

<br/>
<hr/>
<a href="/people/new">Create new person</a>


</body>
</html>
