'use strict';

var app = angular.module('hrAnalyzer', [
            // ng router
            'ui.router',

            // restangular
            'restangular',

            // angular ui
            'ui.bootstrap',
            'ngGrid',
            'ui.select2'
        ]);

app.run(['hrData', function(hrData) {
    // trigger the division fetching
    hrData.divisions();
}]);