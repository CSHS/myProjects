<!DOCTYPE HTML>

<html>
<head>
<title> Image Gallery </title>
<link rel="stylesheet" type="text/css" href="<?php echo basename(__FILE__,'.php') . '.css' ?>">
<link rel="stylesheet" type="text/css" href="<?php echo basename('bottom','.php') . '.css' ?>">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
<div id="log_in">
	<h1 id="title">Image Gallery</h1>
	<form method="POST" id="login" class="form-inline">
		<div class="form-group">
			<input type="text" name="username" placeholder="Your Username" id="username">
			<input type="password" name="password" placeholder="Enter Your Password" id="password">
			<button type="submit" id="form-login" class="btn btn-default">Login</button>
		</div>
		<p id="error"></p>
	</form>
</div>

<div id="image_text"> <!--style="background-image: url(road.png); height: 500px"-->
	<img id="image" src="road.png">
	<div id="incentives">
		<h1><strong>
			&nbsp More storage <br>
			 &nbsp &nbsp &nbsp In the convenience of your home  <br>
			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp For your lovely photos and images. <br></strong>
		</h1>
		<h1 id="motto">
			<b>We'll help you get there.</b>
		</h1>
	</div>
	<div id="signup">
		<form class="form-horizontal" role="form" method="POST" action="../controller/register.php">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-1 control-label">Username</label>
				<div class="col-sm-4">
					<input type="email" class="form-control" id="inputEmail3" placeholder="Username">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-1 control-label">Password</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="inputPassword3" placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default btn-lg">Sign up!</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div id="more_info">

</div>


<?php include('bottom.php') ?>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<?php echo basename(__FILE__, '.php') . '.js' ?>" ></script>
</body>
</html>