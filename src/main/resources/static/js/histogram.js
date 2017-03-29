function draw_histogram_week(n1,n2,n3,canvasId,dayName,id) 
{
	var total = n1+n2+n3;

	var canvas = document.getElementById(canvasId);
	if(canvas==null) {
		return;
	}
	var ctx = canvas.getContext("2d");

    ctx.beginPath();

    ctx.lineWidth = 3;
	ctx.strokeStyle = 'black';
	if(id%2==1) {
		ctx.fillStyle = "lightgrey";
	}
	else {
		ctx.fillStyle = "gray";	
	}
    
	ctx.fillRect(0, 0, 100, 200);
	ctx.fillStyle = "green";
	ctx.fillRect(0, 200 - scale(n1), 25, scale(n1));
    ctx.fillStyle = "yellow";
    ctx.fillRect(25, 200 - scale(n2) , 25, scale(n2));
    ctx.fillStyle = "red";
    ctx.fillRect(50, 200 - scale(n3), 25, scale(n3));    

	ctx.fillStyle = "black";
	ctx.rect(1, 200 - scale(n1), 25, scale(n1));
    ctx.rect(26, 200 - scale(n2) , 25, scale(n2));
    ctx.rect(51, 200 - scale(n3), 25, scale(n3));


    ctx.fillText(dayName,10,10);
    ctx.stroke();
}

function scale(n) {
	return 90.0*Math.log10(2.0*n);
}

