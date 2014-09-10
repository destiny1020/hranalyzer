var app = angular.module('hrAnalyzer');

app.value('version', '0.1');

// HR basic data service
app.factory('hrData', ['$log', '$q', 'Restangular', function($log, $q, Restangular) {
    var _hrEndpoint = Restangular.one('hr');
    var _divisions = null;
    var _divisionNames = null;

    var divisions = function(data) {
        if(angular.isDefined(data)) {
            _divisions = data;
        } else {
            return _divisions;
        }
    };

    var divisionNames = function(data) {
        if(angular.isDefined(data)) {
            _divisionNames = data;
        } else {
            return _divisionNames;
        }
    };

    var _divisionToDepartments = {};
    var _addDivisionToDepartment = function(divisionId, deparments) {
        _divisionToDepartments[divisionId] = deparments;
    };

    var _getDepartments = function(divisionId) {
        return _divisionToDepartments[divisionId];
    };

    
    var _departmentToGroups = {};
    var _groupNames = null;
    var _fetchGroups = function(departmentId) {
        var deferred = $q.defer(); 
        _hrEndpoint.customGET('grouping', {
            d: departmentId
        }).then(function(res) {
            var groups = res.data;

            // correlate department id with groups
            _departmentToGroups[departmentId] = groups;

            var groupNames = _.map(groups, function(group) {
                // create mappings from group id ---> arrays of teams[id, name]
                _addGroupToTeam(group.id, group.teams);

                return {
                    id: group.id,
                    name: group.name
                };
            });

            // store into the groupNames
            _groupNames = groupNames;

            deferred.resolve();
        }, function(err) {
            $log.error('fetching grouping data by department id error', err);
            deferred.reject();
        });

        return deferred.promise;
    };

    var _getGroupNames = function() {
        return _groupNames;
    };

    var _groupToTeams = {};
    function _addGroupToTeam(groupId, teams) {
        _groupToTeams[groupId] = teams;
    }

    // teams related
    var _getTeams = function(groupId) {
        return _groupToTeams[groupId];
    };

    // title class related
    var _titleClassToTitleRank = {};
    var _titleClasses = null;
    var _titleClassNames = null;

    var titleClasses = function(data) {
        if(angular.isDefined(data)) {
            _titleClasses = data;
        } else {
            return _titleClasses;
        }
    };

    var titleClassNames = function(data) {
        if(angular.isDefined(data)) {
            _titleClassNames = data;
        } else {
            return _titleClassNames;
        }
    };

    var addTitleClassToTitleRanks = function(titleClassId, titleRanks) {
        _titleClassToTitleRank[titleClassId] = titleRanks;
    };

    // title rank related
    var _titleRankNames = null;
    var titleRankNames = function(data) {
        if(angular.isDefined(data)) {
            _titleRankNames = data;
        } else {
            return _titleRankNames;
        }
    };

    var getTitleRanks = function(titleClassId) {
        return _titleClassToTitleRank[titleClassId];
    };

    var appendTitleRankNames = function(data) {
        if(angular.isDefined(data)) {
            if(_titleRankNames === null) {
                _titleRankNames = [];
            }
            _titleRankNames = _titleRankNames.concat(data);
        } else {
            return _titleRankNames;
        }
    };

    return {
        divisions: divisions,
        divisionNames: divisionNames,

        // division id ---> departments
        addDivisionToDepartment: _addDivisionToDepartment,
        getDepartments: _getDepartments,

        // group API
        fetchGroups: _fetchGroups,
        getGroupNames: _getGroupNames,

        // teams API
        getTeams: _getTeams,

        // title class API
        titleClasses: titleClasses,
        titleClassNames: titleClassNames,
        addTitleClassToTitleRanks: addTitleClassToTitleRanks,

        // title rank API
        titleRankNames: titleRankNames,
        getTitleRanks: getTitleRanks,
        appendTitleRankNames: appendTitleRankNames
    };
}]);
