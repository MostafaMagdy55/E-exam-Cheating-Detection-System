const Student=document.querySelector('.St');
const Course=document.querySelector('.Cou');
const Instructor=document.querySelector('.Inst');
const Exams=document.querySelector('.Exa');

let ctx = document.getElementById('myChart').getContext('2d');
let labels = ['Student', 'Course', 'Instrucor', 'Exams'];
let colorHex = ['#026FE5', '#19839A', '#BB7C45','#32911f'];
let items= [Student,Course,Instructor,Exams];

let myChart = new Chart(ctx, {
  type: 'pie',
  data: {
    datasets: [{
      data:  items
      /*function great(S,C,I,E){
        document.write(S,C,I,E)
      }
      [123, 32, 19,29]*/,
      backgroundColor: colorHex,
      borderColor: '#292F45',
      borderWidth: 0.1,
    }],
    labels: labels,
    
  },
  options: {
     responsive: true,
    legend: {
      position: 'right'
    },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'start', 
        offset: -10,
        borderWidth: 2,
        borderColor: '#fff',
        borderRadius: 25,
        backgroundColor: (context) => {
          return context.dataset.backgroundColor;
        }, 
        font: {
          weight: 'bold',
          size: '10'
        },
        formatter: (value) => {
          return value + ' %';
        }     
        
      }
    }
  }
});

/*const updateChartValue=(input,dataorder)=>{
  input.addEventListener('change', e =>{
     myChart.data.datasets[0].data[dataorder] = e.target.value;
     myChart.update();
  });
};

updateChartValue(Student,0);
updateChartValue(Course,1);
updateChartValue(Instructor,2);
updateChartValue(Exams,3);*/