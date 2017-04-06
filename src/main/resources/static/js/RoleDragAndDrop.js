//var targetElements  = new Set();
//var tartetSize = 0;


function targetElementsToString() {
	console.log("targetElementsToString()");

	var hybrid = document.getElementById('hybrid');
	if(hybrid==null) {
		console.error("hybrid = null");
		return;
	}
	var targetArea = document.getElementById('roleTarget'); 
	var rolesInTarget = targetArea.querySelectorAll('.roleDaD');
		

	var targetElems = new Set();
	for(var i =0;i<rolesInTarget.length;i++) {
		targetElems.add(rolesInTarget[i].getAttribute("value"));
	}

	str = "VALUES("+targetElems.size+"):";
	for(let elem of targetElems.values()) {
		str += elem;
		str += ",";
	}
	hybrid.setAttribute("value",str);
	console.log(str);

}

function enableDragAndDrop() {
		targetElementsToString();
		console.log("enableDragAndDrop()");
		
		var hybrid = document.getElementById('hybrid');
		
		if(document==null) {
			console.error("document is null");
			return;
		}
		//var targetArea = document.querySelector('#roleTarget'); 
		var targetArea = document.getElementById('roleTarget'); 
		if(targetArea==null) {
		 	console.error("targetArea = null, exit");
		 	return;
		}

		var sourceArea = document.getElementById('roleSource'); 
		if(sourceArea==null) {
		 	console.error("sourceArea = null, exit");
		 	return;
		}

		var rolesDaD = document.querySelectorAll('.roleDaD');
		console.log("nombre d'éléments trouvés : "+rolesDaD.length);
		for(var i = 0; i < rolesDaD.length; i++)
		{
			rolesDaD[i].setAttribute('draggable', 'true');  // optional with images
			rolesDaD[i].addEventListener('dragstart', function(evnt) {
				//this.className = 'itemchoosen';
				evnt.dataTransfer.effectAllowed = 'copy';
				evnt.dataTransfer.setData('text', this.id);
				return false;  
			}, false);
		}	
		
		var rolesInTarget = targetArea.querySelectorAll('.roleDaD');
		for(var i = 0; i < rolesInTarget.length; i++) {
			rolesInTarget[i].addEventListener('dragend', function(evnt) {
				console.log("dragend");
				evnt.target.parentNode.removeChild(evnt.target);
				//targetElements.delete(rolesInTarget[i].value);
				//targetElements.delete(rolesInTarget[i]);
				//tartetSize--;
				targetElementsToString();	
				return false;
			}, false);
			
			//targetElements.add(rolesInTarget[i]);
			//tartetSize++;
			targetElementsToString();
		}
		
		targetArea.addEventListener('dragover', function(evnt) {
				console.log("dragover");
				if (evnt.preventDefault) evnt.preventDefault();
				evnt.dataTransfer.dropEffect = 'copy';
				return false;	
		}, false);
		  
		targetArea.addEventListener('dragenter', function(evnt) {
				console.log("dragenter");
				return false;	
		}, false);
		  
		targetArea.addEventListener('dragleave', function(evnt) {
				console.log("dragleave");
				return false;
		}, false);
		  
		targetArea.addEventListener('drop', function(evnt) {
			if (evnt.stopPropagation) evnt.stopPropagation();
			var id = evnt.dataTransfer.getData('text');
			//var html = evnt.dataTransfer.getData("text/html");
			//var text = evnt.dataTransfer.getData("text/plain");
			//if(html) {
			//	evnt.target.innerHTML += html + " ";
			//}
			//else {
			//	evnt.target.innerHTML += text + " ";
			//}
			//evnt.target.className = '';
//
			//var obj = evnt.target.innerHTML += evnt.dataTransfer.getData("text/plain");	
			var theitem = document.getElementById(id);	
			//theitem.parentNode.removeChild(el);	
			//theitem.className='itemblurred';
			theitem.className='roleDaD';
			var y  = document.createElement('img');

			y.src = theitem.src;
            y.className = 'roleDaD';
            y.setAttribute('value',theitem.getAttribute("id"));
			y.addEventListener('dragend', function(evnt) {
				console.log("dragend");
				evnt.target.parentNode.removeChild(evnt.target);
				tartetSize--;
				targetElementsToString();	
				return false;
			}, false);
			//tartetSize++;
			targetArea.appendChild(y);
			evnt.preventDefault(); // for Firefox
			//targetElements.add(rolesInTarget[i].getAttribute("value"));
			//targetElements.add(rolesInTarget[i].value);
			//targetElements.add(rolesInTarget[i]);	
			targetElementsToString();
			return false;
		}, false);



		var trashArea = document.getElementById('trashZone'); 
		if(trashArea==null) {
		 	console.error("trashArea = null, exit");
		 	return;
		}


		trashArea.addEventListener('dragover', function(evnt) {
				console.log("dragover");
				if (evnt.preventDefault) evnt.preventDefault();
				evnt.dataTransfer.dropEffect = 'move';
				return false;	
		}, false);
		
		trashArea.addEventListener('dragenter', function(evnt) {
				console.log("dragenter");
				return false;	
		}, false);
		  
		trashArea.addEventListener('dragleave', function(evnt) {
				console.log("dragleave");
				return false;
		}, false);
		  
		trashArea.addEventListener('dragend', function(evnt) {
				console.log("dragend");
				evnt.target.parentNode.removeChild(evnt.target);	
				tartetSize--;
				return false;
		}, false);
		

		trashArea.addEventListener('drop', function(evnt) {
			if (evnt.stopPropagation) evnt.stopPropagation();
			var id = evnt.dataTransfer.getData('text');		
			var theitem = document.getElementById(id);
			if((theitem==null) || (theitem.parentNode==null)) {
				console.error("theitem, parentNode is null");
				return false;
			}
			theitem.parentNode.removeChild(theitem);	
			//theitem.className='itemblurred';
            //var y  = document.createElement('img');
			//y.src = theitem.src;
			//trashArea.appendChild(y);
			evnt.preventDefault(); // for Firefox
			return false;
		}, false);


}