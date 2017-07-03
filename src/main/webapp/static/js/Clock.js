/**
 * Created by tryu on 2017/7/3.
 */
$(function () {
    $('#clock_in').click(function () {
        $.ajax({
            type: "POST",
            url: "/attend/clockin",
            success: function (data) {
                alert(data);
            }
        });
    })
});