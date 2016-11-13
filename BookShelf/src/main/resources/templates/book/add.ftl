[#ftl]
[#import "/spring.ftl" as spring /]
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Books List</title>
    <link  href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>
	
	
	<script>
	function goToBooksList() {
		window.location.href = '/book';
	}
	</script>
</head>
[#escape x as x?html]


<div class="container">
		<a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>


		<ol class="breadcrumb">
			<li><a href="/">Home</a></li>
			<li class="active">Books</li>
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">[#if book.id??]Edit[#else]Add[/#if] Book</h3>
			</div>


			<div class="panel-body">
			
			    [#if errors??]
				    <div>
				        <ul>
				            [#list errors as error]
				            <br>
				                <b style="color:red">
				                [#if error.field??]${error.field}: [/#if]${error.defaultMessage}
				                </b>
				            [/#list]
				        </ul>
				    </div>
				[/#if]
			    
			    
				<form action="/book/save" method="POST">
				<input type="hidden" name="id" value="[#if book.id??]${book.id?c}[/#if]">
					<div class="form-group">
						<label for="bookTitle">Book Title</label> 
						<input type="text"
							class="form-control" id="bookTitle" name="bookTitle"
							placeHolder="Book Title" value="${book.bookTitle!''}"/>
					</div>
					
					<div class="form-group">
						<label for="author">Book Author</label> 
						<input type="text"
							class="form-control" id="author" name="author"
							placeHolder="Book Author" value="${book.author!''}"/>
					</div>
					
					<div class="form-group">
						<label for="bookLanguage">Book Language</label> 
						<input type="text"
							class="form-control" id="bookLanguage" name="bookLanguage"
							placeHolder="Book Language" value="${book.bookLanguage!''}"/>
					</div>
					
					<div class="form-group">
						<label for="numberOfPages">Pages</label> 
						<input type="number"
							class="form-control" id="numberOfPages" name="numberOfPages"
							placeHolder="Pages" value="${book.numberOfPages!''}"/>
					</div>
					
					<div class="form-group">
						<label for="publicationYear">Publication Year</label> 
						<input type="number"
							class="form-control" id="publicationYear" name="publicationYear"
							placeHolder="Publication Year" value="${book.publicationYear!''}"/>
					</div>
					
					<div class="form-group">
						<label for="bookLocation">Book Location</label> 
						<input type="text"
							class="form-control" id="bookLocation" name="bookLocation"
							placeHolder="Book Location" value="${book.bookLocation!''}"/>
					</div>
					
					<div class="form-group">
						<label for="donatorEmail">Owner email</label> 
						<input type="text"
							class="form-control" id="donatorEmail" name="donatorEmail"
							placeHolder="name@address.com" value="${book.donatorEmail!''}"/>
					</div>
		

					<div class="container-fluid">
						<div class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
							
								<li><button type="submit" class="btn btn-default"
										onclick="javascript:goToBookList();return false">Cancel</button></li>
								<li>&nbsp;&nbsp;&nbsp;</li>
								<li><button type="submit" class="btn btn-default">Save</button></li>
								
							</ul>
						</div>
					</div>
					<br /> <input type="hidden" class="form-control" id="id" value="0" />
			</form>
			</div>
		</div>
	</div>

[/#escape]