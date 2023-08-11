angular.module("myApp",[])
 .controller('RosterAngularController', function($scope, $http) {
    $scope.departmentNameForCourses = null;
    $scope.departmentNameForStudents = null;
    $scope.departmentCourses = null;
    $scope.departmentStudents = null;


	$http.get('/courses').then(function(response) {
			$scope.courses = response.data;
		},
		function(response) {
			alert('Http Courses Error');
		}
	);
	$http.get('/students').then(function(response) {
    			$scope.students = response.data;
    		},
    		function(response) {
    			alert('Http Students Error');
    		}
    	);

    $scope.getCoursesOfDepartment = function(departmentId, departmentName) {
        console.log('/department-courses/' + departmentId);
        $scope.departmentCourses = null;
        $scope.departmentNameForCourses = departmentName;
        $http.get('/department-courses/' + departmentId).then(function(response) {
            			$scope.departmentCourses = response.data;
            			console.log($scope.departmentCourses);
            		},
            		function(response) {
            			alert('Http Error');
            		}
            	);
    }

    $scope.getStudentsOfDepartment = function(departmentId, departmentName) {
            console.log('/department-students/' + departmentId);
            $scope.departmentStudents = null;
            $scope.departmentNameForStudents = departmentName;
            $http.get('/department-students/' + departmentId).then(function(response) {
                			$scope.departmentStudents = response.data;
                			console.log($scope.departmentStudents);
                		},
                		function(response) {
                			alert('Http Error');
                		}
                	);
        }

})
