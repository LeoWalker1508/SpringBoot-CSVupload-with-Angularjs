'use strict';

/**
 * @ngdoc directive
 * @name myWareHouseApp.directive:dealUploadDirective
 * @description
 * # dealUploadDirective
 */
angular.module('myWareHouseApp')
  .directive('dealUpload', function ($parse) {
    return {
      restrict: 'A',
      // Link function will define the functionality of directive 
      link: function postData(scope, element, attrs) {
        console.log('this is the dealUploadDirective directive');
        var fileModel = $parse(attrs.dealUpload);
        var fileModelSetter = fileModel.assign;
        // Bind the change event of directive 
        element.bind('change', function () {
          // //Call apply on scope, it checks for value changes and reflect them on UI
          scope.$apply(function () {
            //set the model value
            fileModelSetter(scope, element[0].files[0])
          });
        })
      }
    };
  });
