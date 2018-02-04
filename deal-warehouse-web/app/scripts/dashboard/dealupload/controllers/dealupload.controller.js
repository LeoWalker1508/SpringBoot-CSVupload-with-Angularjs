'use strict';

/**
 * @ngdoc function
 * @name myWareHouseApp.controller:DealUploadCtrl
 * @description
 * # DealUploadCtrl
 * Controller of the myWareHouseApp
 */

uploadDeal.controller('DealUploadCtrl',
  function ($scope, $timeout, wareHouseServices, apiEndPoint) {

    var dealUploadCtrlVM = this;
    dealUploadCtrlVM.uploadsummary = {};
    dealUploadCtrlVM.showError = false;
    dealUploadCtrlVM.showMsg = false;

    dealUploadCtrlVM.dealFileUpload = function () {


      var file = $scope.mycsvfile;
      var uploadUrl = apiEndPoint.UPLOAD_USER_CSV_URL, //Url of webservice/api/server
        promise = wareHouseServices.uploadWareHouseCSV(file, uploadUrl);

      promise.then(function (response) {
        dealUploadCtrlVM.showMsg = true;
        dealUploadCtrlVM.uploadsummary = response.data;
        // console.log(dealUploadCtrlVM.uploadsummary);

        $timeout(function () {
          dealUploadCtrlVM.showMsg = false;
        }, 2000);

      }, function (error) {
        // console.log(error);
        dealUploadCtrlVM.uploadsummary = {};
        dealUploadCtrlVM.showError = true;
        dealUploadCtrlVM.errorMsg = error.data.message

        $timeout(function () {
          dealUploadCtrlVM.showError = false;
        }, 2000);
        // console.log(dealUploadCtrlVM.errorMsg);
      })
    };


  });