function enableDragAndDrop() {
	
		console.log("enableDragAndDrop");
		
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
		 	console.log("targetArea = null, exit");
		 	return;
		}

		var prods = document.querySelectorAll('.roleDaD');
		//var prods = document.getElementsByClassName('roleDad');
		console.log("nombre d'éléments trouvés : "+prods.length);
		for(var i = 0; i < prods.length; i++)
		{
			prods[i].setAttribute('draggable', 'true');  // optional with images
			prods[i].addEventListener('dragstart', function(evnt) {
				this.className = 'itemchoosen';
				evnt.dataTransfer.effectAllowed = 'copy';
				evnt.dataTransfer.setData('text', this.id);
				return false;  
			}, false);
		}	
		
		
		targetArea.addEventListener('dragover', function(evnt) {
				if (evnt.preventDefault) evnt.preventDefault();
				evnt.dataTransfer.dropEffect = 'copy';
				return false;	
		}, false);
		  
		targetArea.addEventListener('dragenter', function(evnt) {
				return false;	
		}, false);
		  
		targetArea.addEventListener('dragleave', function(evnt) {
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
			targetArea.appendChild(y);
			evnt.preventDefault(); // for Firefox
			return false;
		}, false);

}