"use strict";

define([
    // angluar itself
    "angular",

    // 3rd lib
    "angularRouter",
    "angularBootstrap",
    "angularGrid",
    "angularSelect2",
    "restangular",

    // hrAnalyzer defined
    "filters",
    "services",
    "directives",
    "controllers"
    ], function (angular, filters, services, directives, controllers) {

        // Declare app level module which depends on filters, and services
        
        return angular.module("hrAnalyzer", [
            // ng router
            "ui.router",

            // restangular
            "restangular",

            // angular ui
            "ui.bootstrap",
            "ngGrid",
            "ui.select2",

            // hr analyzer defined modules
            "hrAnalyzer.filters",
            "hrAnalyzer.services",
            "hrAnalyzer.directives",
            "hrAnalyzer.controllers"
        ]);
});
