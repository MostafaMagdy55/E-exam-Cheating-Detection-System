$("#MyButton").click(function (){
  var ID = document.getElementById('ID');
  var name = document.getElementById('name');
  var Email = document.getElementById('email');
  var pass = document.getElementById('pass');
  var photo= document.getElementById('photo');

  if(ID.value === '' || name.value === '' || Email.value === '' || pass.value === '' || photo.value === ''){
     alert("Some thing is Wrong");
  }
  else{
      /*setTimeout(() => {
          Name.value = '';
          email.value = '';
          msg.value = '';
      }, 2000);*/

      alert("successfully added");
  }
});

