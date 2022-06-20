const Login = { template: '<login></login>' }
const LoginCustomer = { template: '<customerHomePage></customerHomePage>' }
const RegisterCustomer = {template: '<register></register>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Login},
	    { path: '/customerHomePage/', component: LoginCustomer},
		{ path: '/register/', component: RegisterCustomer}
	  ]
});

var app = new Vue({
	router,
	el: '#webApp'
});