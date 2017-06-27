<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="kanban">
	<c:forEach items="${kanban.categories}" var="category">
		<div class="category">
			<h2>${category.name}</h2>
			<c:forEach items="${category.tasks}" var="task">
				<div class="task">
					<h3>${task.title}</h3>
					<p>${task.description}</p>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	<div class="clearFloat"></div>
</div>
