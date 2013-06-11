<%
    String pageName, pageNameNice, path, absolutePath;
    
    /* Get the name of the page to include */
    pageName = request.getParameter("page");
    
    /* Check if pageName is set, and sets to "Home" if it isn't */
    if(pageName == null) {
        pageName = "home";
    }
    else {
        pageName = pageName.toLowerCase();
    }
    
    /* Check if file exists and replaces with "File not found"-page if it doesn't*/
    path = "./contents/" + pageName + ".jsp";
    absolutePath = getServletContext().getRealPath(path);
    java.io.File file = new java.io.File(absolutePath);
    
    if (!file.exists()) {
        pageName = "file_not_found";
    }
    
    /* Generates a nice looking pagename for title etc. */
    char[] temp = pageName.toCharArray();
    temp[0] = Character.toUpperCase(temp[0]);
    pageNameNice = new String(temp);
    
    pageNameNice = pageNameNice.replace('_', ' ');
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - <% out.print(pageNameNice); %></title>
        <link rel="stylesheet" type="text/css" href="./styles/main.css" />
        
        <jsp:useBean id="CustomCharts" class="server.function.CustomCharts" /> 
        <% out.print(CustomCharts.importScripts()); %>
    </head>

    <body>
        <div id="website_container">
            <div id="banner">
                <h1 id="head">Network Monitoring System</h1>
                <a href="./" class="menu_link menu_link_first<% if(pageName.equals("home")){out.print(" menu_link_active");} %>">Home</a>
                <a href="./?page=statistics" class="menu_link<% if(pageName.equals("statistics")){out.print(" menu_link_active");} %>">Statistics</a>
                <a href="./?page=live_traffic" class="menu_link<% if(pageName.equals("live_traffic")){out.print(" menu_link_active");} %>">Live traffic</a>
                <a href="./?page=create_user" class="menu_link<% if(pageName.equals("create_user")){out.print(" menu_link_active");} %>">Create user</a>
                <a href="./?page=login" class="menu_link<% if(pageName.equals("login")){out.print(" menu_link_active");} %>">Login</a>
            </div>
            <div id="main">
                <h2 id="title"><% out.println(pageNameNice); %></h2>
                <% pageContext.include(path); %>
            </div>
            <div id="bottom">
            </div>
        </div>
    </body>

</html> 