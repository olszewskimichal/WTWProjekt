'use strict';

App.controller('CustomerInfoController', ['$scope', function ($scope) {
    var self = this;
    self.customerInfo = {id: null, name: '', lastName: '', doorNo: '', flatNo: '', streetName: '', areaName: '',state: '',zipCode:'',phoneNumber:''};

}]);