$(document).ready(function()
{
    /*to make the text field able to hide */
    $('input[type="radio"]').click(function(){
        var inputvalue = $(this).attr("value");

        if (inputvalue == "1")
        {

            $('.correctanswer').hide(200);
            $('.wronganswer1').hide(200);
            $('.wronganswer2').hide(200);
            $('.wronganswer3').hide(200);
            $('.droplistclass').hide(200);
            $('.Answers').show(200);
            $('.button').addClass('buttonaddedclasstotrueandfalse');
            $('.button').removeClass('buttonaddedclasstoMCQ');
            $('.Answers').addClass('Answersaddedclass');

        }
        else if (inputvalue == "0")
        {
            $('.Answers').hide(200);
            $('.correctanswer').show(230);
            $('.wronganswer1').show(230);
            $('.wronganswer2').show(230);
            $('.wronganswer3').show(230);
            $('.droplistclass').show(230);
            $('.button').addClass('buttonaddedclasstoMCQ');
            $('.button').removeClass('buttonaddedclasstotrueandfalse');
        }
    });
});


    