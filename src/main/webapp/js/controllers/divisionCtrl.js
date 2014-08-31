var app = angular.module('hrAnalyzer');

// division controller
app.controller('divisionController', ['$scope', '$log', '$timeout', 'Restangular', 'hrData', function($scope, $log, $timeout, Restangular, hrData) {

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
            // store the divisions data
            hrData.divisions(data);
        };

        // no need to use paging func
        $scope.getDataAsync = function() {
            // check whether data is already stored
            var data = hrData.divisions();
            if(data) {
                $scope.setData(data);
            } else {
                $log.error("retrieve divisions from store failed");
            }
        };

        // retrieve data at once, waiting the app.run finish the fetching
        $timeout(function() {
            $scope.getDataAsync();
        }, 0);

        // grid options
        $scope.clickGridOptions = function() {
            // TODO
        };

        // click the departments
        $scope.expandDepartments = function(row) {
            if(!$scope.divisions) {
                // fetch the departments and store
            }
        };
    }]
);
