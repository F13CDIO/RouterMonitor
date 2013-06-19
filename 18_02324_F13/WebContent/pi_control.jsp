<%@include file="./includes/top.jsp" %>

<%
	Object pi_response;

	pi_response = session.getAttribute("pi_response");
	if(pi_response != null) {
	    out.println("<p id=\"pi_response\">" + pi_response + "</p>");
	    request.getSession().removeAttribute("pi_response");
	}
%>    
  
<br />
<span id="pi_title">Available Pi's:</span>
Change channel:
<form action="./pi_action.jsp" method="post">
	<div id="pi_list">

<!-- Includes .jsp with list of Pi's -->
<%@include file="./includes/pi_list.jsp" %>
<!-- Included .jsp list end -->

	</div>
	<div id="pi_channel_box">
	    To change channel for the selected Pi, you need to select the channel from the list below
	    <br /><br />
	    <select name="channel">
	        <option value="1">Channel 1</option>
	        <option value="2">Channel 2</option>
	        <option value="3">Channel 3</option>
	        <option value="4">Channel 4</option>
	        <option value="5">Channel 5</option>
	        <option value="6">Channel 6</option>
	        <option value="7">Channel 7</option>
	        <option value="8">Channel 8</option>
	        <option value="9">Channel 9</option>
	        <option value="10">Channel 10</option>
	        <option value="11">Channel 11</option>
	        <option value="12">Channel 12</option>
	        <option value="13">Channel 13</option>
	        <option value="36">Channel 36</option>
	        <option value="40">Channel 40</option>
	        <option value="44">Channel 44</option>
	        <option value="48">Channel 48</option>
	    </select>
	</div>

	<div class="clear"></div>

	<input class="pi_control_submit" type="submit" name="action" value="Start" />
	<input class="pi_control_submit" type="submit" name="action" value="Stop" />
	<input class="pi_control_submit" type="submit" name="action" value="Iterate channels" />
	<input class="pi_control_submit" type="submit" name="action" value="Scan networks" />
	<input class="pi_control_submit pi_submit_last" type="submit" name="action" value="Set channel" />
</form>

<script>
	/* Function to get new data and insert it into the list */
	function updatePiList() {
		$.get("./includes/pi_list.jsp", function(data) {
			document.getElementById("pi_list").innerHTML=data;
		});
	}
	
	window.setInterval(updatePiList, 5000);
	
</script>

<%@include file="./includes/bottom.jsp" %>