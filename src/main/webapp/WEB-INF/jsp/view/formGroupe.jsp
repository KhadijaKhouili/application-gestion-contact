<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact app</title>
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
					    <a class="nav-link" href="${pageContext.request.contextPath}/showGroupeForm">Add groupes</a>
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
      
        <div>
			<h3 class="my-3 text-center">Registration Form</h3>
		</div>
		<c:if test="${infoMsg!=null}">
			<div class="alert alert-success" role="alert">${infoMsg}</div>
		</c:if>
		<c:if test="${errorMsg!=null}">
		    <div class="alert alert-danger" role="alert">${errorMsg}</div>
		</c:if>
        <f:form action="addGroupe" method="POST" modelAttribute="groupeModel">
  	    
				
            <div class="row">
                <input type="hidden" id="id">
                <div class="form-group col">
                    <label for="nomGroupe">Nom groupe</label>
                    <f:input path="nomGroupe" type="text" class="form-control" name="nomGroupe" id="nom" placeholder="nom du groupe"/>
                    <f:errors path="nomGroupe" class="text-danger" />
                </div>

            </div>
            <div class="col-12 d-flex justify-content-center">
                <button id="ajouter" type="submit" class="btn btn-primary col-2 mx-2">Enregistrer</button>
                <button type="reset" class="btn btn-secondary">Rest</button>
            </div>
        </f:form>
        <h3 class="my-3 text-center">Liste des groupes</h3>

        <table class="table table-light">
            <thead>
                <tr class="table-dark">
                    <th scope="col">#</th>
                    <th scope="col">Nom Groupe</th>
                </tr>
            </thead>
           
                <c:forEach items="${groupeList}" var="g">
					<tr>
						<th><c:out value="${g.idGroupe}" /></th>
						<td><c:out value="${g.nomGroupe}" /></td>
											
					</tr>
                </c:forEach>

        </table>
        </div>

</body>
</html>