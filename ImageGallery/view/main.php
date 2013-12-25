<!DOCTYPE html>
<?php
	session_start();
	if (isset($_SESSION['id'])){ 
?>
<html>
	<head>
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" data-angle='stylesheet' href="css/bootstrap-arrows.css">
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		<!--script src="angular.min.js"></script-->
		<link rel="stylesheet" type="text/css" href="<?php echo basename(__FILE__,'.php') . '.css' ?>">
		<script type="text/javascript" src="<?php echo basename(__FILE__, '.php') . '.js' ?>" ></script>
	</head>
	<body>
		<div id="container">
			<div id="main_content">
				
			</div>
			
			<div id="errors"> 
				<?php
				if (isset($_GET['upload'])){
					if ($_GET['upload'] == 'failed')
						echo "<p id='failed'> The file uploading has failed. Please try again. </p>";
					if ($_GET['upload'] == 'exists')
						echo "<p id='exists'>This image already exists here.</p>";
					if ($_GET['upload'] == 'success')
						echo "<p id='success'>Image has been uploaded.</p>";
				} 
				?>
			</div>
			
			<div id="second">
				<section>
					<span id='arrow' class='arrow'></span>
					<div id="picture_rows">
				
					</div>   
					<span id='arrow2' class='arrow' data-angle='180'></span>
				</section>
			</div>
		</div>
		
		<button id="uploader" class="btn btn-default">Upload image</button>
		<div id="upload_files">
			<form action="../controller/upload.php" method="POST" enctype="multipart/form-data">
				<label for="file">Filename:</label>
				<input type="file" name="file" id="file"><br>
				<input type="submit" name="submit" value="Submit">
			</form>
		</div>
	</body>
	
	<script type="text/javascript" src="js/bootstrap-arrows.min.js"></script>
</html>
<?php } ?>