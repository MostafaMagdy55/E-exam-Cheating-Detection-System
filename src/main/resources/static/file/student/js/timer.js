var skip = document.getElementById('submit');
var countdown =document.getElementById('countdown');
var count =0 ;
var duration =0 ;
var qa_set = document.querySelectorAll('.qa-set')
var qa_ans = document.querySelectorAll('.qa-set .qa-ans-set input')


skip.addEventListener("click", function(){
	step()
});

function step() {
	count +=1;
	for (var i = 0; i < qa_set.lenght; i++)
	{
		qa_set[i].className ='qa-set';
	}
	qa_set[count].className ='.qa-set active';
	if (count == 4) {
		skip.style.display = 'none';
	}