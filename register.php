<?php
//require "init.php"
$db_name="fkc";
$mysql_user="root";
$server_name="localhost";

$connection=mysqli_connect($server_name,$mysql_user,"",$db_name);
if(!$connection)
{echo "fkc";
}
//$rollNo=$_POST['rollNo'];
$rollNo = isset($_POST['rollNo']) ? $_POST['rollNo'] :'01';
$name = isset($_POST['name']) ? $_POST['name'] : '01';
$branch = isset($_POST['branch']) ? $_POST['branch'] : '01';
$shift = isset($_POST['shift']) ? $_POST['shift'] : '01';

$sql_query="insert into fkcu values('$rollNo','$name','$branch','$shift');";

if (mysqli_query($connection,$sql_query)) {
echo "inserted";
}
 else {
echo "not inserted";
}

?>
