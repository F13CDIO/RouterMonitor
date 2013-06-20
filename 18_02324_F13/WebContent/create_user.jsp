<%@include file="./includes/top.jsp" %>

<%	
	Object createMessage;

	createMessage = session.getAttribute("createMessage");
	if(createMessage != null) {
	    out.println("<p id=\"message\">" + createMessage + "</p>");
	    request.getSession().removeAttribute("createMessage");
	}
%>

<br />

<form action="./create_user_action.jsp" method="post">
    <div class="form_description">Email:</div>
    <div id="mail_input" class="form_input"><input size="70" name="mail" type="email" onchange="onChange(this.value);" onkeyup="this.onchange();" onpaste="this.onchange();" oninput="this.onchange();" /></div>
    <div id="live_check">...</div>
    <div class="form_description">Password:</div>
    <div class="form_input"><input size="70" name="password" type="password" /></div>  
    
    <input class="form_submit" type="submit" value="Create user" />
</form>

<script>
	var currentValue, isActive = false;

	function onChange(value) {
		currentValue = value;
		
		if(!isActive && isValidEmail(value)) {
			isActive = true;
			checkAvailable(value);
		}
		else {
			setInfo("...");
		}
	}
	
	function isValidEmail(input) {
		var array = input.split("@");
		
		if(array[1] == null) {
			return false;
		}
		
		if(!array[1].contains(".")) {
			return false;
		}
		
		array = array[1].split(".");
		if(array[1].length < 2) {
			return false;
		}
		
		return true;
	}

	function setInfo(input) {
		document.getElementById("live_check").innerHTML=input;
	}
	
	function checkAvailable(input) {
		$.get("./live_check_user.jsp?username="+input, function(data) {
			if(input == currentValue) {
				if(data == "true") {
					setInfo("Username is already taken.");
				}
				else if(data == "false") {
					setInfo("Username available.");
				}
				else {
					setInfo("...")
				}
			}
			else {
				setInfo("...");
			}
			
			isActive = false;
		});
	}
</script>

<%@include file="./includes/bottom.jsp" %>