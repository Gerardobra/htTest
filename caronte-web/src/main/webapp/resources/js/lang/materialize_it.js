// Italian

// DATEPICKER
var datepicker_firstDay = 1;
var datepicker_format = 'dd/mm/yyyy';
var datepicker_i18n = {
	cancel:	'Chiudi',
	clear: 'Cancella',
	done: 'Ok',
	months: [ 'gennaio', 'febbraio', 'marzo', 'aprile', 'maggio', 'giugno', 'luglio', 'agosto', 'settembre', 'ottobre', 'novembre', 'dicembre' ],
	monthsShort: [ 'gen', 'feb', 'mar', 'apr', 'mag', 'giu', 'lug', 'ago', 'set', 'ott', 'nov', 'dic' ],
	weekdays: [ 'domenica', 'lunedì', 'martedì', 'mercoledì', 'giovedì', 'venerdì', 'sabato' ],
	weekdaysShort: [ 'dom', 'lun', 'mar', 'mer', 'gio', 'ven', 'sab' ],
	weekdaysAbbrev: ['D', 'L', 'M', 'M', 'G', 'V', 'S']
};

function getDatepickerInitDate($input) {
	var splitDate = $input.val().split("/");
	return new Date(splitDate[2], splitDate[1] - 1, splitDate[0])
}

// TIMEPICKER
var timepicker_i18n = {
	cancel:	'Chiudi',
	clear: 'Cancella',
	done: 'Ok'
};

function formatDate(date) {
    var d = date.getDate();
    var m = date.getMonth() + 1;
    var y = date.getFullYear();
    return '' + (d <= 9 ? '0' + d : d) + '/' + (m<=9 ? '0' + m : m) + '/' + y;
}