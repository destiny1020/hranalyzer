var app = angular.module('hrAnalyzer');

app.value('version', '0.1');

// HR basic data service
app.factory('hrData', ['Restangular', function(Restangular) {
    var _fetchingDivisions = false
    var _divisions = null;
    var _divisionNames = null;

    var divisions = function(data) {
        if(angular.isDefined(data)) {
            _divisions = data;
        } else {
            if(!_divisions) {
                // fetch the divisions
                if(!_fetchingDivisions) {
                    _fetchingDivisions = true;
                    fetchDivisions();
                }
            }
            return _divisions;
        }
    };

    function fetchDivisions() {
        var divisionEndpoint = Restangular.one('hr/division');
        divisionEndpoint.get().then(function(res) {
            divisions(res.data);

            var divisionOptions = _.map(res.data, function(record) {
                return {
                    id: record.content.id,
                    name: record.content.division
                };
            });
            divisionNames(divisionOptions);

            _fetchingDivisions = false;
        }, function(err) {
            $log.error('fetching division data error', err);
            _fetchingDivisions = false;
        });
    }

    var divisionNames = function(data) {
        if(angular.isDefined(data)) {
            _divisionNames = data;
        } else {
            return _divisionNames;
        }
    }

    return {
        divisions: divisions,
        divisionNames: divisionNames
    };
}]);
