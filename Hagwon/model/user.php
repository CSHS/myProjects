<?php

class User
{
	private $con;

	function __construct()
	{
		include_once('config.php');
	    $this -> con = mysqli_connect(WEB,NAME,PASS,DB);
		//$this -> con = mysqli_connect('localhost/view','root','hello','hagwon');
	}
	
	public function update($username, $password)
	{
		$returner = mysqli_query($this -> con, "INSERT INTO users (username, password) VALUES ('".$username."', '".$password."')");
		return $returner;
	}
	
	public function retrieve($username)
	{
		$result = mysqli_query($this -> con, 'SELECT id,username FROM users WHERE username="'.$username.'"');
		$returner = mysqli_fetch_array($result, MYSQLI_NUM);
		return $returner;
		//return $result;
	}
}

?>