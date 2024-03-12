
/* global jsGrid */

var Datetimeui = function (config) {
    jsGrid.Field.call(this, config);
};
var TimeUi = function (config) {
    jsGrid.Field.call(this, config);
};
var textnew = function (config) {
    jsGrid.Field.call(this, config);
};

var floatshow = function (config) {
    jsGrid.Field.call(this, config);
};

Datetimeui.prototype = new jsGrid.Field({
    sorter: function (date1, date2) {
        return new Date(date1) - new Date(date2);
    },

    itemTemplate: function (value) {
        return value;
    },

    editTemplate: function (value) {
        if (this.editing)
            return this._datePicker = $("<input>").datepicker({dateFormat: 'yy-mm-dd'}).datepicker("setDate", new Date(value));
        else
            return value;
    },

    insertValue: function () {
        //console.log(this)
        return this._datePicker.$("input").val();
    },
    filterTemplate: function () {
        var now = new Date();
        this._datePicker = $("<input>").datepicker({dateFormat: 'yy-mm-dd'});
        // console.log(this);

        return this._datePicker;
    },
    filterValue: function () {
        // console.log(this);
        return  this._datePicker[0].value;
    },
    editValue: function () {
        //console.log(this._datePicker[0].value);
        return this._datePicker[0].value;
    }
});



textnew.prototype = new jsGrid.Field({
    sorter: function (date1, date2) {
        return $("<input>").val();
    },

    itemTemplate: function (value) {
        //  console.log(this);
        return value;
    },

    editTemplate: function (value) {
        // console.log(this)
        if (this.editing)
            return this._textedit = $("<input type='text'>").val(value);
        else
            return value;
    },

    insertValue: function () {
        return this._textedit = $("<input type='text'>");
    },
    filterTemplate: function () {
        var now = new Date();
        this._textedit = $("<input type='text'>").val();
        // console.log(this);
        // this._toPicker = $("<input>").datepicker({ defaultDate: now.setFullYear(now.getFullYear() + 1) });
        return this._textedit;
    },
    filterValue: function () {
        /// console.log(this);
        return  this._textedit[0].value;
    },
    editValue: function () {
        // console.log(this._textedit[0].value);
        return this._textedit[0].value;
    }
});



floatshow.prototype = new jsGrid.Field({

    itemTemplate: function (value) {
        //console.log(this);
        if (value > 0) {
            return parseFloat(value).toFixed(2);
        } else {
            return value;
        }
    },

    editTemplate: function (value) {
        // console.log(this)
        if (this.editing)
            return this._floatedit = $("<input type='text'>").val(parseFloat(value).toFixed(2));
        else
            return  parseFloat(value).toFixed(2);
    },

    insertValue: function () {
        return this._floatedit = $("<input type='text'>");
    },

    editValue: function () {
        // console.log(this._floatedit[0].value);
        return  parseFloat(this._floatedit[0].value).toFixed(2);
    }
});

TimeUi.prototype = new jsGrid.Field({

    itemTemplate: function (value, item) {
        //console.log(item)
        this._editPicker = $('<div class="bootstrap-timepicker">');

        this._editPicker.append($('<input style="width: 100% ;">').timepicker({
            template: false,
            showInputs: false,
            showSeconds: true,
            showMeridian: false,
            minuteStep: 5
        }).timepicker('setTime', value));
        this._editPicker.append('</div>');

        return value;
    },
    validator: function (value, item) {
        console.log(aaaa)
        //return value > item.value1 && value > lastItemValue2;
    },

    editTemplate: function (value, item) {
        // $('#datetimepicker1').datetimepicker();
        //console.log(item)

        this._editPicker = $('<div class="bootstrap-timepicker">');

        this._editPicker.append($('<input>').timepicker({
            template: false,
            showInputs: false,
            showSeconds: true,
            showMeridian: false,
            minuteStep: 5
        }).timepicker('setTime', value));
        this._editPicker.append('</div>');



        // console.log( this._editPicker);

        return this._editPicker;
    },

    insertValue: function () {
        return this._insertPicker.$("input").val();
    },

    editValue: function () {
        //console.log(this._editPicker);
        return this._editPicker[0].lastChild.value;
    }
});

jsGrid.fields.dateTimeui = Datetimeui;
jsGrid.fields.timeUI = TimeUi;
jsGrid.fields.TextNew = textnew;
jsGrid.fields.Floatshow = floatshow;

