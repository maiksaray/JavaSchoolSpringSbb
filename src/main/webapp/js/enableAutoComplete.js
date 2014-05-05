document.onload = function(){
    console.log('enabling autocomplete');
    $('#fromStationInput').autocomplete({
        serviceUrl: 'http://localhost:8080/SpringSbb/stations/stationListJSON', // Страница для обработки запросов автозаполнения
        minChars: 1, // Минимальная длина запроса для срабатывания автозаполнения
        delimiter: /(,|;)\s*/, // Разделитель для нескольких запросов, символ или регулярное выражение
        maxHeight: 400, // Максимальная высота списка подсказок, в пикселях
        width: 300, // Ширина списка
        zIndex: 9999, // z-index списка
        deferRequestBy: 300, // Задержка запроса (мсек), на случай, если мы не хотим слать миллион запросов, пока пользователь печатает. Я обычно ставлю 300.
        params: { country: 'Yes'}, // Дополнительные параметры
        onSelect: function(data, value){ }// Callback функция, срабатывающая на выбор одного из предложенных вариантов,
        //lookup: [] // Список вариантов для локального автозаполнения
    });

    $('#toStationInput').autocomplete({
        serviceUrl: 'http://localhost:8080/SpringSbb/stations/stationListJSON', // Страница для обработки запросов автозаполнения
        minChars: 1, // Минимальная длина запроса для срабатывания автозаполнения
        delimiter: /(,|;)\s*/, // Разделитель для нескольких запросов, символ или регулярное выражение
        maxHeight: 400, // Максимальная высота списка подсказок, в пикселях
        width: 300, // Ширина списка
        zIndex: 9999, // z-index списка
        deferRequestBy: 300, // Задержка запроса (мсек), на случай, если мы не хотим слать миллион запросов, пока пользователь печатает. Я обычно ставлю 300.
        params: { country: 'Yes'}, // Дополнительные параметры
        onSelect: function(data, value){ }// Callback функция, срабатывающая на выбор одного из предложенных вариантов,
        //lookup: [] // Список вариантов для локального автозаполнения
    });
    console.log('executed')
}