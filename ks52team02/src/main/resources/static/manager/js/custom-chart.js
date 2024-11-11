(function ($) {
    "use strict";

    /*Sale statistics Chart*/
    if ($('#myChart').length) {
        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',
            
            // The data for our dataset
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                        label: '로그인 이력',
                        tension: 0.3,
                        fill: true,
                        backgroundColor: 'rgba(44, 120, 220, 0.2)',
                        borderColor: 'rgba(44, 120, 220)',
                        //data: [18, 17, 4, 3, 2, 20, 25, 31, 25, 22, 20, 9]
                        data: [0, 0, 0, 0, 0, 0, 0, 0, 7, 14, 202, 0]
                    },
                    {
                        label: '회원가입',
                        tension: 0.3,
                        fill: true,
                        backgroundColor: 'rgba(4, 209, 130, 0.2)',
                        borderColor: 'rgb(4, 209, 130)',
                        //data: [40, 20, 17, 9, 23, 35, 39, 30, 34, 25, 27, 17]
                        data: [15, 6, 8, 13, 22, 10, 19, 8, 3, 20, 18, 8]
                    },
                    {
                        label: '결제',
                        tension: 0.3,
                        fill: true,
                        backgroundColor: 'rgba(380, 200, 230, 0.2)',
                        borderColor: 'rgb(380, 200, 230)',
                        //data: [30, 10, 27, 19, 33, 15, 19, 20, 24, 15, 37, 6]
                        data: [0, 0, 0, 0, 0, 0, 0, 0, 5, 72, 11, 0]
                    }

                ]
            },
            options: {
                plugins: {
                legend: {
                    labels: {
                    usePointStyle: true,
                    },
                }
                }
            }
        });
    } //End if

    
    
})(jQuery);