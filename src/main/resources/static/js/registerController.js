'use strict';

App.controller('RegisterController', ['$scope', function ($scope) {
    var self = this;
    self.register = {id: null, email: '', name: '', lastName: '', password: '', confirmPassword: ''};

    self.submit = function () {
        if (self.product.id === null) {
            console.log('Saving New Product', self.product);
            self.createProduct(self.product);
        } else {
            self.updateProduct(self.product, self.product.id);
            console.log('Product updated with id ', self.product.id);
        }
        self.reset();
    };
    

    self.reset = function () {
        self.product = {id: null, name: '', price: '', description: '', unitsInStock: '0', imageURL: ''};
        $scope.myForm.$setPristine(); //reset Form
    };

}]);