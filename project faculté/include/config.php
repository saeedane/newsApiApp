<?php

$host = "localhost";
$username = "root";
$password = "";
$db_name = "ecole";


$conn = mysqli_connect($host,$username,$password,$db_name);

mysqli_set_charset($conn,'utf-8');


if(!$conn){
    echo mysqli_error('mysql error connect ') . mysqli_errno();
}





?>