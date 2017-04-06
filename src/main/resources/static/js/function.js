numButton = 0;
var calendar = new Date();
var nbRandNiko = 8;

function createWeek(dateStr, nikonikos) {

	listMonth =["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"];
	listDays = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Sam", "Dim"];

	date = new Date();
	calendar = new Date(dateStr);

	var nDayMonth = new Date(calendar.getFullYear(), calendar.getMonth()+1, -1).getDate()+1;

	var firstDay = (new Date(calendar.getFullYear(), calendar.getMonth(), 1)).getDay() -1 ;
	if(firstDay == (-1)) //passage du dimanche de 0 a 7
		firstDay=6;

	var currentDayOfMonth = calendar.getDate();

	// Recherche du nombre de user dans la team
	// ----------------------------------------
	var nbTeamUser=0;
	var TeamUser = new Array();
	for (var i=0; i<nikonikos.length; i++) {
		var userExist = false;
		for (var j=0; j<TeamUser.length; j++) {
			if(nikonikos[i][5] == TeamUser[j])
				userExist=true;
		}
		if(userExist==false) {
			TeamUser[nbTeamUser] = nikonikos[i][5];
			nbTeamUser++;
		}
	}


	var container = document.getElementById("calendar");

	var contLastChild = container.LastChild;
	var dayOfMonth = 0;
	var currentDay = "";
	var calculatedDay = 0;
	var currentZone = "";
	var dayDate = 0;

	/* mise à jour du jour courant si meme moi et meme annee */
	/* ----------------------------------------------------- */
	var dateReelle = new Date();
	if((dateReelle.getFullYear() == date.getFullYear()) && (dateReelle.getMonth() == date.getMonth()))
		date.setDate(dateReelle.getDate());

	/* pour numméro du dimanche à 7 */
	/* ---------------------------- */
	var numDay = calendar.getDay();
	if(numDay == 0)
		numDay = 7;

	/* Calcul du nombre de semaines */
	/* ---------------------------- */
	var maxWeek = (nDayMonth+firstDay)/7;
	if ((maxWeek - parseInt(maxWeek)) >0 )
		maxWeek = maxWeek +1;
	maxWeek = parseInt(maxWeek);

	/* Insertion du mois en cours et année */
	/* ----------------------------------- */
	var contLastChild = container.LastChild;

	var newRow = document.createElement("div");
	newRow.setAttribute("class","row");
	var rowLastChild = newRow.LastChild;

	/* Insertion du nom des jours */
	/* -------------------------- */
	var contLastChild = container.LastChild;

	var newRow2 = document.createElement("div");
	newRow2.setAttribute("class","row Rangee");
	var row2LastChild = newRow2.LastChild;

	for(var j=0; j<7; j++) {

		var newCol2 = document.createElement("div");
		if(j<5) {
			newCol2.setAttribute("class","col-xs-2 Colonne");
		} else {
			newCol2.setAttribute("class","col-xs-1 Colonne");
		}
		var col2LastChild = newCol2.LastChild;

		var monthName = document.createElement("div");

		var newContent = document.createTextNode(listDays[j]);

		monthName.appendChild(newContent);
		newCol2.insertBefore(monthName,col2LastChild);

		newRow2.insertBefore(newCol2,row2LastChild);

	}
	container.insertBefore(newRow2, contLastChild);

	/* Creation des Lignes bootstrap */
	/* ----------------------------- */
	for (var i = 0; i < maxWeek; i++) {
		var newRow = document.createElement("div");
		var contLastChild = container.LastChild;

		newRow.setAttribute("class","row Rangee");
		var rowId = "week" + i;
		newRow.setAttribute("id","week" + i);

		/* Creation des Colonnes Bootstrap */
		/* ------------------------------- */
		for(var j=0; j<7;j++) {

			/* Recherche du jour courant */
			/* ------------------------- */
			dayOfMonth++;
			dayDate	= dayOfMonth - firstDay;

			var newCol = document.createElement("div");
			newCol.setAttribute("id","cell" + i + j);

			var rowLastChild = newRow.LastChild;

			if(j<5) {
				newCol.setAttribute("class","col-xs-2 Colonne ");
			} else {
				newCol.setAttribute("class","col-xs-1 Colonne");
			}
			var colLastChild = newCol.LastChild;

			var cellule = document.createElement("div");
			var celluleLastChild = cellule.LastChild;

			/* coloration du background du jour courant */
			/* ---------------------------------------- */
			var currentDate = new Date(calendar.getFullYear(), calendar.getMonth(), dayDate);
			if((dateReelle.getDate() == currentDate.getDate()) &&
			   (dateReelle.getMonth() == currentDate.getMonth()) &&
			   (dateReelle.getFullYear() == currentDate.getFullYear())) {
					cellule.setAttribute("class","cellule currentDate");
			} else {
				cellule.setAttribute("class","cellule");
			}
			var celluleId =  "" + calendar.getDate() + calendar.getMonth()+ calendar.getFullYear();
			cellule.setAttribute("id",celluleId);


			/* Insertion Info bulle bootstrap popover */
			/* -------------------------------------- */
			if((j!=5)&&(j!=6)) {

				cellule.setAttribute("container","'body'");
				cellule.setAttribute("data-toggle","popover");
				cellule.setAttribute("data-html","true");
				cellule.setAttribute("data-placement","bottom");
				var nikoComment = "<ul class='popover2'>";
				var nbInsertedNiko = 0;
				for(var k = 0 ; k<nikonikos.length; k++) {
					var nikoLogDate = nikonikos[k][1].split(" ");
					if(nikoLogDate[0] ==dayDate) {
						var nikoCommentColor = "nikoCommentColor" + nikonikos[k][0];
						nikoComment += "<li>" + " <b class=" + nikoCommentColor + "> " + nikonikos[k][4] + " " + nikonikos[k][3] + "</b> : " + nikonikos[k][2] + "</li>";
						nbInsertedNiko ++;
					}
				}
				nikoComment += "</ul>";

				if(nbInsertedNiko>0) {
					cellule.setAttribute("data-content",nikoComment);
					$(["#" + celluleId]).popover('toggle');
				}
			}

			/* Insertion de la date */
			/* -------------------- */
			var dayName = document.createElement("div");
			var dayNameId = "dateStr" + i +j;
			dayName.setAttribute("id",dayNameId);

			var newContent = document.createTextNode("");

			newContent = document.createTextNode(dayDate);

			var colId = dayDate + calendar.getMonth() + calendar.getFullYear();

			/* Insertion des zones de nikoniko entre le 1er et le dernier jour du mois */
			/* ----------------------------------------------------------------------- */
			if((dayOfMonth <= nDayMonth+firstDay)&&(dayOfMonth > firstDay)) {

				//var currentDate = new Date(date.getFullYear(), date.getMonth(), dayDate);
				var currentDate = new Date(calendar.getFullYear(), calendar.getMonth(), dayDate);

				/* Insertion de la date de la zone nikkoniko */
				/* ----------------------------------------- */
				dayName.appendChild(newContent);
				cellule.insertBefore(dayName,colLastChild);

				/* Insertion de n zones des nikoNiko et Nikonikos sauf samedi et dimanche */
				/* ---------------------------------------------------------------------- */
				if((j!=5)&&(j!=6)) {
					/* Insertion des zones des Nikonikos */
					/* --------------------------------- */
					for(var k=0; k<nbTeamUser; k++ ) {
						var ZoneNicos = document.createElement("canvas");
						ZoneNicos.setAttribute("width","25");
						ZoneNicos.setAttribute("height","25");

						var todayDate = new Date();
						var canvasId = "canvas_" + dayDate + calendar.getMonth() + calendar.getFullYear() + k;
						ZoneNicos.setAttribute("id",canvasId);

						cellule.insertBefore(ZoneNicos,colLastChild);
					}
				}
			}

			/* Insertion de la Colonne jour de la semaine */
			/* ------------------------------------------ */
			newCol.insertBefore(cellule,celluleLastChild);
			newRow.insertBefore(newCol,rowLastChild);
		}

		/* Insertion de la row de la semaine */
		/* --------------------------------- */
		container.insertBefore(newRow, contLastChild);
	}

	/* Insertion des nikoniko en fonction des dates */
	/* -------------------------------------------- */
	for (var i=1; i<nDayMonth+1; i++) {
		var nikos = createRandNiko();

		var todayDate = new Date();
		var currentDate = new Date(calendar.getFullYear(), calendar.getMonth(), i);

		var colId = "" + i + calendar.getMonth() + calendar.getFullYear();

		k=0; //increment dans canvasId;
		for(var j = 0 ; j<nikonikos.length; j++) {
			var nikoLogDate = nikonikos[j][1].split(" ");
			if(nikoLogDate[0] == i) {

				var canvasId = "canvas_" + colId + k ;
				if (document.getElementById(canvasId) !=null) {
					createNiko(nikonikos[j][0],colId,canvasId,0.18,false,"green","red","yellow");
					}
				k++;
			}
		}
	}

	/* Uniformise la hauteur des cellules du calendrier */
	/* ------------------------------------------------ */
	var heightMaxCel =0;
	for (var i = 0; i < maxWeek; i++) {
		for(var j=0; j<7;j++) {
			var colId = "cell" + i + j;
			if(document.getElementById(colId) != null) {
				if(document.getElementById(colId).offsetHeight > heightMaxCel) {
					heightMaxCel = document.getElementById(colId).offsetHeight;
				}
			}
		}
	}
	//heightMaxCel+= 2;
	//heightMaxCel+= 15;
	for (var i = 0; i < maxWeek; i++) {
		for(var j=0; j<7;j++) {
			var colId = "cell" + i + j;
			document.getElementById(colId).style.minHeight = heightMaxCel + "px";
			//document.getElementById(colId).style.height = heightMaxCel + "px";
			//console.log("colId= " + colId + "  heightMaxCel=" + heightMaxCel);
		}
	}

}


function createRandNiko() {
	var nikos = new Array() ;
	var randMax = nbRandNiko;
	var randMin = nbRandNiko - 2;
	var nbNiko = Math.floor(Math.random()*(randMax-randMin+1)+randMin);
	for (var i = 0; i < nbNiko; i++) {
		var niko = Math.floor(Math.random()*(3)+1);
		nikos[i] = niko;
	}

	return nikos;
}

function previousNextMonth2(date,inc) {

	var moisCourant = new Date();
	var moisPreced = date;
	moisPreced = new Date(moisPreced.getFullYear(),(moisPreced.getMonth()+inc),moisPreced.getDate());

	if(moisPreced.getMonth() == 13) {
		moisPreced = new Date((moisPreced.getFullYear()+1),(moisPreced.getMonth()-12),moisPreced.getDate());
	}
	if(moisPreced.getMonth() == 0 ) {
		moisPreced = new Date((moisPreced.getFullYear()-1),(moisPreced.getMonth()+12),moisPreced.getDate());
	}

	// cas ou le mois precedent à moins de jour dque le mois courant
	if(moisCourant.getMonth() == moisPreced.getMonth())
		moisPreced.setDate(moisPreced.getDate()-1);

	var calendar = document.getElementById("calendar");
	var nbSup = calendar.childNodes.length;
	for(var j=0; j<nbSup; j++) {
		calendar.removeChild(calendar.childNodes[0]);
	}
	createWeek(moisPreced);
}

