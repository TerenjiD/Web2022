const Login = { template: '<login></login>' }
const LoginCustomer = { template: '<customerHomePage></customerHomePage>' }
const RegisterCustomer = {template: '<register></register>'}
const LoginAdmin = {template: '<adminHomePage></adminHomePage>'}
const LoginCoach = {template: '<coachHomePage></coachHomePage>'}
const LoginManager = {template: '<managerHomePage></managerHomePage>'}
const RegisterManager = {template : '<registerManager></registerManager>'}
const RegisterCoach = {template : '<registerCoach></registerCoach>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Login},
        { path: '/customerHomePage/', component: LoginCustomer},
		{ path: '/adminHomePage/', component: LoginAdmin},
		{ path: '/coachHomePage/', component: LoginCoach},
		{ path: '/managerHomePage/', component: LoginManager},
        { path: '/register/', component: RegisterCustomer},
		{ path : '/adminHomePage/registerManager/',component: RegisterManager },
		{ path : '/adminHomePage/registerCoach/',component: RegisterCoach }
	  ]
});

var app = new Vue({
	router,
	el: '#webApp'
});
