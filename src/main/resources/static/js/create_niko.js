var fillStyle = 0;
function createNiko(index,divId,canvasId,scale,clic,colHappy,colSad,colSoft) {
	 var container = document.getElementById(divId);
	 var canvas = document.getElementById(canvasId);
	 var ctx = canvas.getContext("2d");


//console.log(index)
	 if(index==1) {
	 	ctx.fillStyle = colHappy;
	 } else if(index==2) {
	 	ctx.fillStyle = colSoft;
	 } else if(index==3) {
	 	ctx.fillStyle = colSad;
	 } else {
	 	ctx.fillStyle = "blue";
	 }

	 ctx.beginPath();
	 //arc (xCentre,yCentre,Rayon,angleOrig,angleFin)
	 ctx.arc(50*scale,50*scale,50*scale,0,2*Math.PI);
	 ctx.fill();

	 ctx.beginPath();
	 ctx.arc(30*scale,35*scale,5*scale,0,2*Math.PI);
	 ctx.lineWidth = 5*scale;
	 ctx.strokeStyle = 'black';
     ctx.stroke();

	 ctx.beginPath();
	 ctx.arc(70*scale,35*scale,5*scale,0,2*Math.PI);
	 ctx.lineWidth = 5*scale;
	 ctx.strokeStyle = 'black';
     ctx.stroke();

	 if(index==1) {
		ctx.beginPath();
		ctx.arc(50*scale,55*scale,25*scale,0,1*Math.PI,false);
		ctx.lineWidth = 5*scale;
		ctx.strokeStyle = 'black';
	    ctx.stroke();
     }

     if(index==2) {
		ctx.beginPath();
		ctx.moveTo(30*scale,65*scale);
		ctx.lineTo(70*scale,65*scale);
		ctx.lineWidth = 5*scale;
		ctx.strokeStyle = 'black';
	    ctx.stroke();
     }

     if(index==3) {
		ctx.beginPath();
		ctx.arc(50*scale,77*scale,25*scale,0,1*Math.PI,true);
		ctx.lineWidth = 5*scale;
		ctx.strokeStyle = 'black';
	    ctx.stroke();
     }

	if(clic==true) {
		canvas.setAttribute("onclick","callCreateNiko()");
		container.appendChild(canvas);
    container.scrollTop = container.scrollHeight;
	}
}

function callCreateNiko(divId) {
	if(fillStyle==1)
		fillStyle=2;
	else if (fillStyle==2)
		fillStyle=3;
	else
		fillStyle=1;

	var canvas = "canvas";
	createNiko(fillStyle,divId,canvas,1,true,"green","red","yellow");

	var monthNames = [
    "Janvier", "Février", "Mars",
    "Avril", "Mai", "Juin", "Juillet",
    "Août", "Septembre", "Octobre",
    "Novembre", "Décembre"
  	];

  	var dayNames = [
    "lundi", "mardi", "mercredi",
    "jeudi", "vendredi", "samedi", "dimanche"
  	];

  	var date = new Date();
  	var day = date.getDate();
 	var monthIndex = date.getMonth();
 	var year = date.getFullYear();

	document.getElementById("DateDuJour").innerHTML = day + " " + monthNames[monthIndex] + " " + year;
}
