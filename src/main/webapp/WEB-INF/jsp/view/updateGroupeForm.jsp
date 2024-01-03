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
		<f:form action="${pageContext.request.contextPath}/updateGroupe" method="POST" modelAttribute="groupeModel">
  	    
				<f:hidden path="idGroupe" />
            <div class="row">
                <input type="hidden" id="id">
                <div class="form-group col">
                    <label for="nomGroupe">Nom groupe</label>
                    <f:input path="nomGroupe" type="text" class="form-control" name="nomGroupe" id="nom" placeholder="nom du groupe"/>
                    <f:errors path="nomGroupe" class="text-danger" />
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