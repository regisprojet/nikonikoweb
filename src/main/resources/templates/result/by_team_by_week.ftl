<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="../../../js/histogram.js"></script>

</head>
<body>
<h1> résultat équipe (${teamname}) par semaine </h1>

  <canvas id="canvas0" width="92" height="200"></canvas>

  <script type="text/javascript">
    var d = new Date();
    var n = d.getDay();
   
    var day_french_name = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];


    var body = document.getElementsByTagName('body')[0];


    var span1 = document.createElement("span");

    body.appendChild(span1);
    

    for(var i=0;i<7;i++) {
      var span = document.createElement("span");  
      //var spanText = document.createElement("span");
      //spanText.innerText=day_french_name[i];  
      //span.appendChild(spanText);
      
      var canvas = document.createElement("canvas");
      canvas.setAttribute("id","canvas"+(i+1));
      canvas.setAttribute("width","92");
      canvas.setAttribute("height","200");
      span.appendChild(canvas);
      console.log("canvas"+(i+1));
      span1.appendChild(span);
    }

    draw_histogram_week(${greens[0]},${yellows[0]},${reds[0]}, "canvas1" , day_french_name[0],0);
    draw_histogram_week(${greens[1]},${yellows[1]},${reds[1]}, "canvas2" , day_french_name[1],1);
    draw_histogram_week(${greens[2]},${yellows[2]},${reds[2]},  "canvas3" , day_french_name[2],2);
    draw_histogram_week(${greens[3]},${yellows[3]},${reds[3]}, "canvas4" , day_french_name[3],3);
    draw_histogram_week(${greens[4]},${yellows[4]},${reds[4]}, "canvas5" , day_french_name[4],4);
    draw_histogram_week(${greens[5]},${yellows[5]},${reds[5]}, "canvas6" , day_french_name[5],5);
    draw_histogram_week(${greens[6]},${yellows[6]},${reds[6]}, "canvas7" , day_french_name[6],6);
  </script>
 </body>


</html>