$(document).ready(function () {
    $("#add_new_tren").click(function () {
        var name = prompt("Name of Workout");
        if (name != null) {
            var x = new XMLHttpRequest();
            x.open("GET", "addNewWorkout?name=" + name, true);
            x.send();
            x.onreadystatechange = function() {
                if (x.readyState == 4) {

                    if (x.status == 200) {
                        getXml();
                    } else alert("no connection");
                }
            }

        }
    });
});

function deleting() {
   var id= $(this).parent().attr("id");
    var x = new XMLHttpRequest();
    x.open("GET", "deleteWorkout?id=" + id, true);
    x.send();
    x.onreadystatechange = function() {
        if (x.readyState == 4) {

            if (x.status == 200) {
                getXmlUser();
            } else alert("no connection");
        }
    }
}
function  edit() {
    var id= $(this).parent().attr("id");
    getXmlWorkout(id);
}