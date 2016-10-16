$(document).ready(function () {
    $("#add_new_tren").click(function () {
        $("#delete").click(deleting);
        var name = prompt("Name of Workout");
        if (name != null) {
            var element = "<div id='workout'><button id='edit_Workout'>Edit</button><button class='delete'>Delete</button><span id='name_workout'>" + name + "</span></div>";
            var x = new XMLHttpRequest();
            x.open("GET", "addNewWorkout?name="+name, true);
            x.send();
            $("#list_workout").append(element);
            $(".delete").click(deleting);
            getXml();
        }
    });
});

function addinxml(xml) {
    $("workout").append("<newwwwwwwwwwwwwwwwwwww>");
}

function deleting() {

    $(this).parent().remove();

}