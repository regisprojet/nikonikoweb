
function clearTargetArea() {
	console.log("clearTargetArea()");
	var targetArea = document.getElementById('roleTarget'); 
	if(targetArea==null) {
	 	console.log("targetArea = null, exit");
		 	return;
	}
	var rolesTarget = targetArea.querySelectorAll('.roleDaD');
	for(var elem in rolesTarget) {
		console.log(""+elem);
		elem.remove();
	}

}

function enableDragAndDrop() {
	
		console.log("enableDragAndDrop");
		
		var resetRoleButton = document.getElementById('resetRoleButton'); 
		resetRoleButton.addEventListener('click',
				function() {clearTargetArea();});

		if(document==null) {
			console.log("document is null");
			return;
		}
		//var targetArea = document.querySelector('#roleTarget'); 
		var targetArea = document.getElementById('roleTarget'); 
		if(targetArea==null) {
		 	console.log("targetArea = null, exit");
		 	return;
		}

		var sourceArea = document.getElementById('roleSource'); 
		if(sourceArea==null) {
		 	console.log("sourceArea = null, exit");
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
			y.addEventListener('dragend', function(event){
    			if(event.dataTransfer.dropEffect !== 'none'){
        		$(this).remove();
    		}
			});
			targetArea.appendChild(y);
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