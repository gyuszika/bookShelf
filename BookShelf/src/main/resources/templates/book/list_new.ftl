[#ftl]
[#import "/spring.ftl" as spring /]
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Books List</title>
    <link  href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
</head>
[#escape x as x?html]
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Panel heading</div>
  <div class="panel-body">
    <p><a style="float:right" href="/book/add"><span class="glyphicon glyphicon-plus" aria-hidden="true"></a></p>
  </div>

  	<!-- Table -->
  
	<table class="table">
	<tr>
		<th>Book Name</th>
		<th>Author</th>
		<th>Language</th>
		<th>Pages</th>
		<th>Publication Year</th>
		<th>Book Location</th>
		<th>Book Owner</th>
		<th></th>
		
	</tr>
	<!-- begin iteration -->
	[#if books??]
		[#list books as book] 
			<tr>
				<td>${book.bookTitle}</td>
				<td>${book.author}</td>
				<td>${book.bookLanguage}</td>
				<td>${book.numberOfPages}</td>
				<td>${book.publicationYear}</td>
				<td>${book.bookLocation}</td>
				<td>${book.donatorEmail}</td>
				<td><a href="/book/edit?id=${book.id?c}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
			</tr>
		[/#list]
	[/#if]
	
	<!-- end iteration -->
	
	</table>
</div>



 	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>

[/#escape]
