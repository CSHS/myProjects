<?php

class Picture
{
	private $con;

	function __construct()
	{
		include_once('config.php');
	    $this -> con = mysqli_connect(WEB,NAME,PASS,DB);
		//$this -> con = mysqli_connect('localhost/view','root','hello','hagwon');
	}
	
	public function update($pic_url,$u_id)
	{
		$returner = mysqli_query($this -> con, "INSERT INTO pictures (url,u_id) VALUES ('images/" . $pic_url . "'," . $u_id . ")");
		return $returner;
	}
	
	public function retrieve($u_id)
	{
		$result = mysqli_query($this -> con, 'SELECT url FROM pictures WHERE u_id="'.$u_id.'"');
		//$returner = mysqli_fetch_array($result, MYSQLI_NUM);
		//error_log(count($returner));
		//return $returner;
		return $result;
	}
}

?>