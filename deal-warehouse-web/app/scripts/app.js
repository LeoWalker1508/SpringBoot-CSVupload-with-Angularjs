'use strict';

/**
 * @ngdoc overview
 * @name myWareHouseApp
 * @description
 * # myWareHouseApp
 *
 * Main module of the application.
 */
angular.module('myWareHouseApp', ['blockUI',
  "ui.router",
  'uploadDealModule',
  'summaryModule',
  'serviceEndPoint',
  'TableModule'
])
  .config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/dashboard/upload");
    $stateProvider.state('base', {
      abstract: true,
      url: '',
      templateUrl: 'views/base.html'
    }).state('dashboard', {
      url: '/dashboard',
      parent: 'base',
      templateUrl: 'views/dashobard/dashboard.html'
    })
      .state('dealupload', {
        url: '/upload',
        parent: 'dashboard',
        templateUrl: 'views/dashobard/dealupload/dealupload.html',
        controller: 'DealUploadCtrl',
        controllerAs: 'dealUploadCtrlVM'
      })
      .state('uploadsummary', {
        url: '/uploadsummary',
        parent: 'dashboard',
        templateUrl: 'views/dashobard/summary/summary.html',
        controller: 'summaryCtrl',
        controllerAs: 'summaryCtrlVM'
      });

  });