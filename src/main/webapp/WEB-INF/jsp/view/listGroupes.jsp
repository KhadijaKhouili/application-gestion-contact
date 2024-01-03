<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<title>Contact app</title>
<style>
        .custom-modal .modal-dialog {
  max-width: 1000px;
  width: 80%;
}
        
        .modal-header {
            background: #F7941E;
            color: #fff;
        }
        
        .required:after {
            content: "*";
            color: red;
        }
    </style>
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
					   <form action="${pageContext.request.contextPath}/serachGroupe" class="d-flex" method="POST">
							<input name="valeurChercher" class="form-control me-2" type="search" placeholder="search by name" aria-label="Search">
                    
							<button class="btn btn-outline-light mx-2" type="submit">Search</button>
						</form>
					</li>
				</ul>
			</div>
		</nav>
      
        <div>
			<h3 class="my-3 text-center">Manangement des groupes</h3>
		</div>
		<c:if test="${infoMsg!=null}">
			<div class="alert alert-success" role="alert">${infoMsg}</div>
		</c:if>
		<c:if test="${errorMsg!=null}">
		    <div class="alert alert-danger" role="alert">${errorMsg}</div>
		</c:if>

        <h3 class="my-3 text-center">Liste des groupes</h3>

        <table class="table table-light">
            <thead>
                <tr class="table-dark">
                    <th scope="col">#</th>
                    <th scope="col">Nom Groupe</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
           
                <c:forEach items="${groupeList}" var="g">
					<tr>
						<th><c:out value="${g.idGroupe}" /></th>
						<td><c:out value="${g.nomGroupe}" /></td>
						<td>
						   <a class="btn btn-success mx-1" href="updateGroupeForm/${g.idGroupe}" role="button">Update</a>
						   <a class="btn btn-danger mx-1" href="deleteGroupe/${g.idGroupe}" role="button">Delete</a>
						   <a class="btn btn-primary mx-1" href="/groupeContacts/${g.idGroupe}" role="button">Contacts of group <c:out value="${g.nomGroupe}" /></a>
						   <a class="btn btn-secondary mx-1" href="/AddContactsAuGroupe/${g.idGroupe}" role="button">Add contact</a>
						   
						   <!--<a class="btn btn-primary mx-1" href="#" role="button" data-bs-toggle="modal" data-bs-target="#myModal">contact</a>
						   
						   <div class="container mt-5">
						   
                               <div class="modal fade custom-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="popupTitle" aria-hidden="true">
                                   <div class="modal-dialog">
                                        <div class="modal-content">
                                             <div class="modal-header">
                                                     <h5 class="modal-title">Contacts du groupe <c:out value="${g.nomGroupe}" /></h5>
                                                     <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                             </div>
                                             <div class="modal-body">
                                                    <table class="table table-light">
            <thead>
                <tr class="table-dark">
                    <th scope="col">#</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Email personnel</th>
                    <th scope="col">Email professionnel</th>
                    <th scope="col">Tel personnel</th>
                    <th scope="col">Tel professionnel</th>
                    <th scope="col">Adresse</th>
                    <th scope="col">Genre</th>
                    
                </tr>
            </thead>
           
                <c:forEach items="${contactList}" var="contact">
					<tr>
						<th><c:out value="${contact.idContact}" /></th>
						<td><c:out value="${contact.nom}" /></td>
						<td><c:out value="${contact.prenom}" /></td>
						<td><c:out value="${contact.personnalemail}"/></td>
						<td><c:out value="${contact.professionalemail}" /></td>
						<td><c:out value="${contact.personnalNumber}" /></td>
						<td><c:out value="${contact.professionalNumber}" /></td>
						<td><c:out value="${contact.address}" /></td>
						<td><c:out value="${contact.gender}" /></td>
						<td><f:form method="POST" action="/groupes/${g.idGroupe}/ajouterContact/${contact.idContact}" modelAttribute="groupeModel">
                               <f:hidden path="idGroupe" />
                             
                               <button class="btn btn-success mx-1" type="submit">Choisir</button>
                            </f:form>
                         </td>
							
					</tr>
                </c:forEach>

        </table>
                                            </div>
                                            <div class="modal-footer">
                                                 <button type="submit" class="btn btn-primary">Submit</button>
                                                 <button type="submit" class="btn btn-danger">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>-->
                        </td>
											
					</tr>
                </c:forEach>

        </table>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>