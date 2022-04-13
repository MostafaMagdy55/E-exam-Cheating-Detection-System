$(function() {

    var $quote = $("h4");
    
    var $numWords = $quote.text().split(" ").length;
    
    if (($numWords <= 26) ) {
        $quote.css("font-size", "1.7vw");
    }
    else {
        $quote.css("font-size", "1vw");
    }    
    
});