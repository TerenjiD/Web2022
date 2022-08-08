const Login = { template: '<login></login>' }
const LoginCustomer = { template: '<customerHomePage></customerHomePage>' }
const RegisterCustomer = {template: '<register></register>'}
const LoginAdmin = {template: '<adminHomePage></adminHomePage>'}
const CreateManagerFacility = {template : '<managerForFacility></managerForFacility>'}
const LoginCoach = {template: '<coachHomePage></coachHomePage>'}
const LoginManager = {template: '<managerHomePage></managerHomePage>'}
const RegisterManager = {template : '<registerManager></registerManager>'}
const RegisterCoach = {template : '<registerCoach></registerCoach>'}
const ChangeManagerInfo = {template : '<changeInfoManager></changeInfoManager>'}
const ChangeCustomerInfo = {template : '<changeInfoCustomer></changeInfoCustomer>'}
const ChangeCoachInfo = {template : '<changeInfoCoach></changeInfoCoach>'}
const ChangeAdminInfo = {template : '<changeInfoAdmin></changeInfoAdmin>'}
const CreateFacility = {template : '<createFacility></createFacility>'}
const AddContent = {template : '<addContent></addContent>'}
const AddCoachToContent = {template : '<addCoachToTraining></addCoachToTraining>'}
const ChangeContent = {template : '<changeContent></changeContent>'}


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path : '/', component: Login},
        { path : '/customerHomePage/', component: LoginCustomer},
		{ path : '/adminHomePage/', component: LoginAdmin},
		{ path : '/coachHomePage/', component: LoginCoach},
		{ path : '/managerHomePage/', component: LoginManager},
        { path : '/register/', component: RegisterCustomer},
		{ path : '/adminHomePage/registerManager/',component: RegisterManager },
		{ path : '/adminHomePage/registerCoach/',component: RegisterCoach },
		{ path : '/managerHomePage/changeInfoManager/',component: ChangeManagerInfo },
		{ path : '/changeInfoCustomer',component: ChangeCustomerInfo },
		{ path : '/coachHomePage/changeInfoCoach/',component: ChangeCoachInfo },
		{ path : '/adminHomePage/changeInfoAdmin/',component: ChangeAdminInfo },
		{ path : '/adminHomePage/createFacility/',component: CreateFacility },
		{ path : '/adminHomePage/createFacility/managerForFacility/',component: CreateManagerFacility },
		{ path : '/managerHomePage/addContent/',component: AddContent },
		{ path : '/managerHomePage/addCoachToTraining/',component: AddCoachToContent },
		{ path : '/managerHomePage/changeContent/',component: ChangeContent }
	  ]
});

var app = new Vue({
	router,
	el: '#webApp'
});
