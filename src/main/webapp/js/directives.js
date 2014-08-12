"use strict";

define(["angular", "services"], function(angular, services) {

    /* Directives */
    
    angular.module("hrAnalyzer.directives", ["hrAnalyzer.services"])
        .directive("appVersion", ["version", function(version) {
            return function(scope, elm, attrs) {
                elm.text(version);
            };
        }])

        .directive("gridify", ["$parse", "$compile", function($parse, $compile){
            return {
                restrict: 'E',
                replace: true,
                transclude: false,
                link: function(scope, element, attrs, controller) {
                    var modelAccessor = attrs["ngModel"];
                    scope.$watch(modelAccessor, function(val) {
                        if(val){
                            var newEl = $compile("<div class='gridStyle' ng-grid='gridOptions'></div>")(scope);
                            element.append(newEl);
                        }
                    });
                }
            }
        }]);
});
