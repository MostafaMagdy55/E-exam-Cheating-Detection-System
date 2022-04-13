var responsiveImages = document.querySelectorAll('.responsive-image');
var responsiveImageList = [];


for(var i = 0; i < responsiveImages.length; i++) {
  responsiveImageList.push(ResponsiveImage(responsiveImages[i]));
}

function ResponsiveImage(elem) {
  window.addEventListener('resize', changeImageSize);
  changeImageSize();
  
  function changeImageSize() {
    if(outerWidth > 1400) {
      elem.style.backgroundImage = 'url(' + elem.dataset.imgLarge + ')';
    } else if(outerWidth > 1024) {
      elem.style.backgroundImage = 'url(' + elem.dataset.imgMedium + ')';
    } else if(outerWidth > 320) {
      elem.style.backgroundImage = 'url(' + elem.dataset.imgSmall + ')';
    }
  }
}