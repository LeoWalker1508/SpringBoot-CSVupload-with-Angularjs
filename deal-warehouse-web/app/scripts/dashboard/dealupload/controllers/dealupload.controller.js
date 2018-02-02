'use strict';

/**
 * @ngdoc function
 * @name myWareHouseApp.controller:DealUploadCtrl
 * @description
 * # DealUploadCtrl
 * Controller of the myWareHouseApp
 */
var uploadDeal = angular.module('uploadDealModule', []);
uploadDeal.controller('DealUploadCtrl',
  function ($scope, $timeout, wareHouseServices) {

    var dealUploadCtrlVM = this;
    dealUploadCtrlVM.uploadsummary = {};

    dealUploadCtrlVM.showError = false;
    dealUploadCtrlVM.showMsg = false;

    dealUploadCtrlVM.dealFileUpload = function () {
      var file = $scope.mycsvfile;
      var uploadUrl = "http://localhost:8080/datawarehouse/api/uploaduserstories", //Url of webservice/api/server
        promise = wareHouseServices.uploadWareHouseCSV(file, uploadUrl);

      promise.then(function (response) {
        dealUploadCtrlVM.showMsg = true;
        dealUploadCtrlVM.uploadsummary = response.data;
        // console.log(dealUploadCtrlVM.uploadsummary);
        $timeout(function () {
          dealUploadCtrlVM.showMsg = false;
        }, 5000)

      }, function (error) {
        // console.log(error);

        dealUploadCtrlVM.uploadsummary = {};
        dealUploadCtrlVM.showError = true;
        dealUploadCtrlVM.errorMsg = error.data.message
        $timeout(function () {
          dealUploadCtrlVM.showError = false;
        }, 5000)

        // console.log(dealUploadCtrlVM.errorMsg);
      })
    };
  });