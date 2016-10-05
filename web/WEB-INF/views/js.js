$(document).ready( function(){
var arayTren = new Array();
$("#add_new_tren").click( function(){
$("#delete").click(deleting);
var name = prompt("нзвание упражнения");
var element="<div id='tren'><button id='edittren'>Редактировать</button><button class='delete'>Удалить</button><span id='name_tren'>"+name+"</span></div>";
$("#list_tren").append(element);
$(".delete").click(deleting);
addinxml;
//arayTren[arayTren.length]=element;
//alert(arayTren.length);


});




});

function addinxml(xml){
$("workout").append("<newwwwwwwwwwwwwwwwwwww>");
}

function deleting(){

$(this).parent().remove();

}