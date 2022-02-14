<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List View</title>
</head>
<body>
 
    <div>
        <H1>DB 연동 확인</H1>
        <table border="1" align ="center">
            <thead>
                <tr>
                    <th>IDX</th>
                    <th>NAME</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${fn:length(AllList) > 0}">
                        <c:forEach items="${AllList}" var="AllList">
                            <tr>
                                <td>${AllList.IDX}</td>
                                <td>${AllList.NAME}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="4">조회된 결과가 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
 
</body>
</html>
