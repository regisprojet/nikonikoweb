<html>
  <body>
	<div class="container" id="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="row" id="connect">
					<div class="col-sm-1">
					</div>
					<div class="col-sm-10" id="NikoTitle">
						<!--img src='images/bandeau.png' alt='' id="logo"-->
						<span>CGI NikoNiko</span>
					</div>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="row">
				<div class="col-sm-1"></div>
					<div class="col-sm-10">
			 			<form  ENCTYPE="multipart/form-data" method="post" action="">
					  		<div id="connectezVous">
				  				<span >Connectez-vous :</span>
							</div>
							<div class="formField">
				  				<span >Utilisateur :</span>
							</div>
							<div class="inputText">
				  				<input  type="texte" name="username">
							</div>
							<div class="formField">
				  				<span >Mot de passe :</span>
							</div>
							<div class="inputText">
				  				<input type="password" name="password">
							</div>
							<div class="formField">
								<input type="submit" value="valider"/>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			  			</form>
					</div>
		  		</div>
			</div>
		</div>
	</div>
	<style>
		body {
		 	text-align: center;
		 	color: #E11836;
		}
		#container {
			margin: 20px 20px 20px 20px;
			padding : 20px 20px 20px 20px;
			border: 15px solid lightgrey;
			color: #E11836;
		}
		#connectezVous {
		 	font-size:35px;
		 	font-weight: bolder;
		 	margin: 10px 20px 10px 20px;
		 	color: #E11836;
		 }

		#NikoTitle {
		 	border-bottom: 5px solid #E11836;
			padding-top: 10px;
		 	padding-bottom: 20px;
		 	font-size:80px;
		 	color: #E11836;
		 }

		.formField {
		 	font-size:20px;
		 	font-weight: bold;
		 	outline-color : #E11836;
		 	margin: 10px 20px 00px 20px;
		 	color: #E11836;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
  </body>
</html>
