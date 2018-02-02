'use strict';

/**
 * @ngdoc directive
 * @name myWareHouseApp.directive:dealUploadDirective
 * @description
 * # dealUploadDirective
 */
angular.module('TableModule',[])
    .directive('summaryTable', function ($parse) {
        var directive = {
            restrict: 'E',
            scope: {
                results: '='
            },
            templateUrl: 'views/common/table.html',
            link: link
        }
        // Link function will define the functionality of directive 
        function link(scope, element, attrs) {
            console.log(scope.results +" ------")
        }
        return directive
    });
