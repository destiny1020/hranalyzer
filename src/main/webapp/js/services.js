"use strict";

define([
    "angular",
    "services/basicDataService"
], function (angular, hrBasicData) {
    return angular.module("hrAnalyzer.services", [])
        .value("version", "0.1")
        .factory("HRBasicData", hrBasicData);
});
