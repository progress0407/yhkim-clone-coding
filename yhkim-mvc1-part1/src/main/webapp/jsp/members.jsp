<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>

<%
  MemberRepository memberRepository = MemberRepository.getInstance();
  List<Member> members = memberRepository.findAll();
  response.setContentType("text/html");
  response.setCharacterEncoding("utf-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
<thead>
<th>id</th>
<th>username</th>
<th>age</th>
</thead>
<tbody>
<%
  for(Member member : members) {
    %>
    <tr>
      <td><%=member.getId()%></td>
      <td><%=member.getUsername()%></td>
      <td><%=member.getAge()%></td>
    </tr>
<%
  }
%>
</tbody>
</table>
</body>
</html>