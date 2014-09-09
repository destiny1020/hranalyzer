var app = angular.module('hrAnalyzer');

// employee controller
app.controller('employeeController', ['$scope', '$log', '$timeout', 'Restangular', 'hrData', function($scope, $log, $timeout, Restangular, hrData) {

        // search dto
        $scope.searchDto = {
            d: 0,
            de: 0,
            g: 0,
            t: 0
        };

        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [10, 20, 50],
            pageSize: 20,
            currentPage: 1
        };

        $scope.setPagingData = function(data, totalCount) {
            $scope.currentEmployees = data;
            $scope.totalServerItems = totalCount;
        };

        $scope.getPagedDataAsync = function(searchText) {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                // TODO
            } else {
                var employeeEndpoint = Restangular.one('hr/employee');
                employeeEndpoint.customGET('search', assembleSearchDto($scope.searchDto)).then(function(res) {
                    $scope.setPagingData(res.data, res.totalCount);
                }, function(err) {
                    $log.error('fetching employee data error', err);
                });
            }
        };

        $scope.getPagedDataAsync();

        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal !== oldVal) {
              $scope.getPagedDataAsync();
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
        ];

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
        $scope.divisionNames = hrData.divisionNames();

        $scope.$watch("searchDto.d", function(newVal, oldVal) {
            // fetch the corresponding department data
            if(newVal) {
                $scope.departmentNames = hrData.getDepartments(newVal);
            }
        });

        $scope.$watch("searchDto.de", function(newVal, oldVal) {
            // fetch the corresponding department data
            if(newVal) {
                hrData.fetchGroups(newVal).then(function() {
                    $scope.groupNames = hrData.getGroupNames();
                });
            }
        });

        $scope.$watch("searchDto.g", function(newVal, oldVal) {
            // fetch the corresponding team data
            if(newVal) {
                $scope.teamNames = hrData.getTeams(newVal);
            }
        });

        // search funcion
        $scope.searchEmployees = function(searchDto) {
            // reset current page to 1
            $scope.pagingOptions.currentPage = 1;

            $scope.getPagedDataAsync();
        };

        // clear search criteria
        $scope.clearCriteria = function() {
            $scope.searchDto.d = 0;
            $scope.searchDto.de = 0;
            $scope.searchDto.g = 0;
            $scope.searchDto.t = 0;
        };

        function assembleSearchDto(searchDto) {
            searchDto.p = $scope.pagingOptions.currentPage - 1;
            searchDto.si = $scope.pagingOptions.pageSize;

            return searchDto;
        }
    }]
);
