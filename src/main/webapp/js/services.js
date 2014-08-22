"use strict";

define([
    "angular",
    "services/basicDataService",
    "services/basicDataFetchService"
], function (angular, hrBasicData, hrBasicDataFetch) {
    return angular.module("hrAnalyzer.services", [])
        .value("version", "0.1")
        .factory("HRBasicData", hrBasicData)
        .factory("HRBasicDataFetch", hrBasicDataFetch);
});
