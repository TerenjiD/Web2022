Vue.component("registerManager",{
    data : function(){
        return{
            managerDTO : {username : null,password : null,name : null,lastname : null,gender : null,dateOfBirth : null,role : null,facility : null},
            facilities: null
        }
    },
    template:`
    <div>
        <form method="post">
                <table>
                <tr><td>Username</td><td><input type="text" id="username" v-model="managerDTO.username"/></td></tr>
                <tr><td>Password</td><td><input type="password" id="password" v-model="managerDTO.password"/></td></tr>
                <tr><td>Name</td><td><input type="text" id="name" v-model="managerDTO.name"/></td></tr>
                <tr><td>Lastname</td><td><input type="text" id="lastname" v-model="managerDTO.lastName"/></td></tr>
                <tr><td>Gender</td><td><select type="text" value="" id="gender" v-model="managerDTO.gender">
                    <option>MALE</option>
                    <option>FEMALE</option>
                </select></td></tr>
                <tr><td>Date of birth</td><td><input type="date" id="dateOfBirth" v-model="managerDTO.dateOfBirth"/></td></tr>
                <tr><td><button  v-on:click = "addUser" style="padding: 7px 20px;
                        background-color: aqua;" >Napravi</button></td>
                <td><button v-on:click= "returnToHome" style="padding: 7px 20px;
                        background-color: aqua;">Vrati se</button></td></tr>
                </table>
        </form>
    </div>
    `,
    mounted(){
        axios.get('rest/facilities/').then(response => (this.facilities=response.data))
    },
    methods : {
        addUser : function(event){
            axios.post('rest/adminHomePage/registerManager/',this.managerDTO).then(response => alert("Uspesno pravljenje"))
            .catch(error => alert("Neuspesno pravljenje menadzera"));
            event.preventDefault();
        },
        returnToHome : function(){
            router.push('/adminHomePage/')
        }
    }
})