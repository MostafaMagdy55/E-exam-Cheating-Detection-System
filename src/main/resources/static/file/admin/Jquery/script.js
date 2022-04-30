let ctx = document.getElementById('myChart').getContext('2d');
let labels = ['Student', 'Course', 'Instrucor'];
let colorHex = ['#026FE5', '#19839A', '#BB7C45'];

let myChart = new Chart(ctx, {
  type: 'pie',
  data: {
    datasets: [{
      data: [123, 32, 19],
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
})
