{% extends "Base.html" %}

{% block functions %}
<!-- Sets the style for the elements -->
<!-- Might need to do the same for all the dice -->
<style>
#resources{
}
#forbidden, #permitted, #required{
}
#proposedSolution{
}
#solution{
}
</style>

<!-- The code that allows the dice to be dropped into their places -->
<script>
function allowDrop(ev){
	ev.preventDefault();
}
<!-- Essentially just transfers text data between locations -->
function drag(ev){
	ev.dataTransfer.setData("text", ev.target.id);
}
function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	ev.target.appendChild(document.getElementById(data));
}
</script>
{% endblock %}

{% block content %}
<!-- Makes the dice. DIE FACES WILL CHANGE IN A LATER BUILD-->
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die1">
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die2"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die3"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die4"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die5"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die6"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die7"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die8"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die9"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die10"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die11"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die12"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die13"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die14"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die15"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die16"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die17"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die18"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die19"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die20"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die21"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die22"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die23"> 
<img src="Images/Red0.svg" draggable="true" ondragstart="drag(event)" id="die24"> 

<!-- Makes the places of the board 
	 MAY NOT ALLOW FOR MORE THAN ONE DIE PER PLACE-->
<div id="resources" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="forbidden" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="permitted" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="required" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="proposedSolution" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="solution" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
 
{% endblock %}