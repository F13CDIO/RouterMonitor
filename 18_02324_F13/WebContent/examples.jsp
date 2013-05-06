<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Jeg er en Boss!</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>

<body>
	<div id="container">
		<div id="header">
			<h1>
				<a href="home.jsp">Team_18</a>
			</h1>
			<h2>We rock!</h2>
			<div class="clear"></div>
		</div>
		<div id="nav">
			<ul>
				<li><a href="home.jsp">Home</a></li>
				<li class="selected"><a href="examples.jsp">Examples</a></li>
				<li><a href="contact.jsp">Contact</a></li>
			</ul>
		</div>
		<div id="body">
			<div id="content">
				<h1>Page Content</h1>
				<div class="box">
					<h2>Examples</h2>

					<h1>Heading H1</h1>
					<h2>Heading H2</h2>
					<h3>Heading H3</h3>
					<h4>Heading H4</h4>
					<h5>Heading H5</h5>


					<p>&nbsp;</p>


					<h3>Lists</h3>
					<ul>

						<li>List item</li>
						<li>List item</li>
						<li>List item</li>
					</ul>

					<ol>
						<li>List item</li>
						<li>List item</li>
						<li>List item</li>
					</ol>

					<p>&nbsp;</p>



					<h3>Code and blockquote</h3>
					<code>&lt;? echo('Hello world'); ?&gt;</code>

					<blockquote>
						<p>Mauris sit amet tortor in urna tincidunt aliquam.
							Pellentesque habitant morbi tristique senectus et netus et
							malesuada fames ac turpis egestas</p>
					</blockquote>


					<p>&nbsp;</p>



					<h3>Table</h3>

					<table cellspacing="0">
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Age</th>
						</tr>
						<tr>
							<td>1</td>
							<td>John Smith</td>
							<td>28</td>
						</tr>
						<tr>
							<td>2</td>
							<td>Fred James</td>
							<td>49</td>
						</tr>
						<tr>
							<td>3</td>
							<td>Rachel Johnson</td>
							<td>19</td>
						</tr>

					</table>

					<p>&nbsp;</p>

				</div>
			</div>

			<div class="sidebar">
				<ul>
					<li>
						<h4>
							<span>Navigate</span>
						</h4>
						<ul class="blocklist">
							<li><a href="home.jsp">Home</a></li>
							<li><a href="examples.jsp">Examples</a></li>
							<li><a href="contact.jsp">Contact</a></li>
						</ul>
					</li>
					<li>
						<jsp:include page="welcomeForm.jsp"></jsp:include>
					</li>
					<li>
						<h4>About</h4>
						<ul>
							<li>
								<p style="margin: 0;">Aenean nec massa a tortor auctor
									sodales sed a dolor. Duis vitae lorem sem. Proin at velit vel
									arcu pretium luctus.</p>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<div id="footer">
				<div class="footer-bottom">
					<p>&copy;Nu er det mit!</p>
				</div>
			</div>

		</div>
	</div>
</body>
</html>
