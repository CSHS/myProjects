<?php
session_start();
include_once('../model/picture.php');
//ini_set("allow_url_fopen", true);
//ini_set("allow_url_include", true);
//$data = file_get_contents("php://input");
//$objData = json_decode($data);
$mode = $_GET['mode'];
$pic = new Picture();
//error_log('hello');
//ini_set('memory_limit', '-1');
if ($mode=='get'){
	$arr = $pic -> retrieve($_SESSION['id']);
	//error_log(count($arr));
	 $data = array();
	 while ($row = mysqli_fetch_assoc($arr)){
	//while ($row = $arr){
		// error_log($row[0]);
		 $data[] = $row['url'];
	 }
	 echo json_encode($data);
 }


?>