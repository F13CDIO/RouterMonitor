<%
    String pageName, pageNameNice, userName, userType;
    int position;
    
    /* Get username */
    userName = request.getRemoteUser();
    
    /* Gets the name of the current file */
    pageName = request.getRequestURI();
    position = pageName.lastIndexOf("/");
    pageName = pageName.substring(position+1);
    
    /* If there is no filename, it must be because we are on the home-page */
    if(pageName.length() < 4 || "index.jsp".equals(pageName)) {
        pageName = "home.jsp";
    }
    
    /* Generates a nice looking pagename for title etc. */
    pageNameNice = pageName.substring(0, pageName.length()-4);
    char[] pageNameChar = pageNameNice.toCharArray();
    pageNameChar[0] = Character.toUpperCase(pageNameChar[0]);
    pageNameNice = new String(pageNameChar);
    pageNameNice = pageNameNice.replace('_', ' ');
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - <% out.print(pageNameNice); %></title>
        <link rel="stylesheet" type="text/css" href="./styles/main.css" />
        <link rel="icon" href="./favicon.ico" type="image/x-icon">
        
        <jsp:useBean id="CustomCharts" class="server.function.CustomCharts" /> 
        <% out.print(CustomCharts.importScripts()); %>
    </head>

    <body>
        <div id="website_container">
            <div id="banner">
                <h1 id="head">Network Monitoring System</h1>
                <a href="./" class="menu_link menu_link_first<% if("home.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Home</a>
                <a href="./statistics.jsp" class="menu_link<% if("statistics.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Statistics</a>
                <a href="./live_traffic.jsp" class="menu_link<% if("live_traffic.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Live traffic</a>
                <a href="./create_user.jsp" class="menu_link<% if("create_user.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Create user</a>

<%
/* Dynamic menu. Only show control-panel and logout tab if someone is logged in */
if(userName != null) { %>
	<a href="./logout.jsp" class="menu_link<% if("logout.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Logout</a>
	<a href="./control_panel.jsp" class="menu_link<% if("control_panel.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Control Panel</a>
<% }
else { %>
	<a href="./login.jsp" class="menu_link<% if("login.jsp".equals(pageName)){out.print(" menu_link_active");} %>">Login</a>
<% }
%>

            </div>
            <div id="main">
                <h2 id="title"><% out.print(pageNameNice); %></h2>