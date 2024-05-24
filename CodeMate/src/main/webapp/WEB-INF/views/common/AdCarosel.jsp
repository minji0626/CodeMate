<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bootstrap</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<div class="container">
	
	<div id="ad_carousel" class="carousel slide" 
	     data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#ad_carousel" 
			              data-bs-slide-to="0" class="active"></button>
			<button type="button" data-bs-target="#ad_carousel" 
			              data-bs-slide-to="1"></button>
			<button type="button" data-bs-target="#ad_carousel" 
			              data-bs-slide-to="2"></button>  
				                                        
		</div>
		<div class="carousel-inner">
		
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/landscape.jpg" class="d-block w-100">
			</div>
			
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/sample2.jpg" class="d-block w-100">
			</div>
			
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/sample3.jpg" class="d-block w-100">
			</div>
			
		</div>
		<button class="carousel-control-prev" type="button"
		                 data-bs-target="#ad_carousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon"></span>
			<span class="visually-hidden">Previous</span>                             
		</button>
		<button class="carousel-control-next" type="button"
		                 data-bs-target="#ad_carousel" 
		                             data-bs-slide="next">
			<span class="carousel-control-next-icon"></span>
			<span class="visually-hidden">Next</span>                             
		</button>
	</div>
	
	
	
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
