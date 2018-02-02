'use strict';

/**
 * @ngdoc service
 * @name myWareHouseApp.warehousedeal.services
 * @description
 * # warehousedeal.services
 * Service in the myWareHouseApp.
 */
angular.module('myWareHouseApp')
    .service('wareHouseServices', function ($http, $q) {
        // AngularJS will instantiate a singleton by calling "new" on this function

        return {
            uploadWareHouseCSV: uploadWareHouseCSV,
            fileSummary: fileSummary

        }
        function uploadWareHouseCSV(file, apiURL) {
            //FormData, object of key/value pair for form fields and values
            var fileFormData = new FormData();
            fileFormData.append('file', file);
            var deffered = $q.defer();

            $http.post(apiURL, fileFormData, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).then(function (response) {
                deffered.resolve(response);

            }).catch(function (response) {
                deffered.reject(response);
            });

            return deffered.promise;;
        }

        function fileSummary(fileName, apiURL) {
            var deffered = $q.defer();

            var params = {
                'fileName': fileName
            };
            $http.get(apiURL, {params}).then(function (response) {
                deffered.resolve(response);

            }).catch(function (response) {
                deffered.reject(response);
            });

            return deffered.promise;;
        }
    });
