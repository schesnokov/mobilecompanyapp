$(document).ready(function () {
    tariffChanged();
});

var allIds = [];

function tariffChanged() {
    allIds = [];
    var tariffId = $('#tariffId').val();
    console.log("script started");

    $.get("/options/" + tariffId, null, function (data, textStatus, jqXHR) {
        console.log("continue1");
        var container = $('#optionCheckboxes');
        container.find('*').remove();
        var newOptions = "";
        console.log("continue2");
        data.forEach(function (item, i, arr) {
            newOptions += '<span style="text-align: left">\n' +
                '    <input class="optionCheckbox" data-id="' + i + '" id="optionsIds' + i + '" name="optionsIds" type="checkbox" value="' + item.id + '">\n' +
                '    <label for="optionsIds' + i + '">' + item.name + '</label>\n' +
                '</span> <br />';
        });
        newOptions += '<input type="hidden" name="_optionsIds" value="on">';
        console.log("continue3")
        container.html(newOptions);
    });
}

$('body').on('change', '.optionCheckbox', function () {
    var optionId = $(this).attr('value');

    var checkboxes = document.getElementsByClassName("optionCheckbox");

    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = $('.optionCheckbox')[i];
        if ($(this).attr('value') !== optionId) {
            checkbox.attr('checked', false);
        }
    }

    var checked = $(this).attr('checked');
    var container = $('#optionCheckboxes');

    $.get("/options/conflict/" + optionId, null, function (data, textStatus, jqXHR) {
        var conflictedIds = data;

        if (checked) {
            conflictedIds.forEach(function (item, i, arr) {
                if (container.find($('.optionCheckbox[value=' + item + ']'))) {
                    $('input[value=' + item + ']').prop({
                        checked: false,
                        disabled: true
                    });
                    allIds[allIds.indexOf(parseInt(item))] = '';
                }
            })
        } else {
            allIds[allIds.indexOf(parseInt(optionId))] = '';
            conflictedIds.forEach(function (item, i, arr) {
                $('input[value=' + item + ']').prop({
                    disabled: false
                })
            });
        }
    });

    $.get("/options/dependent/" + optionId, null, function (data, textStatus, jqXHR) {
        var dependentIds = data;

        if (checked) {
            allIds.push(parseInt(optionId));
            dependentIds.forEach(function (item, i, arr) {
                if (container.find($('.optionCheckbox[value=' + item + ']'))) {
                    $('input[value=' + item + ']').prop({
                        checked: true,
                        disabled: true
                    });
                    allIds.push(parseInt(item));
                }
            })
        } else {
            dependentIds.forEach(function (item, i, arr) {
                $('input[value=' + item + ']').prop({
                    checked: false,
                    disabled: false
                });

                allIds[allIds.indexOf(parseInt(item))] = '';

            })
        }
    });

    allIds = allIds.filter(function (e) {
        return e
    });

    console.log(allIds);

});

$("#addToCartBtn").on('click', function () {
    var idsInString = '';
    for (var i = 0; i < allIds.length; i++) {
        if (allIds[i] !== "") idsInString += allIds[i] + ',';
    }
    //$('#ids').attr('value', idsInString);
    $('#ids').attr('value', allIds);
    document.getElementById('changeContract').submit();
});