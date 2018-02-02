'use strict';

/**
 * @ngdoc overview
 * @name myWareHouseApp
 * @description
 * # myWareHouseApp
 *
 * Main module of the application.
 */
angular
  .module('myWareHouseApp', [
    "ui.router",
    'uploadDealModule',
    'summaryModule',
    'TableModule'
  ])
  .config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/dashboard", "/dashboard/searchscreen");
    $stateProvider.state('base', {
      abstract: true,
      url: '',
      templateUrl: 'views/base.html'
    })
      .state('dashboard', {
        url: '/dashboard',
        parent: 'base',
        templateUrl: 'views/dashobard/dashboard.html'
      })
      .state('dealupload', {
        url: '/dealupload',
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