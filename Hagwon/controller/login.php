<?php
session_start();
include_once('../model/user.php');
$username = $_POST['username'];
$password = $_POST['password'];
$set = $_POST['set'];

$us = new User();

if ($set == 'login'){
	$returner = $us -> retrieve($username);
	if (count($returner) > 0){
		$_SESSION['id'] = $returner[0];
		echo json_encode(['login' => 'success']);
	}
	else{
		echo json_encode(['login' => 'Unable to login.  Please try again.']);
	}
	return;
}



?>