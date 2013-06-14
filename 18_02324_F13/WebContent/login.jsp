<%@include file="./includes/top.jsp" %>

<br />
<form action="j_security_check" method="post">
    <div class="form_description">Username:</div>
    <div class="form_input"><input size="70" name="j_username" type="text" /></div>
    <div class="form_description">Password:</div>
    <div class="form_input"><input size="70" name="j_password" type="password" /></div>
    
    <input class="form_submit" type="submit" value="login" />
</form>

<div class="clear"></div>
<br /><br />

Herunder er angivet testbrugere med forskellige rettigheder, som kan bruges til test...<br />
<br />
Admin:<br />
Username = hans<br />
Password = 1212<br />
<br />
User:<br />
Username = flemming<br />
Password = 1313<br />

<%@include file="./includes/bottom.jsp" %>