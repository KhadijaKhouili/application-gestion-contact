<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.0/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.0/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="container" style="padding: 20px;">
       <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
					    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/showContactForm">Home</a>
					</li>
					<li class="nav-item"> 
					    <a class="nav-link" href="${pageContext.request.contextPath}/showContactForm">Add contacts</a>
					</li>
					<li class="nav-item">
					    <a class="nav-link" href="${pageContext.request.contextPath}/manageContacts">ManageContacts </a>
					</li>
					<li class="nav-item">
					    <a class="nav-link" href="${pageContext.request.contextPath}/manageGroupes">ManageGroupes </a>
					</li>
					<li class="nav-item ">
					   <form action="${pageContext.request.contextPath}/serachContact" class="d-flex" method="POST">
							<input name="valeurChercher" class="form-control me-2" type="search" placeholder="search" aria-label="Search">
                        <select class="form-control mx-2" id="search" name="search">
                             <option value="nom">Nom</option>
                             <option value="personnalNumber">personnal Number</option>
                             <option value="professionalNumber">professional Number</option>
                         </select>
							<button class="btn btn-outline-light mx-2" type="submit">Search</button>
						</form>
					</li>
				</ul>
			</div>
		</nav>
		<f:form action="${pageContext.request.contextPath}/updateContact" method="POST" modelAttribute="contactModel">
  	    
				<f:hidden path="idContact" />
            <div class="row">
                
                <div class="form-group col">
                    <label for="nom">Nom</label>
                    <f:input path="nom" type="text" class="form-control" name="nom" id="nom" placeholder="Votre nom"/>
                    <f:errors path="nom" class="text-danger" />
                </div>

                <div class="form-group col">
                    <label for="prenom">Prenom</label>
                    <f:input path="prenom" type="text" class="form-control" name="prenom" id="prenom" placeholder="Votre prenom"/>
                    <f:errors path="prenom" class="text-danger" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <label for="tele">Numero de telephone personnel</label>
                    <f:input path="personnalNumber" type="tel" class="form-control" name="tele" id="tele"  placeholder="Numero du telephone" />
                    <f:errors path="personnalNumber" class="text-danger" />
                </div>
                <div class="form-group col">
                    <label for="tele">Numero de telephone professionnel</label>
                    <f:input path="professionalNumber" type="tel" class="form-control" name="tele" id="tele"  placeholder="Numero du telephone" />
                    <f:errors path="professionalNumber" class="text-danger" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <label for="emailper">Email personnel</label>
                    <f:input path="Personnalemail" type="email" class="form-control" name="emailper" id="emailper" placeholder="name@example.com" />
                    <f:errors path="Personnalemail" class="text-danger" />
                </div>
                <div class="form-group col">
                    <label for="emailpro">Email professionnel</label>
                    <f:input path="Professionalemail" type="email" class="form-control" name="emailpro" id="emailpro" placeholder="name@example.com" />
                    <f:errors path="Professionalemail" class="text-danger" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <label for="addresse">Addresse</label>
                    <f:input path="address" type="text" class="form-control" name="addresse" id="addresse" placeholder="...., Morocco" />
                    <f:errors path="address" class="text-danger" />
                </div>
                 <div class="col">
                   <legend class="col-form-label col-sm-2 pt-0">Gender</legend>
						<div class="form-check">
							<f:radiobutton path="gender" class="form-check-input"
								value="Female" />
							<label class="form-check-label">Female </label>
						</div>
						<div class="form-check">
							<f:radiobutton path="gender" class="form-check-input"
								value="Male " />
							<label class="form-check-label">Male </label>
						</div>
						<f:errors path="gender" class="text-danger" />
                </div>
            
            </div>
            <div class="col-12 d-flex justify-content-center">
                <button  type="submit" class="btn btn-success col-2 mx-2">Update</button>
                <button type="reset" class="btn btn-secondary">Rest</button>
            </div>
        </f:form>
    </div>
</body>
</html>