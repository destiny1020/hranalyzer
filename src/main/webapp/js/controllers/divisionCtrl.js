var app = angular.module('hrAnalyzer');

// division controller
app.controller('divisionController', ['$scope', '$log', 'Restangular', 'HRBasicData', 'HRBasicDataFetch', function($scope, $log, Restangular, HRBasicData, HRBasicDataFetch) {

        $scope.columnDefs = [
            { field: 'content.division', displayName: 'Division', width: '50%' },
            { 
                field: 'content.departmentsCount', 
                displayName: 'Department Count', 
                witdh: '50%',
                cellTemplate: 'partials/cellTemplates/divisionDepartmentCell.html'
            }
        ]

        $scope.gridOptions = { 
            data: 'currentDivisions',
            showFooter: true,
            totalServerItems: 'totalServerItems',
            enableRowSelection: false,
            columnDefs: $scope.columnDefs
        };

        $scope.setData = function(data) {
            $scope.currentDivisions = data;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };

        // no need to use paging func
        $scope.getDataAsync = function() {
            var divisionEndpoint = Restangular.one('hr/division');
            divisionEndpoint.get().then(function(res) {
                $scope.setData(res.data);
            }, function(err) {
                $log.error('fetching division data error', err);
            });
        };

        // retrieve data at once
        $scope.getDataAsync();

        // $scope.$apply();

        // grid options
        $scope.clickGridOptions = function() {
            // TODO
        };

        // click the departments
        $scope.expandDepartments = function(row) {
            HRBasicDataFetch.fetchDivisions();
        };

        // retrieve the divisions data
        $scope.divisions = HRBasicData.divisions();
    }]
);
