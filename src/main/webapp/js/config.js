'use strict';

var app = angular.module('hrAnalyzer');

app.config(['RestangularProvider', function(RestangularProvider) {
    // config the Restangular
    RestangularProvider.addResponseInterceptor(function (data, operation, what, url, response, deferred) {
        var extractedData = {};

        // console.log(data);

        if (operation === 'getList' || operation === 'get') {
            // process when the operation is getList
            if (data._embedded) {
                extractedData.data = data._embedded.data;
            } 

            if (data.page) {
                extractedData.page = data.page;
            }

            if (data.contents) {
                extractedData.data = data.contents;
            }

            // paging information
            if (data.totalCount) {
                extractedData.totalCount = data.totalCount;
            }

            if (data.totalPages) {
                extractedData.totalPages = data.totalPages;
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
        controller: 'employeeController',
        resolve: {
            // this divisions can be injected into the employeeController, but here just store the data into service
            // in order to decouple the data from directly injected into controller
            // refer to: http://stackoverflow.com/questions/11972026/delaying-angularjs-route-change-until-model-loaded-to-prevent-flicker/11972028#11972028
            divisions: ['$q', '$log', 'Restangular', 'hrData', function($q, $log, Restangular, hrData) {
                var divisionEndpoint = Restangular.one('hr/division');
                var deferred = $q.defer();
                divisionEndpoint.get().then(function(res) {
                    hrData.divisions(res.data);
                    var divisionOptions = _.map(res.data, function(record) {
                        // create mappings from division id ---> arrays of departments[id, name]
                        hrData.addDivisionToDepartment(record.content.id, record.content.departments);

                        return {
                            id: record.content.id,
                            name: record.content.division
                        };
                    });
                    hrData.divisionNames(divisionOptions);

                    deferred.resolve(res.data);
                }, function(err) {
                    $log.error('fetching division data error', err);

                    deferred.reject();
                });

                return deferred.promise;
            }]
        }
    })
}]);