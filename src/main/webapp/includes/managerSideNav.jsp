<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

    </head>
    <body>



        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>Management Portal</h3>
                    <img class="img-circle" src="https://www.jamf.com/jamf-nation/img/default-avatars/generic-user-purple.png" alt="manager user" height="75" width="75">
            <p class="welcome"><i class="fa fa-key"></i> Logged in as</p>
                <span class="last-name">${firstName} </span>
                <a href="/logout"><i class="fa fa-sign-out"></i></a>      
                </div>

                <ul class="list-unstyled components">
                    <li class="nav-search">
			            <form class="navbar-form">
			                <div class="input-group">
			                    <input type="text" class="form-control" placeholder="Search">
			                    <div class="input-group-btn">
			                        <button class="btn btn-default" type="submit">
			                            <i class="glyphicon glyphicon-search"></i>
			                        </button>
			                    </div>
			                </div>
			            </form>
			        </li>
			        <li>
                        <a href="/"><i class="fa fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                    		
                        <a href="#usersSubmenu" data-toggle="collapse" aria-expanded="false"><i class="fa fa-users"></i> Users <i class="fa fa-caret-down pull-right"></i></a>
                        <ul class="collapse list-unstyled" id="usersSubmenu">
                       		<li><a href="/voterApproval"><i class="fa fa-angle-double-right"></i> Approve Voter Applications</a></li>
                            <li><a href="/voterlist"><i class="fa fa-angle-double-right"></i> Users</a></li>
                            <li><a href="voterlist"><i class="fa fa-angle-double-right"></i> Voters</a></li>
                            <li><a href="#"><i class="fa fa-angle-double-right"></i> Managers</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#analyticsSubmenu" data-toggle="collapse" aria-expanded="false"><i class="fa fa-bar-chart-o"></i> Analytics <i class="fa fa-caret-down pull-right"></i></a>
                        <ul class="collapse list-unstyled" id="analyticsSubmenu">
                        	<li><a href="/votes"><i class="fa fa-angle-double-right"></i> Review Reports</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pollsSubmenu" data-toggle="collapse" aria-expanded="false"><i class="fa fa-edit"></i> Polls <i class="fa fa-caret-down pull-right"></i></a>
                        <ul class="collapse list-unstyled" id="pollsSubmenu">
                            <li><a href="/poll"><i class="fa fa-angle-double-right"></i> Add a Poll</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

           




        <!-- jQuery CDN -->
         <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

         <script type="text/javascript">
             $(document).ready(function () {
                 $('#sidebarCollapse').on('click', function () {
                     $('#sidebar').toggleClass('active');
                     $(this).toggleClass('active');
                 });
             });
         </script>
    </body>
</html>
