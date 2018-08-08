function tariffChanged() {
    var tariffId = $('#tariffId').val();

    $.get("/options/" + tariffId, null, function (data, textStatus, jqXHR) {
        var container = $('#optionCheckboxes');
        container.find('*').remove();
        var newOptions = "";
        data.forEach(function (item, i, arr) {
            newOptions += '<span>\n' +
                '    <input class="optionCheckbox" data-id="' + i + '" id="optionsIds' + i + '" name="optionsIds" type="checkbox" value="' + item.id + '">\n' +
                '    <label for="optionsIds' + i + '">' + item.name + '</label>\n' +
                '</span> <br />';
        });
        newOptions += '<input type="hidden" name="_optionsIds" value="on">';
        container.html(newOptions);
    });
}

$('body').on('change', '.optionCheckbox', function () {
    var optionId = $(this).attr('value');
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
                }
            })
        } else {
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

            dependentIds.forEach(function (item, i, arr) {
                if (container.find($('.optionCheckbox[value=' + item + ']'))) {
                    $('input[value=' + item + ']').prop({
                        checked: true,
                        disabled: true
                    });
                }
            })
        } else {
            dependentIds.forEach(function (item, i, arr) {
                $('input[value=' + item + ']').prop({
                    checked: false,
                    disabled: false
                });
            })
        }
    });
});