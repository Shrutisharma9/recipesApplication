<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Recipe Application</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

</head>

<body data-new-gr-c-s-check-loaded="14.1087.0" data-gr-ext-installed="">

<main>

    <div class="container bg-light my-2">
        <div class="bd-example-row">
            <h1 class="display-3">Recipe Application!</h1>
            <p>This is the landing page to experience recipe application and to see the list of all recipes please first add some recipes and its ingredients by using
            <a href="http://localhost:9000/swagger-ui.html" rel="http://localhost:9000/swagger-ui.html">http://localhost:9000/swagger-ui.html</a></p>
        </div>
    </div>
<div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <div class="col-md-4 border-right">

            </div>
            <div class="col-md-4 border-right">
                <h2>Recipes List</h2>
                <p>To see all the available recipes please click below button.</p>

                <form method="get" action="/recipes">
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-2">Recipes List</button>
                    </div>
                </form>

                <div>
                    <#if recipes??>
                        <ul class="list-group">
                            <#list recipes as recipe>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    ${recipe.name!''}
                                    <#-- <span class="badge badge-primary badge-pill">${recipe.id?c!''}</span>-->
                                </li>
                            </#list>
                        </ul>
                    </#if>
                </div>
            </div>

            <div class="col-md-4">
            </div>
        </div>
        <hr>
    </div> <!-- /container -->

</main>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/bootstrap-autocomplete.min.js"></script>

<script>
    $('.basicAutoComplete').autoComplete({
        minLength: 2,
        resolverSettings: {
            url: 'searchAsync',
            queryKey: 'keyword'

        }
    });

</script>
</body>