<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="parse.XMLParser, java.util.ArrayList, java.util.Iterator" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Statement Processor</title>
<link rel="stylesheet" href="display.css">
</head>
<body>

<% 
ArrayList<Integer> failedReference=null;
ArrayList<String> failedDescription=null;
try { XMLParser xp = new XMLParser(); 
failedReference = new ArrayList();
failedReference = xp.failedReference;
failedDescription = new ArrayList();
failedDescription = xp.failedDescription;
} catch(Exception e) { System.out.println("e"+e);}
%>
<h3>Failed Records</h3>
 <table>
 <th>
  <span >Transaction Reference</span>
 </th>
<th> <span>Description</span></th>

 <% Iterator k = failedReference.iterator();
 int n=0;%>
 <% while(k.hasNext()) { %>
		<% Integer m=(Integer)k.next();%>
		 <tr>
		 <td>
		<%= m %>
		</td>
		 <td>
		 <% String s= failedDescription.get(n);n++; %>
		<%= s %>
		</td>		
		</tr>
	<% } %>
	
	 </table>
</body>
</html>