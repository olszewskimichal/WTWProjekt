var productApp = angular.module('productApp', []);
productApp.filter('range', function () {
    return function (input, min, max) {
        min = parseInt(min); //Make string input int
        max = parseInt(max);
        for (var i = min; i <= max; i += min)
            input.push(i);
        return input;
    };
});
productApp.filter('pages', function () {
    return function (input, currentPage, totalPages) {
        currentPage = parseInt(currentPage);
        totalPages = parseInt(totalPages);
        var minPage = currentPage;
        var maxPage = totalPages;
        for (var i = minPage; i < maxPage; i++) {
            input.push(i);
        }
        return input;
    };
});



productApp.controller('productListCtrl', function ($scope, $http) {

    $scope.pageNumber = 0;
    $scope.pageSize = null;
    $scope.totalPages = null;

    $scope.getProducts = function () {
        $http.get('/api/products')
            .success(function (data) {
                $scope.data = data;
                $http.get('/api/products/get/totalPages').success(function (data2) {
                    $scope.totalPages = data2;
                    $scope.pageNumber = 0;
                    pageSize = document.getElementById('pageSizeSelect')[document.getElementById('pageSizeSelect').selectedIndex].innerHTML;
                });
            });
    };
    $scope.getTotalPages = function () {
        var quantity = document.getElementById('pageSizeSelect')[document.getElementById('pageSizeSelect').selectedIndex].innerHTML;
        $http.get('/api/products/get/totalPages?page=' + $scope.pageNumber + '&limit=' + quantity).success(function (data2) {
                $scope.totalPages = data2;
                return $scope.totalPages;
            }
        );
    };


    $scope.productFromPageMax = function (page, max) {
        var quantity = document.getElementById('pageSizeSelect')[document.getElementById('pageSizeSelect').selectedIndex].innerHTML;
        $http.get('/api/products?page=' + page + "&limit=" + quantity)
            .success(function (data) {
                $scope.pageNumber = page - 1;
                $scope.data = data;
            });
    }

    $scope.changePageSize = function () {
        var quantity = document.getElementById('pageSizeSelect')[document.getElementById('pageSizeSelect').selectedIndex].innerHTML;
        $http.get('/api/products?page=' + $scope.pageNumber + 1 + "&limit=" + quantity)
            .success(function (data) {
                $scope.data = data;
                $scope.getTotalPages();
            });
    };
});