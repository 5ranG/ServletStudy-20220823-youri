<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	//선언식
	String name = "김준일";
%>

<%
	//스크립트릿
	List<String> nameList = new ArrayList<String>();

	nameList.add("김준일");
	nameList.add("조문기");
	nameList.add("박소영");
	nameList.add("서재효");
	nameList.add("신승한");
	nameList.add("이승환");
	nameList.add("최연호");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	
	table{
		border: 1px solid #141414;
		border-collapse: collapse;
	}
	
	th, td{
		border: 1px solid #141414;
		width: 80px; height: 40px;
		text-align: center;
	}
</style>
<body>
	<h1>hello jsp!</h1>
	<label>이름: </label><%= name %>
	
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
		</tr>
<%
		for(int i = 0; i <nameList.size(); i++){
%>		
		<tr>
			<td> <%= i + 1 %> </td>
			<td> <%=nameList.get(i) %> </td>
		</tr>
<%
		}
%>
	</table>
	
	<%-- 
	주석 단축키 ctrl + shift + /
	<%
		for(int i = 0; i <10; i++){
	%>
			<label>이름: </label><%= name %><br>
	<%		
		}
	%>
	 --%>
</body>
</html>