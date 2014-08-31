var app = angular.module('hrAnalyzer');

// employee controller
app.controller('employeeController', ['$scope', '$log', '$timeout', 'Restangular', 'hrData', function($scope, $log, $timeout, Restangular, hrData) {

        $scope.test = '';

        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [10, 20, 50],
            pageSize: 20,
            currentPage: 1
        };

        $scope.setPagingData = function(data, page) {
            $scope.currentEmployees = data;
            $scope.totalServerItems = page.totalElements;
        };

        $scope.getPagedDataAsync = function(pageSize, page, searchText) {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                // TODO
            } else {
                var employeeEndpoint = Restangular.one('hr/employee');
                employeeEndpoint.get({
                    page: page - 1,
                    size: pageSize
                }).then(function(res) {
                    // console.log(res.data);
                    $scope.setPagingData(res.data, res.page);
                }, function(err) {
                    $log.error('fetching employee data error', err);
                });
            }
        };

        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal !== oldVal) {
              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.columnDefs = [
            { field: 'eid', displayName: 'Employee ID', width: '10%' },
            { field: 'fullname', displayName: 'Name', witdh: '10%' },
            { field: 'fullname2', displayName: 'Name2', witdh: '10%' },
            { field: 'lid', displayName: 'Employee LID', witdh: '10%' },

            // current record
            { field: 'currentRecord.division.name', displayName: 'Division', width: '10%' },
            { field: 'currentRecord.department.name', displayName: 'Department', width: '10%' },
            { field: 'currentRecord.group.name', displayName: 'Group', width: '10%' },
            { field: 'currentRecord.team.name', displayName: 'Team', width: '10%' },
            { field: 'currentRecord.titleClass.name', displayName: 'Title', width: '10%' },
            { field: 'currentRecord.titleRank.name', displayName: 'Rank', width: '10%' }
        ]

        $scope.gridOptions = { 
            data: 'currentEmployees',
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            enableRowSelection: false,
            columnDefs: $scope.columnDefs
        };

        // grid options
        $scope.clickGridOptions = function() {
            // TODO
        };

        // get divisionNames from store
        $timeout(function() {
            $scope.divisionNames = hrData.divisionNames();
        }, 0);
    }]
);
