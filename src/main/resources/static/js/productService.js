'use strict';

App.factory('ProductService', ['$http', '$q', function ($http, $q) {

    return {

        fetchAllProducts: function () {
            return $http.get('http://localhost:8080/api/users')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching users');
                        return $q.reject(errResponse);
                    }
                );
        },

        createProduct: function (product) {
            return $http.post('http://localhost:8080/api/user/', product)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateProduct: function (product, id) {
            return $http.put('http://localhost:8080/api/product/' + id, product)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating user');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteProduct: function (id) {
            return $http.delete('http://localhost:8080/api/product/' + id)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting user');
                        return $q.reject(errResponse);
                    }
                );
        }

    };

}]);