utils =
{
    showTicketPurchaseDialog: function (trainId) {
        $.ajax({
            type: "GET",
            url: "SpringSbb/trains/trainJSON",
            data: {id: trainId},
            dataType: "json",
            success: function (data) {

            }
        });
    },
    appendNewStationInput: function () {
        var stations = [];
        $.ajax({
            url: "http://localhost:8080/SpringSbb/stations/stationListJSON?query=",
            success: function (response) {
                stations = $.parseJSON(response).suggestions;
            }
        }).done(function () {
                var div = $('<div></div>');
                div.addClass('stationInput');
                var stationSelect = $('<select></select>')
                stations.forEach(function (value) {
                    stationSelect.append($('<option>'+value+'</option>'));
                });
                stationSelect.addClass('stationSelect');
                div.append($('<label>station</label>'));
                div.append(stationSelect);
                div.append($('<label>time</label>'));
                div.append($('<input type="time">').addClass('stationTime'));
                $('#stations').append(div);
            });
    },
    collectInputData:function(){
        var trainDataJSON = {
            schedule:[],
            seats:$('#numberOfSeats')[0].value
        };
        var stations = $('.stationInput').children('.stationSelect').find(':selected');
        var times = $('.stationInput').children('.stationTime');
        for (var i = 0; i < stations.size(); i++) {
            var item = {
                station: stations[i].text,
                time:times[i].value
            };
            trainDataJSON.schedule.push(item);
        };
        $.ajax({
            url:"http://localhost:8080/SpringSbb/trains/create/submit",
            data:"train="+JSON.stringify(trainDataJSON),
            dataType: 'application/json; charset=utf-8',

            success: function (data) {
                alert("got!");
                var answer = JSON.parse(data);
                if(answer.status=="ok"){
                    window.location.href="http://localhost:8080/SpringSbb/trains";
                }
                else{
                    $('#errorDiv').text="can't add train"+answer.reason;
                    $('#errorDiv').show();
                }
            },
            complete:function(jqXHR){
                if(jqXHR.readyState===4){
                    if(jqXHR.responseText == "ok"){
                        window.location.href="http://localhost:8080/SpringSbb/trains";
                    }
                    else{
                        $('#errorDiv').text="can't add train";
                        $('#errorDiv').show();
                    }
                }
            }
        }).done(function(){alert('done!')});
    }
};