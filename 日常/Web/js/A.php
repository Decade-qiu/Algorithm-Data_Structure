<?php
  $file = "‪E:\DeskTop\1.png"; // 文件路径 
  if($fp = fopen($file, "rb", 0)) { 
    $binary = fread($fp, filesize($file)); // 文件读取
    fclose($fp); 
    $base64 = base64_encode($binary); // 转码
    echo $base64; // 显示base64码
  }   
?>