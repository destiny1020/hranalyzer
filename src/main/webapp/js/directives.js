"use strict";

define(["angular", "services"], function(angular, services) {

    /* Directives */
    
    angular.module("hrAnalyzer.directives", ["hrAnalyzer.services"])
        .directive("appVersion", ["version", function(version) {
            return function(scope, elm, attrs) {
                elm.text(version);
        };
    }]);
});
