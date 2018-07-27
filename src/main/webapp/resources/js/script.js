function tariffChanged() {
    var tariffId = $('#tariffId').val();

    $.get("/options/" + tariffId, null, function (data, textStatus, jqXHR) {
        var container = $('#optionCheckboxes');
        container.find('*').remove();
        var newOptions = "";
        data.forEach(function (item, i, arr) {
            newOptions += '<span>\n' +
                '    <input id="optionsIds' + i + '" name="optionsIds" type="checkbox" value="' + item.id + '">\n' +
                '    <label for="optionsIds' + i + '">' + item.name + '</label>\n' +
                '</span>';
        });
        newOptions += '<input type="hidden" name="_optionsIds" value="on">';
        container.html(newOptions);
    });
}

function optionPicked() {
    var optionId = $('#id').val();

    $.get("/options/" + optionId, null, function (data, textStatus, jqXHR) {
        $("id").change(function () {
            var checked = $("#id").is(':checked');
            if (checked) {

            }
        })
    })
}