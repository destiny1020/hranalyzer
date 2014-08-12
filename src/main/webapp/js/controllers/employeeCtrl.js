// employee controller
define([], function() {
    return ["$scope", "$log", "Restangular", function($scope, $log, Restangular) {

        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [10, 20, 50],
            pageSize: 20,
            currentPage: 1
        };

        $scope.setPagingData = function(data, page) {
            $scope.currentEmployees = data;
            $scope.totalServerItems = page.totalElements;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };

        $scope.getPagedDataAsync = function(pageSize, page, searchText) {
            setTimeout(function () {
                var data;
                if (searchText) {
                    var ft = searchText.toLowerCase();
                    // TODO
                } else {
                    var employeeEndpoint = Restangular.one("hr/employee");
                    employeeEndpoint.get({
                        page: page - 1,
                        size: pageSize
                    }).then(function(res) {
                        $scope.setPagingData(res.data, res.page);
                    }, function(err) {
                        $log.error("fetching employee data error", err);
                    });
                }
            }, 0);
        };

        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

        $scope.$watch("pagingOptions", function (newVal, oldVal) {
            if (newVal !== oldVal) {
              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.gridOptions = { 
            data: "currentEmployees",
            enablePaging: true,
            showFooter: true,
            totalServerItems: "totalServerItems",
            pagingOptions: $scope.pagingOptions
        };

        // $scope.$apply();
    }];
});
