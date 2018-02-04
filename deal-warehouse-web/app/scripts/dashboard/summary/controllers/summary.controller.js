 
  /**
   * Created by Bharat Singh 
   * @ngdoc function
   * @name myWareHouseApp.controller:summaryCtrl
   * @description
   * # summaryCtrl
   * Controller of the myWareHouseApp
   */
  summaryModule.controller('summaryCtrl',
    function ($scope, wareHouseServices, apiEndPoint) {
      var summaryCtrlVM = this;

      summaryCtrlVM.fileName = "";

      summaryCtrlVM.doFileSummary = function () {
        console.log(summaryCtrlVM.fileName)
        var Url = apiEndPoint.FILE_SUMMARY_URL, //Url of webservice/api/server
          promise = wareHouseServices.fileSummary(summaryCtrlVM.fileName, Url);

        promise.then(function (response) {
          console.log(response)
          summaryCtrlVM.summary = response.data;

        }, function (error) {
          summaryCtrlVM.errorMsg = error.data.message
        })
      };
    }); 