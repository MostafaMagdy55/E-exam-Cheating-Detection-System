$(document).ready(function()
{
 /*to make the text field able to edit it */
    $('.pen-name').click(function()
    {
         $('.Nametext').removeAttr('disabled');
         $('.Nametext').focus();            
    });
    $('.pen-email').click(function()
    {
         $('.Emailtext').removeAttr('disabled');
         $('.Emailtext').focus();
            
    });
    $('.pen-with-eye').click(function()
    {
         $('.Passwordtext').removeAttr('disabled');
         $('.Passwordtext').focus();
            
    });
    $('.eye-password').click(function()
    {
         $('.Passwordtext').attr('type','text');
         $('.Passwordtext').focus();
         $('.eye-password').css("visibility","hidden");
         $('.open-eye-password').css("visibility","visible");
            
    });
   $('.open-eye-password').click(function()
    {
         $('.Passwordtext').attr('type','Password');
         $('.Passwordtext').focus();
         $('.eye-password').css("visibility","visible");
         $('.open-eye-password').css("visibility","hidden");
            
    });


    $('.pen-phone').click(function()
    {
         $('.Phonetext').removeAttr('disabled');
         $('.Phonetext').focus();
            
    });


});