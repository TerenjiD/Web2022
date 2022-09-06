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
const BuyMembership = {template : '<buyFitPass></buyFitPass>'}
const FacilityOpen = {template : '<FacilityOpen></FacilityOpen>'}
const ViewTraining = {template : '<viewTrainingCoach></viewTrainingCoach>'}
const AddComment = {template : '<putComment></putComment>'}
const ViewComments = {template : '<viewComments></viewComments>'}
const ViewCommentsAdmin = {template : '<commentsToViewAdmin></commentsToViewAdmin>'}
const ViewCommentsManager = {template : '<commentsToViewManager></commentsToViewManager>'}
const ViewCommentsForFacility = {template : '<viewCommentsForFacility></viewCommentsForFacility>'}


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
		{ path : '/managerHomePage/changeContent/',component: ChangeContent },
		{ path : '/customerHomePage/buyFitPass', component: BuyMembership},
		{ path : '/customerHomePage/FacilityOpen', component: FacilityOpen},
		{ path : '/coachHomePage/view', component: ViewTraining},
		{ path : '/customerHomePage/putComment', component: AddComment},
		{ path : '/adminHomePage/viewComments', component: ViewComments},
		{ path : '/adminHomePage/commentsToViewAdmin', component: ViewCommentsAdmin},
		{ path : '/managerHomePage/commentsToViewManager', component: ViewCommentsManager},
		{ path : '/customerHomePage/viewCommentsForFacility', component: ViewCommentsForFacility},
	  ]
});

var app = new Vue({
	router,
	el: '#webApp'
});
