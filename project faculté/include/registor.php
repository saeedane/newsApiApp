<?php 

 include_once('config.php');


if(isset($_POST["submit"])){
    
    $username = strip_tags($_POST["username"]);
    $password = $_POST['password']; 
    $confirm_pass = $_POST["pass_confirm"];
    $email = $_POST["email"];
    $gander = $_POST["gender"];
    $about = strip_tags($_POST["about"]);
    $date = date("Y-m-d H:i:s");
    $facebook= $_POST["facebook"];
    $google = htmlspecialchars($_POST["google"]);
    $twitter = htmlspecialchars($_POST["twitter"]);
    $youtube = htmlspecialchars($_POST["youtube"]);
    
    if(empty($username)){
        
        echo"<div class='alert alert-danger' role='alert'>لا تترك  حقل اسم مستخدم فارغ   </div> ";
    }else if(empty($email)){
        
        echo"<div class='alert alert-danger' role='alert'> لا تترك حقل البريد فارغ     </div> ";

        
    }else if(!filter_var($email,FILTER_VALIDATE_EMAIL)){
        
        echo"<div class='alert alert-danger' role='alert'> يجب كتابة بريد الكتروني صحيح      </div> ";

        
    }elseif(empty($password)){
        
        echo"<div class='alert alert-danger' role='alert'> لا تترك حقل كلمة السر فارغ   </div> ";

        
    }elseif(empty($confirm_pass)){
        
        echo"<div class='alert alert-danger' role='alert'> الرجاء تأكيد كلمة السر فارغ   </div> ";

        
    }elseif( $password != $confirm_pass){
        
        echo"<div class='alert alert-danger' role='alert'> كلمة السر غير متطابقة    </div> ";

        
    }else{
        
        $sql_username = mysqli_query($conn,"SELECT name from users where name = '$username'");
        $sql_email = mysqli_query($conn,"SELECT email from users where email = '$email'");
        
        if(mysqli_num_rows($sql_username) >0){
            
                    echo"<div class='alert alert-danger' role='alert'> الاسم  مسجل سابقا    </div> ";

            
        }elseif(mysqli_num_rows($sql_email) >0){
            
                    echo"<div class='alert alert-danger' role='alert'> البريد الكتروني مسجل سابقا    </div> ";

            
        }else{
            
            if(isset($_FILES['avater'])){
          

                $feature = $_FILES["avater"];
                $image_name = $feature["name"];
                $image_tmp  = $feature["tmp_name"];
                $image_error= $feature["error"];
                $image_size = $feature["size"];

                
                $image_exe = explode(".",$image_name);
                $image_exe = strtolower(end($image_exe));
                
                
                $allow = ["png","jpg","jpej","gif"];
                
                if(in_array($image_exe,$allow)){
                    
                    if($image_error == 0){
                        
                        if($image_size <= 3000000){
                            
                            $image_new_name = uniqid("user",false) ."." . $image_exe;
                            $image_path_new = "../images/user/" . $image_new_name;
                            $image_db = "images/user/" . $image_new_name;
                            
                            
                        if(move_uploaded_file($image_tmp,$image_path_new)){
                            

                            $pass = md5($_POST["password"]);
                            
                            

                            
                            
                      


                    $sql = "INSERT INTO users (
                                                'name',
                                                'password',
                                                'confirm_pass',
                                                'email',
                                                'gander',
                                                'avater',
                                                'about',
                                                'facebook',
                                                'google',
                                                'twitter',
                                                'youtube',
                                                'date',
                                                'role')
                                                VALUES 
                                                (
                                                '$username',
                                                '$pass', 
                                                '$confirm_pass',
                                                '$email',
                                                '$gander',
                                                '$image_db',
                                                '$about',
                                                '$facebook',
                                                '$google',
                                                '$twitter',
                                                '$youtube',
                                                '$date',
                                                'user')";
                            
                      $insert = mysqli_query($conn,$sql); 
                            
                            if(isset($insert) == 0){
                                
                            echo"<div class='alert alert-danger' role='alert'> تم تسجيل بنجاح   </div> ";
  
                                
                            }else{
                                
                           echo"<div class='alert alert-success' role='alert'> تم تسجيل بنجاح   </div> ";

                                
                            }


                            
                           
                      
                     
                            
                            
                            
                        }
                            
                            
                            
                            
                        }else{
                            
                        echo"<div class='alert alert-danger' role='alert'> حجم الصورة كبير جدا الحجم مناسب 2 mb    </div> ";

                            
                        }
                        
                        
                    }else{
                        
                  echo"<div class='alert alert-danger' role='alert'> حدث خطأ غير متوقع    </div> ";
    
                        
                    }
                    
                    
                    
                    
                }else{
                    
                  echo"<div class='alert alert-danger' role='alert'> الرجاء اختيار صورة صحيح    </div> ";
  
                }
                
                
                
                
                
                
            }else{
                
                // insert 
                
                
            }
            
            
            
        }
      

            
            
             
            
            
        
        
    }
    
    
}


?>