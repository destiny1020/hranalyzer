"use strict";

define(["angular", "app"], function(angular, app) {

    return app.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider) {
        // other cases
        $urlRouterProvider.otherwise("/employee");

        $stateProvider.state("division", {
            url: "/division",
            templateUrl: "partials/division.html",
            controller: "DivisionCtrl"
        });

        $stateProvider.state("department", {
            url: "/department",
            templateUrl: "partials/department.html",
            controller: "DepartmentCtrl"
        });

        $stateProvider.state("group", {
            url: "/group",
            templateUrl: "partials/group.html",
            controller: "GroupCtrl"
        });

        $stateProvider.state("team", {
            url: "/team",
            templateUrl: "partials/team.html",
            controller: "TeamCtrl"
        });

        $stateProvider.state("title", {
            url: "/title",
            templateUrl: "partials/title.html",
            controller: "TitleCtrl"
        });

        $stateProvider.state("rank", {
            url: "/rank",
            templateUrl: "partials/rank.html",
            controller: "RankCtrl"
        });

        // employee view
        $stateProvider.state("employee", {
            url: "/employee",
            templateUrl: "partials/employee.html",
            controller: "EmployeeCtrl"
        })

    }]);

});
