'use strict';

/**
 * @ngdoc function
 * @name myWareHouseApp.controller:summaryCtrl
 * @description
 * # summaryCtrl
 * Controller of the myWareHouseApp
 */
var Deal = angular.module('summaryModule', []);
Deal.controller('summaryCtrl',
  function ($scope, wareHouseServices) {
    var summaryCtrlVM = this;

    summaryCtrlVM.fileName = "";

    summaryCtrlVM.doFileSummary = function () {
      console.log(summaryCtrlVM.fileName)
      var Url = "http://localhost:8080/datawarehouse/api/filesummary", //Url of webservice/api/server
        promise = wareHouseServices.fileSummary(summaryCtrlVM.fileName, Url);

      promise.then(function (response) {
        console.log(response)
        summaryCtrlVM.summary = response.data;

      }, function (error) {
        summaryCtrlVM.errorMsg = error.data.message
      })
    };
  });