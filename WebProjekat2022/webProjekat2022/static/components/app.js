const HomePage = { template: '<facilities></facilities>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: HomePage}
	  ]
});

var app = new Vue({
	router,
	el: '#webApp'
});
