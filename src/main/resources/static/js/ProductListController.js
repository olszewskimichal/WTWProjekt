var productApp = angular.module('productApp', []);

productApp.controller('productListCtrl', function ($scope, $http) {

    $scope.getProducts = function () {
        $http.get('/api/products')
            .success(function (data) {
                $scope.cart = data;
            });
    };
});