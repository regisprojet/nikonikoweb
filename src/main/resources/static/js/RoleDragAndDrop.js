// non utilisé
function clearTargetArea() {
	console.log("clearTargetArea()");
	var targetArea = document.getElementById('roleTarget'); 
	if(targetArea==null) {
	 	console.error("targetArea = null, exit");
		 	return;
	}
	var rolesTarget = targetArea.querySelectorAll('.roleDaD');
	for(var elem in rolesTarget) {
		console.log(""+elem);
		elem.remove();
	}

}

// non utilisé
function getItemFromTarget() {
	console.log("getItemFromTarget()");
	var targetArea = document.getElementById('roleTarget'); 
	var items = targetArea.querySelectorAll('.roleDaD');
}

console.log("on passe dans la variable globale du source JS");
var targetElements  = new Set();

function targetElementsToString() {
	console.log("targetElementsToString()");

	var hybrid = document.getElementById('hybrid');
	if(hybrid==null) {
		console.error("hybrid = null");
		return;
	}
	string = "valeur:"+targetElements.length+":";
	for(var i =0;i<targetElements.length;i++) {
		string = string + targetElements[i].value;
		if(i<targetElements-1) {
			string = string + "&";
		}
	}
	hybrid.setAttribute("value",string);
	console.log(string);

}

function enableDragAndDrop() {
	
		console.log("enableDragAndDrop()");
		
		var hybrid = document.getElementById('hybrid');
		
		var resetRoleButton = document.getElementById('resetRoleButton'); 
		resetRoleButton.addEventListener('click',
				function() {clearTargetArea();});

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
				this.className = 'itemchoosen';
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
				targetElements.delete(rolesInTarget[i]);
				targetElementsToString();	
				return false;
			}, false);
			targetElements.add(rolesInTarget[i]);
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
			var theitem = document.getElementById(id);	
			//theitem.parentNode.removeChild(el);	
			theitem.className='itemblurred';
			var y  = document.createElement('img');
			y.src = theitem.src;
			//y.addEventListener('dragend', function(event){
    		//	if(event.dataTransfer.dropEffect !== 'none'){
        	//	$(this).remove();
    		//}
			//});

			y.addEventListener('dragend', function(evnt) {
				console.log("dragend");
				evnt.target.parentNode.removeChild(evnt.target);
				targetElementsToString();	
				return false;
			}, false);
		
			targetArea.appendChild(y);
			evnt.preventDefault(); // for Firefox
			//targetElements.add(rolesInTarget[i].getAttribute("value"));
			//targetElements.add(rolesInTarget[i].value);
			targetElements.add(rolesInTarget[i]);
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



		//targetArea.addEventListener('dragend', function(event){
		//	console.log("dragend");
    	//	if(event.dataTransfer.dropEffect !== 'none'){
        //		event.
        //		$(this).remove();
    	//	}
		//});

//		//-----------------------------
//
//        sourceArea.addEventListener('dragover', function(evnt) {
//				if (evnt.preventDefault) evnt.preventDefault();
//				evnt.dataTransfer.dropEffect = 'copy';
//				return false;	
//		}, false);
//		  
//		sourceArea.addEventListener('dragenter', function(evnt) {
//				return false;	
//		}, false);
//		  
//		sourceArea.addEventListener('dragleave', function(evnt) {
//				return false;
//		}, false);
//		  
//		sourceArea.addEventListener('drop', function(evnt) {
//			//if (evnt.stopPropagation) evnt.stopPropagation();
//			//var id = evnt.dataTransfer.getData('text');		
//			//var theitem = document.getElementById(id);	
//			////theitem.parentNode.removeChild(el);	
//			//theitem.className='itemblurred';
//			//var y  = document.createElement('img');
//			//y.src = theitem.src;
//			//sourceArea.appendChild(y);
//			//evnt.preventDefault(); // for Firefox
//			return false;
//		}, false);
//

}