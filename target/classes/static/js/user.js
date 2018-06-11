var userApp = angular.module('userApp', ['ui.bootstrap']);

userApp.controller('userController', function($scope, $http) {

	$scope.saveUser = function(user){
		console.log('User Name '+user.name);
		console.log('User Name '+user.id);
		console.log('Sum '+JSON.stringify($scope.user));
		$http({
	        method : 'POST',
	        url : 'http://localhost:8080/saveorupdateuser',
	        contentType: 'application/json',
	        data : JSON.stringify($scope.user),
	    }).then(function(response){
	    	$scope.successMsg = "User Saved Sucessfully!"
	    	$scope.user = {};
	    	console.log('Response '+response.data);
	    });
	}
	
	$scope.getUsers = function(){
		$http.get('http://localhost:8080/users').then(function(response) {
			$scope.users = response.data;
			console.log('response data ' + JSON.stringify(response.data));
			$scope.successMsg = null;
		});
	};
	
	$scope.getSearchPopup = function(){
		$('#searchedPopUp').modal('show');
	};
	
	$scope.searchUser = function(searchUser){
		$http({
			method : 'GET',
			url : 'http://localhost:8080/searchUser',
			params : { user_id : searchUser.id}
		}).then(function(response){
			
				$scope.users = response.data;
				$scope.sucessMsg = "User Found!!";
				console.log('Response Data ->'+ JSON.stringify(response.data));
		})
	};
	
	$scope.editUser = function(result){
		$scope.editedUser = result;
		$('#editPopUp').modal('show');
	}
	
	$scope.updateUser = function(editedUser){
		console.log('User Name '+editedUser.name);
		console.log('User Name '+editedUser.id);
		console.log('Sum '+JSON.stringify(editedUser));
		$http({
	        method : 'POST',
	        url : 'http://localhost:8080/saveorupdateuser',
	        contentType: 'application/json',
	        data : JSON.stringify(editedUser),
	    }).then(function(response){
	    	$scope.successMsg = "User Updated Sucessfully!"
	    	console.log('Response '+response.data);
	    });
	}
	
	$scope.deleteUser = function(user){
		$http({
	        method : 'POST',
	        url : 'http://localhost:8080/deleteuser',
	        contentType: 'application/json',
	        data : JSON.stringify(user),
	    }).then(function(response){
	    	$scope.successMsg = "User Deleted Sucessfully!"
	    	console.log('Response '+response.data);
	    });
		var index = $scope.users.indexOf(user);
		$scope.users.splice(index, 1);
	}
	
});