var fillStyle = 0;
monthNames = [
    "Janvier", "Février", "Mars",
    "Avril", "Mai", "Juin", "Juillet",
    "Août", "Septembre", "Octobre",
    "Novembre", "Décembre"
  	];

function createNiko(satisfaction,divId,canvasId,scale,clic,colHappy,colSad,colSoft) {
	 var container = document.getElementById(divId);
	 var canvas = document.getElementById(canvasId);
	 var ctx = canvas.getContext("2d");

	if(clic==true) {
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
		}

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
		canvas.setAttribute("onclick","callCreateNiko('"+  divId + "', 'no' ," + satisfaction + ")");
	}

    /* renvoie de la valeur du nikoniko dans le input hidden de la page */
    /* ---------------------------------------------------------------- */
    if(document.getElementById("satisfaction")!=null)
    	document.getElementById("satisfaction").value = satisfaction;
}

function callCreateNiko(divId,init,satisfaction) {

	if(init=="yes") {
		if (satisfaction==3)
			satisfaction=2;
		else if (satisfaction==2)
			satisfaction=1;
		else if (satisfaction==1)
			satisfaction=0;
		else if (satisfaction==0)
			satisfaction=-1;
	}

	var canvas = "canvas";
	createNiko(satisfaction,divId,canvas,1,true,"green","red","yellow");

  	var dayNames = [
    "lundi", "mardi", "mercredi",
    "jeudi", "vendredi", "samedi", "dimanche"
  	];

  	var date = new Date();
  	var day = date.getDate();
 	var monthIndex = date.getMonth();
 	var year = date.getFullYear();

 	/* mise a jour de la date avec le nom du mois en cours au lieu de son numero */
 	/* ------------------------------------------------------------------------- */
 	/*var DateDuJour = document.getElementById("DateDuJour").innerHTML;
	var dateSplit = DateDuJour.split(" ");
  	var day = dateSplit[0];
 	var month = dateSplit[1];
 	var year = dateSplit[2];
 	document.getElementById("DateDuJour").innerHTML = day + " " + month + " " + year;*/
}

function setJourPreced(n) {

 	var DateDuJour = document.getElementById("newDayDateStr").value;
	var dateSplit = DateDuJour.split("-");
 	var year = dateSplit[0];
 	var month = dateSplit[1];
 	var day = dateSplit[2]-1;
 	var hour = dateSplit[3];
 	var min = dateSplit[4];
 	var sec = dateSplit[5];

 	/* calcul du nombre de jours entre la date réelle et la DateDuJour */
 	/* ---------------------------------------------------------------- */
 	var date = new Date();
 	var nDayprecedMonth = new Date(date.getFullYear(), date.getMonth(), -1).getDate()+1;
 	var dayCount = date.getDate() - day;
 	if((date.getMonth()+1) > month)
		dayCount = dayCount - (-nDayprecedMonth);

 	if(dayCount<=n) {
	 	var dateStr = year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec;
		document.getElementById("newDayDate").value = dateStr;

	 	var dateStr = year + "-" + month + "-" + day + "-" + hour + "-" + min + "-" + sec;
	 	document.getElementById("newDayDateStr").value = dateStr;
 	}
}

function setJourSuiv() {

 	var DateDuJour = new Date();
 	var year = DateDuJour.getFullYear();
 	var month = DateDuJour.getMonth()+1;
 	var day = DateDuJour.getDate();
 	var hour = DateDuJour.getHours();
 	var min = DateDuJour.getMinutes();
 	var sec = DateDuJour.getSeconds();

 	var dateStr = year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec;
	document.getElementById("newDayDate").value = dateStr;

	var dateStr = year + "-" + month + "-" + day + "-" + hour + "-" + min + "-" + sec;
	document.getElementById("newDayDateStr").value = dateStr;
}
