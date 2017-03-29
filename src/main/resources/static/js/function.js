numButton = 0;
var calendar = new Date();
var nbRandNiko = 8;


function createWeek(date) {
	listMonth =["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"];
	listDays = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Sam", "Dim"];

	calendar = date;
	var nDayMonth = new Date(date.getFullYear(), date.getMonth()+1, -1).getDate()+1;

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

	/* Calcul du premier jour du mois */
	/* ------------------------------ */
	var firstDay = calendar.getDate() - calendar.getDay();
	while(firstDay>0) {
		firstDay = firstDay - 7 ;
	}
	firstDay = Math.abs(firstDay);

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

	//---------button precedent--------------
	var newCol = document.createElement("div");

	newCol.setAttribute("class","col-xs-3 butPrecSuiv");
	var colLastChild = newCol.LastChild;

	var newButton = document.createElement("button");
	newButton.setAttribute("onclick","previousNextMonth(calendar,-1);");
	newButton.setAttribute("id","Precedant");
	newCol.insertBefore(newButton,colLastChild);
	newRow.insertBefore(newCol,rowLastChild);

	//----------mois et annee-----------------
	var newCol = document.createElement("div");

	newCol.setAttribute("class","col-xs-6 divDateDuJour");
	var colLastChild = newCol.LastChild;

	var monthName = document.createElement("div");

	var newContent = document.createTextNode(listMonth[calendar.getMonth()].toUpperCase() + "   " + calendar.getFullYear());

	monthName.appendChild(newContent);
	newCol.insertBefore(monthName,colLastChild);

	newRow.insertBefore(newCol,rowLastChild);

	//---------button suivant--------------
	var newCol = document.createElement("div");

	newCol.setAttribute("class","col-xs-3 butPrecSuiv");
	var colLastChild = newCol.LastChild;

	var newButton = document.createElement("button");
	newButton.setAttribute("onclick","previousNextMonth(calendar,+1);");
	newButton.setAttribute("id","suivant");
	newCol.insertBefore(newButton,colLastChild);
	newRow.insertBefore(newCol,rowLastChild);

	container.insertBefore(newRow, contLastChild);

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
				newCol.setAttribute("class","col-xs-2 Colonne");
			} else {
				newCol.setAttribute("class","col-xs-1 Colonne");
			}
			var colLastChild = newCol.LastChild;

			var cellule = document.createElement("div");
			var celluleLastChild = cellule.LastChild;
			cellule.setAttribute("class","cellule");

			/* Insertion de la date */
			/* -------------------- */
			var dayName = document.createElement("div");
			dayName.setAttribute("id","dateStr" + i +j);

			var newContent = document.createTextNode("");

				/* Insertion des dates précédentes au jour courant */
				/* ----------------------------------------------- */
			if(dayOfMonth <= firstDay) {
				newContent = document.createTextNode("_");
			}

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
			}

			var colId = dayDate + calendar.getMonth() + calendar.getFullYear();
			//if(cellule.getAttribute("id")=="")
				//cellule.setAttribute("id",colId);

			/* Insertion des zones de nikoniko entre le 1er et le dernier jour du mois */
			/* ----------------------------------------------------------------------- */
			if((dayOfMonth <= nDayMonth+firstDay)&&(dayOfMonth > firstDay)) {

				var currentDate = new Date(date.getFullYear(), date.getMonth(), dayDate);

				/* Insertion de la date de la zone nikkoniko */
				/* ----------------------------------------- */
				dayName.appendChild(newContent);
				cellule.insertBefore(dayName,colLastChild);

				if((currentDate.getDay()!=0)&&(currentDate.getDay()!=6)) {

					/* Insertion de la zone des nikoNiko (createRandNiko) */
					/* -------------------------------------------------- */
					for(var k=0; k<nbRandNiko; k++ ) {
						var ZoneNicos = document.createElement("canvas");
						ZoneNicos.setAttribute("width","25");
						ZoneNicos.setAttribute("height","25");

						var todayDate = new Date();

						if((currentDate.getDate() < todayDate.getDate()) ||
						   ((currentDate.getMonth() > todayDate.getMonth()) && (currentDate.getFullYear() < todayDate.getFullYear())) ||
						   ((currentDate.getMonth() == todayDate.getMonth()) && (currentDate.getDate() <= todayDate.getDate()))) {
								var canvasId = "canvas_" + dayDate + calendar.getMonth() + calendar.getFullYear() + k;
								ZoneNicos.setAttribute("id",canvasId);
						}
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
		if((currentDate.getMonth() < todayDate.getMonth()) ||
		   ((currentDate.getMonth() > todayDate.getMonth()) && (currentDate.getFullYear() < todayDate.getFullYear())) ||
		   ((currentDate.getMonth() == todayDate.getMonth()) && (currentDate.getDate() <= todayDate.getDate()))) {

			if((currentDate.getDay()!=0)&&(currentDate.getDay()!=6)) {
				for (var j=0; j<nikos.length; j++) {
					var colId = "" + i + calendar.getMonth() + calendar.getFullYear();
					var canvasId = "canvas_" + colId + j;
					createNiko(nikos[j],colId,canvasId,0.18,false,"green","red","yellow");
				}
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

function previousNextMonth(date,inc) {

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
