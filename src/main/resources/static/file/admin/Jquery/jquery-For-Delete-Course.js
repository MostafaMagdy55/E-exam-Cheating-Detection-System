$(document).ready(function()
{ 
	/*to open menu in course */
	$(".sub-btn").click(function()
	{
                $(".sub-btn").next('.sub-menu').slideToggle();
                $(".sub-btn").find('.dropdown').toggleClass('rotate');
    });

    $(".sub-btnFor-member").click(function()
	{
                $(".sub-btnFor-member").next('.sub-menuFor-Member').slideToggle();
                $(".sub-btnFor-member").find('.dropdown').toggleClass('rotate');
    });
    $(".sub-btnFor-addmember").click(function()
    {
                $(".sub-btnFor-addmember").next('.sub-menuFor-addMember').slideToggle();
                $(".sub-btnFor-addmember").find('.dropdown').toggleClass('rotate');
    });
	/*to open slide bar  */
    $('.menu-icons').click(function()
    {
        $('.side-bar').addClass('active');
        $('.the-center-div').addClass('the-center-divaddedClass');
        $('.navbar').addClass('navbaraddclass');
        $('.search_textfield').addClass('search_textfieldaddedclass');
        $('.img-container').addClass('img-containeraddclass');
        $('.change-img').addClass('change-imgaddedclass');
        $('.menu-icons').css("visibility","hidden");
        $('.logout').addClass('logoutaddclass');
        $('.add').addClass('addclass');
        $('.closetable').addClass('opentable');
        $('.closetbody').addClass('opentbody');
        $('.search_textfield').addClass('addsearch_textfield');

       
    });

	/*to close slide bar */
    $('.close-btn').click(function()
    {
        $('.side-bar').removeClass('active');
         $('.the-center-div').removeClass('the-center-divaddedClass');
        $('.navbar').removeClass('navbaraddclass');
        $('.search_textfield').removeClass('search_textfieldaddedclass');
         $('.img-container').removeClass('img-containeraddclass');
          $('.change-img').removeClass('change-imgaddedclass');
        $('.menu-icons').css("visibility","visible");
        $('.logout').removeClass('logoutaddclass');
        $('.add').removeClass('addclass');
        $('.closetable').removeClass('opentable');
        $('.closetbody').removeClass('opentbody');
        $('.search_textfield').removeClass('addsearch_textfield');



    });

   $('.Delete').on('click', function(){
        if (confirm('Are You Sure To Delete This Cousre'))
        {    
        $(this).closest('tr').remove();
        }
        else
        {
            return false ;
        }
      });
});