var cartApp = angular.module('cartApp', []);
cartApp.filter('range', function () {
    return function (input, min, max) {
        min = parseInt(min); //Make string input int
        max = parseInt(max);
        for (var i = min; i < max; i++)
            input.push(i);
        return input;
    };
});

cartApp.controller('cartCtrl', function ($scope, $http) {

    $scope.cartId = null;

    $scope.refreshCart = function (cartId) {
        console.log("co tu sie dzieje" + cartId);
        $http.get('/api/carts/cart/' + cartId)
            .success(function (data) {
                $scope.cart = data;
            });
    };
    $scope.changeItems = function (itemId) {
        var quantity = document.getElementById('quantitySelect' + itemId)[document.getElementById('quantitySelect' + itemId).selectedIndex].innerHTML;
        $http.put('/api/carts/cart/updateQuantity?idProd=' + itemId + "&quantity=" + quantity)
            .success(function (data) {
                $http.get('/api/carts/cart/get').success(function (data) {
                    $scope.refreshCart(data);
                });
            });
    };

    $scope.clearCart = function () {
        $http.delete('/api/carts/cart/' + $scope.cartId)
            .success(
                $http.get('/api/carts/cart/get').success(function (data) {
                    $scope.refreshCart(data);
                }));
    };

    $scope.initCartId = function (cartId) {
        console.log("inicjalizacja" + cartId);
        $scope.cartId = cartId;
        console.log("inicjalizacja kolejna" + $scope.cartId);
        $scope.refreshCart($scope.cartId);
    };

    $scope.addToCart = function (productId) {
        $http.put('/api/carts/cart/add/' + productId)
            .success(function (data) {
                $http.get('/api/carts/cart/get').success(function (data) {
                    $scope.refreshCart(data);
                });
                alert("Produkt pomyślnie dodany do koszyka!");
            });
    };
    $scope.removeFromCart = function (productId) {
        $http.put('/api/carts/cart/remove/' + productId)
            .success(function (data) {
                $http.get('/api/carts/cart/get').success(function (data) {
                    $scope.refreshCart(data);
                });
            });
    };
});
