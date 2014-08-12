"use strict";

require.config({
    paths: {
        angular: "../lib/angular/angular",
        angularMocks: "../lib/angular-mocks/angular-mocks",
        text: "../lib/requirejs-text/text",

        jquery: "../lib/jquery/dist/jquery.min",

        // restangular
        lodash: "../lib/lodash/dist/lodash.min",
        restangular: "../lib/restangular/dist/restangular.min",

        // angular ui related
        angularRouter: "../lib/angular-ui-router/release/angular-ui-router.min",
        angularBootstrap: "../lib/angular-bootstrap/ui-bootstrap-tpls.min",
        angularGrid: "../lib/angular-grid/build/ng-grid.min"
    },

    shim: {
        "angular": {
            deps: ["jquery"],
            "exports": "angular"
        },

        "restangular": {
            deps: ["angular", "lodash"],
            "exports": "restangular"
        },

        "angularRouter": {
            deps: ["angular"],
            "exports": "angular-router"
        },

        "angularBootstrap": {
            deps: ["angular"],
            "exports": "angular-boostrap"
        },

        "angularMocks": {
            deps: ["angular"],
            "exports": "angular.mock"
        },

        "angularGrid": {
            deps: ["angular", "jquery"],
            "exports": "angular-grid"
        }
    },

    priority: [
        "angular"
    ]
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

require([
    "angular",
    "app",
    "routes"
], function(angular, app, routes) {
    var $html = angular.element(document.getElementsByTagName("html")[0]);

    angular.element().ready(function() {
        angular.resumeBootstrap([app["name"]]);
    });
});
