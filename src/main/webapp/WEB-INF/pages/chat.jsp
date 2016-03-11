<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <style>
        .block {
            border-color: green;
            border-style: dashed;
            padding: 10px;
        }
    </style>
</head>
<body>
<h1>Chat page</h1>

<div class="block">
    <c:if test="${not empty messages}">
        <c:forEach var="message" items="${messages}">
            <div><span style="color: chocolate">${message.author}: </span>${message.text}</div>
        </c:forEach>
    </c:if>
</div>
<br/>
<br/>

<form:form method="post" action="/" commandName="messageForm">
    <label for="author">Author name:</label><br/>
    <form:input id="author" path="author" placeholder="type author"/>
    <form:errors path="author" cssStyle="color: #ff0000;"/>
    <br/>
    <br/>
    <label for="text">Message text:</label><br/>
    <form:textarea id="text" path="text" placeholder="type message"/>
    <form:errors path="text" cssStyle="color: #ff0000;"/>
    <br/>
    <br/>
    <input type="submit" value="send"/>
</form:form>
</body>
</html>