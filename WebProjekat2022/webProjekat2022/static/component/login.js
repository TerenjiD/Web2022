Vue.component("login",{
    data: function(){
        return{
            loginDTO:{username:null,password:null,role:null}
        }
    },
    template:`
    <div>
        <form method="post">
            <table>
                <tr><td>Username</td><td><input type="text" v-model="loginDTO.username" id="username"  /></td></tr>
                <tr><td>Password</td><td><input type="password" v-model="loginDTO.password" id="password"/></td></tr>
                <tr><td><button v-on:click = "loginUser" style="padding: 7px 20px;
                background-color: aqua;">Login in</button></td></tr>
                
            </table>
            
        </form>
        <button v-on:click = "registerUser" style="padding: 7px 20px;
        background-color: aqua;">Register</button>
    </div>
    `,
    methods : {
        loginUser : function(event){
            
            axios.post('rest/customerHomePage',this.loginDTO).then(response =>{
                if(response.data != null){
                    
                    router.push('/customerHomePage/');
                    alert("Uspesno logovanje");
                }
                
                
                    
        });

            event.preventDefault();
        },
        registerUser : function(){
            router.push('/register/')
        }
    }
})