<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>What Should I Eat?</title>
    <style>
        .main_container { text-align: center; margin-top: 50px; }
        .meal-image { width: 300px; border-radius: 8px; }
    </style>
</head>
<body>
<div class="main_container">
    <h1>What Should I Eat?</h1>
    <form action="getMeal" method="get">
        <input type="submit" value="Get Random Meal"/>
    </form>

    <c:if test="${not empty meal}">
        <div class="meal-box">
            <h2>${meal.strMeal}</h2>
            <br/>
            <img src="${meal.strMealThumb}" alt="Meal Image" class="meal-image"/>
            <br/><br/>
            <a href="${not empty meal.strSource ? meal.strSource : meal.strYoutube}" target="_blank">
                View Recipe Instructions
            </a>
        </div>
    </c:if>
</div>
</body>
</html>