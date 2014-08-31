var app = angular.module('hrAnalyzer');

app.directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
        elm.text(version);
    };
}]);

app.directive('gridify', ['$parse', '$compile', function($parse, $compile){
    return {
        restrict: 'E',
        replace: true,
        transclude: false,
        link: function(scope, element, attrs, controller) {
            var modelAccessor = attrs['ngModel'];
            var classes = attrs['ngClass'];
            scope.$watch(modelAccessor, function(val) {
                if(val){
                    var newEl = $compile('<div class="' + classes + '" ng-grid="gridOptions"></div>')(scope);
                    element.append(newEl);
                }
            });
        }
    }
}]);