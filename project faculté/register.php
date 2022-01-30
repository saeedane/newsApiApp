<?php include_once("include/header.php");?>
        
        
        <div class="row">
        
        
            
            <div class="col-md-8 register">
            
                <article>

                  <form  action="include/registor.php" method="post" id="regiter" enctype="multipart/form-data" >
                    
                    
                <div class="note">
                    <p>جميع الحقول المشار اليها بعلامة (*) مطلوبة </p>
                </div>

                <div class="form-content">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="اسم المستخدم  *" value="" name="username"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder=" كلمة السر *" value="" name="password"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="  البريد الكتروني *" value="" name="email"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="اعادة كلمة السر  *" value="" name="pass_confirm"/>
                            </div>
                        </div>
                        
                                <div class="col-md-6">
                            <div class="form-group">
                          <select class="form-control form-control-xs" name="gender">
                            <option>ذكر </option>
                            <option>أنثى </option>
                            </select>
                          
                        
                            </div>

                        </div>
                        
                        
                            <div class="col-md-6">
                            <div class="form-group">
                            <input type="file" class="form-control-file" id="exampleFormControlFile1" name="avater">
                        
                            </div>

                        </div>
                        
                                    
                            <div class="col-md-12">
                            <div class="form-group">
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="وصف عن " name="about"></textarea>
                        
                            </div>

                        </div>
        

                                <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="  فيسبوك *" value="" name="facebook"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder=" جوجل *" value="" name="google"/>
                            </div>
                        </div> 
                        
                        
                                           <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="  تويتر  *" value="" name="twitter"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder=" يوتيوب *" value="" name="youtube"/>
                            </div>
                        </div> 
                        

                        
                        <div class="col-md-12">
                            
                        <div class="text-center" id="resualt">

                            </div>
                        
                        </div>
                        
                                                        <div class="col-md-12 text-center">
                            <div class="form-group mt-4">
                               <button class="btn btn-primary" type="submit" name="submit"> انشاء حساب جديد </button>
                            </div>
                          
                        </div> 
                        
                        
                        
                    </div>
                   
                </div>
                            </form>
                          
                                  
             
                    
              
                </article>
            
                
            </div>
            
            <?php include_once("include/sidebar.php");?>


        
        </div>
    
    
    
    
<?php include_once("include/footer.php");?>