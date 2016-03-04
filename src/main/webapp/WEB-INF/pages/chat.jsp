<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<form method="post" action="/">
    <label for="author">Author name:</label><br/>
    <input type="text" id="author" name="author" placeholder="type author"/>
    <br/>
    <br/>
    <label for="text">Message text:</label><br/>
    <textarea type="text" id="text" name="text" placeholder="type message"></textarea>
    <br/>
    <br/>
    <input type="submit" value="send"/>
</form>
</body>
</html>