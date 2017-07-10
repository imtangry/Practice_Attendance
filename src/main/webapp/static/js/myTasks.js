$(function () {
    $("#myTasks").click(function () {
        $.ajax({
            type: "POST",
            url: "/reattend/list",
            success: function (data) {
                $(".content").html(data);
            }
        });
    })
});