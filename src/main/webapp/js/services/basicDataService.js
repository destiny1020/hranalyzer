// HR basic data service
define([], function() {
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
});