"use strict";

define(["angular", "app"], function(angular, app) {

    return app.config(["RestangularProvider", function(RestangularProvider) {
        // config the Restangular
        RestangularProvider.addResponseInterceptor(function (data, operation, what, url, response, deferred) {
            var extractedData = {};

            if (operation === "getList" || operation === "get") {
                // process when the operation is getList
                if (data._embedded) {
                    extractedData.data = data._embedded.data;
                } 

                if (data.page) {
                    extractedData.page = data.page;
                }

                if (data._links) {
                    extractedData.links = data._links;
                }
            } else if (operation === "post") {
                extractedData.data = data;
                extractedData.link = response.headers("Location");
            } else {
                extractedData.data = data;
            }
            
            return extractedData;
        });
    }]);

});