Vue.component("customerHomePage",{
    data: function() {
        return {
            customer : null
        }
    },
    template:`
    <button v-on:click = "logoutUser" style="padding: 7px 20px;
    background-color: aqua;" >Log out</button>
    `,
    methods: {
        logoutUser : function(){
            router.push("/")
        }
    }
})