'use strict';

var app = angular.module('hrAnalyzer');

app.config(['RestangularProvider', function(RestangularProvider) {
    // config the Restangular
    RestangularProvider.addResponseInterceptor(function (data, operation, what, url, response, deferred) {
        var extractedData = {};

        if (operation === 'getList' || operation === 'get') {
            // process when the operation is getList
            if (data._embedded) {
                extractedData.data = data._embedded.data;
            } 

            if (data.page) {
                extractedData.page = data.page;
            }

            if (data._links) {
                extractedData.links = data._links;
            }
        } else if (operation === 'post') {
            extractedData.data = data;
            extractedData.link = response.headers('Location');
        } else {
            extractedData.data = data;
        }
        
        return extractedData;
    });
}]);

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    // other cases
    $urlRouterProvider.otherwise('/employee');

    $stateProvider.state('division', {
        url: '/division',
        templateUrl: 'partials/division.html',
        controller: 'divisionController'
    });

    $stateProvider.state('department', {
        url: '/department',
        templateUrl: 'partials/department.html',
        controller: 'departmentController'
    });

    $stateProvider.state('group', {
        url: '/group',
        templateUrl: 'partials/group.html',
        controller: 'groupController'
    });

    $stateProvider.state('team', {
        url: '/team',
        templateUrl: 'partials/team.html',
        controller: 'teamController'
    });

    $stateProvider.state('title', {
        url: '/title',
        templateUrl: 'partials/title.html',
        controller: 'titleController'
    });

    $stateProvider.state('rank', {
        url: '/rank',
        templateUrl: 'partials/rank.html',
        controller: 'rankController'
    });

    // employee view
    $stateProvider.state('employee', {
        url: '/employee',
        templateUrl: 'partials/employee.html',
        controller: 'employeeController'
    })
}]);