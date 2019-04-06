<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<%-- WEB-INF/views/user/createForm.jsp --%>
<body>
    <form:form modelAttribute="userForm" method="post"
        action="${pageContext.request.contextPath}/user/create">
        <form:label path="name">Name:</form:label>
        <form:input path="name" />
        <form:errors path="name" /><%--(1) --%>
        <br>
        <form:label path="email">Email:</form:label>
        <form:input path="email" />
        <form:errors path="email" />
        <br>
        <form:label path="age">Age:</form:label>
        <form:input path="age" />
        <form:errors path="age" />
        <br>
        <form:button name="confirm">Confirm</form:button>
    </form:form>
</body>
</html>