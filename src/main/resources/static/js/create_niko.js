var fillStyle = 0;
function createNiko(satisfaction,divId,canvasId,scale,clic,colHappy,colSad,colSoft) {
	 var container = document.getElementById(divId);
	 var canvas = document.getElementById(canvasId);
	 var ctx = canvas.getContext("2d");


	 if(satisfaction==1) {
	 	ctx.fillStyle = colHappy;
	 } else if(satisfaction==2) {
	 	ctx.fillStyle = colSoft;
	 } else if(satisfaction==3) {
	 	ctx.fillStyle = colSad;
	 } else {
	 	ctx.fillStyle = "white";
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

	 if(satisfaction==0) {
		ctx.font="60px Georgia";
		ctx.fillStyle='black';
		ctx.fillText("?",35*scale,85*scale);

		ctx.beginPath();
		ctx.arc(50*scale,50*scale,47*scale,0,2*Math.PI);
		//ctx.arc(50*scale,55*scale,25*scale,0,1*Math.PI,false);
		ctx.lineWidth = 5*scale;
	    ctx.stroke();
     }

	 if(satisfaction==1) {
		ctx.beginPath();
		ctx.arc(50*scale,55*scale,25*scale,0,1*Math.PI,false);
		ctx.lineWidth = 5*scale;
		ctx.strokeStyle = 'black';
	    ctx.stroke();
     }

     if(satisfaction==2) {
		ctx.beginPath();
		ctx.moveTo(30*scale,65*scale);
		ctx.lineTo(70*scale,65*scale);
		ctx.lineWidth = 5*scale;
		ctx.strokeStyle = 'black';
	    ctx.stroke();
     }

     if(satisfaction==3) {
		ctx.beginPath();
		ctx.arc(50*scale,77*scale,25*scale,0,1*Math.PI,true);
		ctx.lineWidth = 5*scale;
		ctx.strokeStyle = 'black';
	    ctx.stroke();
     }

	if(clic==true) {
		console.log("callCreateNiko("+  divId + "," + satisfaction + ")");
		canvas.setAttribute("onclick","callCreateNiko('"+  divId + "'," + satisfaction + ")");
		//container.appendChild(canvas);
		//container.scrollTop = container.scrollHeight;
	}

    /* renvoie de la valeur du nikoniko dans le input hidden de la page */
    /* ---------------------------------------------------------------- */
    document.getElementById("satisfaction").value = satisfaction;
    //$("#${satisfaction}").val = satisfaction;
}

function callCreateNiko(divId,satisfaction) {
	//alert("toto");

	if(satisfaction==-1)
		satisfaction=0;
	else if(satisfaction==0)
		satisfaction=1;
	else if (satisfaction==1)
		satisfaction=2;
	else if (satisfaction==2)
		satisfaction=3;
	else if (satisfaction==3)
		satisfaction=1;

	var canvas = "canvas";
	createNiko(satisfaction,divId,canvas,1,true,"green","red","yellow");

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
