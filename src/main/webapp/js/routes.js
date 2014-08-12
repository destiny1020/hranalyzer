"use strict";

define(["angular", "app"], function(angular, app) {

    return app.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider) {
        // other cases
        $urlRouterProvider.otherwise("/state1");

        $stateProvider.state("state1", {
            url: "/view1",
            templateUrl: "partials/partial1.html",
            controller: "MyCtrl1"
        });

        $stateProvider.state("state2", {
            url: "/view2",
            templateUrl: "partials/partial2.html",
            controller: "MyCtrl2"
        });

    }]);

});
