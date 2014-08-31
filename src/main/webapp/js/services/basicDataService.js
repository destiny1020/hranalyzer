var app = angular.module('hrAnalyzer');

app.value('version', '0.1');

// HR basic data service
app.factory('hrData', [function() {
    var _divisions = null;

    var divisions = function(data) {
        if(angular.isDefined(data)) {
            _divisions = data;
        } else {
            return _divisions;
        }
    };

    return function() {
        return {
            divisions: divisions
        }
    };
}]);
