Vue.component("register",{
    data: function (){
        return{
            userDTO:{username:null,password:null,name:null,lastName:null,gender:null,dateOfBirth:null}
        }
    },
    template:`
    <div>
        <form method="post">
                <table>
                        <tr><td>Username</td><td><input type="text" id="username" v-model="userDTO.username"/></td></tr>
                        <tr><td>Password</td><td><input type="password" id="password" v-model="userDTO.password"/></td></tr>
                        <tr><td>Name</td><td><input type="text" id="name" v-model="userDTO.name"/></td></tr>
                        <tr><td>Lastname</td><td><input type="text" id="lastname" v-model="userDTO.lastName"/></td></tr>
                        <tr><td>Gender</td><td><select type="text" value="" id="gender" v-model="userDTO.gender">
                            <option>Male</option>
                            <option>Terenji</option>
                        </select></td></tr>
                        <tr><td>Date of birth</td><td><input type="text" id="dateOfBirth" v-model="userDTO.dateOfBirth"/></td></tr>
                        <tr><td><button type="submit"  v-on:click = "addUser" style="padding: 7px 20px;
                                                                        background-color: aqua;" >Napravi</button></td>
                        <td><button v-on:click= "returnToHome" style="padding: 7px 20px;
                                                                        background-color: aqua;">Vrati se</button></td></tr>
                </table>
        </form>
    </div>
    `,
    methods : {
        
        addUser : function(event){
            axios.post('rest/',this.userDTO).then(response => {
                
                    alert("User added");
                
                }).catch(error => alert("User already exists"));
            event.preventDefault();
        },
        returnToHome : function(){
            router.push('/');
        }
    }
})