'use strict';

/**
 * @ngdoc overview
 * @name myWareHouseApp
 * @description
 * # myWareHouseApp
 *
 * Main module of the application.
 */


angular.module('serviceEndPoint',[]).factory('apiEndPoint', apiEndPoint);

function apiEndPoint() {

    var ENDPOINT_HOST = 'http://localhost:8181';
    var API_CONTEXT = "/datawarehouse/api";

    var apiURL =
        {
            UPLOAD_USER_CSV_URL: ENDPOINT_HOST + API_CONTEXT + '/uploaduserstories',
            FILE_SUMMARY_URL: ENDPOINT_HOST + API_CONTEXT + '/filesummary'
        }
    return apiURL;
};