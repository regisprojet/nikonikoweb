numButton = 0;
var calendar = new Date();
var nbRandNiko = 8;

function createWeek(dateStr, nikonikos) {

	listMonth =["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"];
	listDays = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Sam", "Dim"];

	date = new Date(dateStr);
	calendar = date;
	var nDayMonth = new Date(date.getFullYear(), date.getMonth()+1, -1).getDate()+1;
	var firstDayOfMonth = (new Date(date.getFullYear(), date.getMonth(), 1)).getDate();
	var currentDayOfMonth = date.getDate();

console.log ("nDayMonth=" + nDayMonth +" firstDayOfMonth =" + firstDayOfMonth + " currentDayOfMonth =" + currentDayOfMonth);

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
			var newCol = document.createElement("div");

			var rowLastChild = newRow.LastChild;

			if(j<5) {
				newCol.setAttribute("class","col-xs-2 Colonne ");
			} else {
				newCol.setAttribute("class","col-xs-1 Colonne");
			}
			var colLastChild = newCol.LastChild;

			var cellule = document.createElement("div");
			var celluleLastChild = cellule.LastChild;
			cellule.setAttribute("class","cellule");
			var celluleId =  "" + calendar.getDate() + calendar.getMonth()+ calendar.getFullYear();
			cellule.setAttribute("id",celluleId);

			/* Insertion de la date */
			/* -------------------- */
			var dayName = document.createElement("div");
			var dayNameId = "dateStr" + i +j;
			dayName.setAttribute("id",dayNameId);

			var newContent = document.createTextNode("");

				/* Insertion des dates précédentes au jour courant */
				/* ----------------------------------------------- */
//console.log("numDay=" + numDay+ " "+ "dayOfMonth=" + dayOfMonth + " " + "calendar.getDate()=" + calendar.getDate() + " " + " firstDay=" + firstDay + " currentDay=" + currentDay + " j=" + j)

			if(dayOfMonth <= firstDay) {
				newContent = document.createTextNode("_");
			}

//			if((calendar.getDate() > firstDay) && (dayOfMonth > firstDay) && ((dayOfMonth - firstDay) < calendar.getDate())) {
//			//if(((dayOfMonth <= 7)  && (dayOfMonth > firstDay)) ||
//			//   ((calendar.getDay() > firstDay) && (dayOfMonth > firstDay) && ((dayOfMonth - firstDay) < calendar.getDate()))) {
//				dayDate = dayOfMonth - firstDay;
////console.log("	   ok date preced");
//				newContent = document.createTextNode(dayDate);
//			}

			if((calendar.getDate() > firstDay) && (dayOfMonth > firstDay) && ((dayOfMonth - firstDay) < calendar.getDate())) {
				dayDate = dayOfMonth - firstDay;
				newContent = document.createTextNode(dayDate);
			}

				/* insertion de la date du jour */
				/* ---------------------------- */
			//if((calendar.getDay()==(j+1)) && numDay
			if((numDay==(j+1)) &&
				(dayOfMonth >= calendar.getDate()) &&
				(currentDay == "")) {
//console.log("  ok date du jour");
						currentDay = "dateStr" + i + j;
						currentZone = "zone" + i + j;
						calculatedDay = calendar.getDate();

						if((calendar.getDay() == new Date().getDay())&&
						   (calendar.getMonth() == new Date().getMonth())&&
						   (calendar.getFullYear() == new Date().getFullYear())) {
								dayName.setAttribute("id","currentDate");
								cellule.setAttribute("id","currentZone");
								//alert(new Date().getDay())
						}

						dayDate = calendar.getDate();
						newContent = document.createTextNode(dayDate);

				/* Insertion des dates des jours suivants la date du jour */
				/* ------------------------------------------------------ */
			} else if((currentDay != "") && (dayOfMonth <= nDayMonth+firstDay)) {
				calculatedDay++;
				dayDate = calculatedDay;
				newContent = document.createTextNode(dayDate);
//console.log("	   ok date suivante");
			}

			/* Insertion Info bulle bootstrap popover */
			/* -------------------------------------- */
			dayName.setAttribute("container","'body'");
			dayName.setAttribute("data-toggle","popover");
			dayName.setAttribute("data-html","true");
			dayName.setAttribute("data-placement","bottom");
			var nikoComment = "<ul class='popover2'>";
			for(var k = 0 ; k<nikonikos.length; k++) {
				var nikoLogDate = nikonikos[k][1].split(" ");
				if(nikoLogDate[0] ==dayDate) {
					nikoComment += "<li>" + " <b> " + nikonikos[k][4] + " " + nikonikos[k][3] + "</b> : " + nikonikos[k][2] + "</li>";
				}
			}
			nikoComment += "</ul>";
			dayName.setAttribute("data-content",nikoComment);
			$(["#" + dayNameId]).popover('toggle');

			var colId = dayDate + calendar.getMonth() + calendar.getFullYear();

			/* Insertion des zones de nikoniko entre le 1er et le dernier jour du mois */
			/* ----------------------------------------------------------------------- */
			if((dayOfMonth <= nDayMonth+firstDay)&&(dayOfMonth > firstDay)) {

				var currentDate = new Date(date.getFullYear(), date.getMonth(), dayDate);

				/* Insertion de la date de la zone nikkoniko */
				/* ----------------------------------------- */
				dayName.appendChild(newContent);
				cellule.insertBefore(dayName,colLastChild);

				/* Insertion de n zones des nikoNiko sauf samedi et dimanche */
				/* ---------------------------------------------------------- */
				if((j!=5)&&(j!=6)) {
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
		var currentDate = new Date(date.getFullYear(), date.getMonth(), i);

		var colId = "" + i + calendar.getMonth() + calendar.getFullYear();

		k=0; //increment dans canvasId;
		for(var j = 0 ; j<nikonikos.length; j++) {
			var nikoLogDate = nikonikos[j][1].split(" ");
			if(nikoLogDate[0] == i) {

				var canvasId = "canvas_" + colId + k ;
				if (document.getElementById(canvasId) !=null) {
					createNiko(nikonikos[j][0],colId,canvasId,0.18,false,"green","red","yellow");

					//document.getElementById(canvasId).setAttribute("onclick","alert('" + nikonikos[j][4] + ": " + nikonikos[j][2] + "')");
					}
				k++;
			}
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

