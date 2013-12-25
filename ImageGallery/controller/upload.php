<?php  //adapted from w3
session_start();
include_once('../model/picture.php');
$allowedExts = array("gif", "jpeg", "jpg", "png");
$temp = explode(".", $_FILES["file"]["name"]);
$extension = end($temp);
if ((($_FILES["file"]["type"] == "image/gif")
|| ($_FILES["file"]["type"] == "image/jpeg")
|| ($_FILES["file"]["type"] == "image/jpg")
|| ($_FILES["file"]["type"] == "image/pjpeg")
|| ($_FILES["file"]["type"] == "image/x-png")
|| ($_FILES["file"]["type"] == "image/png"))
&& in_array($extension, $allowedExts)) {
	if ($_FILES["file"]["error"] > 0) {
		header('Location: ../view/main.php?upload=failed');
	}
	else{
		if (file_exists("../view/images/" . $_FILES["file"]["name"])){
			header('Location: ../view/main.php?upload=exists');
		}
		else{
			$pic = new Picture();
			$arr = $pic -> update($_FILES["file"]["name"],$_SESSION['id']);
			move_uploaded_file($_FILES["file"]["tmp_name"],
				"../view/images/" . $_FILES["file"]["name"]);
			header('Location: ../view/main.php?upload=success');
		}
    }
}
else{
  header('Location: ../view/main.php?upload=failed');
}
?>