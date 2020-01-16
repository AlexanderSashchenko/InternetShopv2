<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Item</title>
</head>
<body>
<p>Hello Mates! New items are to be added here!</p><br />
    <form action="${pageContext.request.contextPath}/servlet/addItem" method="post">
        <label for="addItemTitle">Add Item Title</label>
            <input type="text" id="addItemTitle" name="title">

        <label for="addItemPrice">Add Item Price</label>
            <input type="text" id="addItemPrice" name="price">
        <br />
        <p><button type="submit">Add Item</button></p>
    </form>
<a href="${pageContext.request.contextPath}/servlet/allItems">View all items in the shop</a><br />
<a href="${pageContext.request.contextPath}/index">Back to the index page</a><br />
</body>
</html>
