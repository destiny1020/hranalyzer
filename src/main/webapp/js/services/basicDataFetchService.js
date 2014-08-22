// HR basic data fetching service
define(["restangular"], function(Restangular) {

    var fetchDivisions = function() {
        console.log(Restangular);
    };

    return function() {
        return {
            fetchDivisions: fetchDivisions
        }
    };
});