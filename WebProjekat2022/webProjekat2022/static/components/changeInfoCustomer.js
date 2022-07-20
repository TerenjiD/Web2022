Vue.component("changeInfoCustomer",{
    data : function() {
        return {
            userDTO : []
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
                <option>MALE</option>
                <option>FEMALE</option>
            </select></td></tr>
            <tr><td>Date of birth</td><td><input type="text" id="dateOfBirth" v-model="userDTO.dateOfBirth"/></td></tr>
            <tr><td><button type="submit"  v-on:click = "changeCustomer" style="padding: 7px 20px;
                                                            background-color: aqua;" >Izmeni</button></td>
            <td><button v-on:click= "returnToCusHP" style="padding: 7px 20px;
                                                            background-color: aqua;">Vrati se</button></td></tr>
    </table>
    </form>
    </div>
    `,
    mounted(){
        axios.get('/rest/customerHomePage/changeInfoCustomer/').then(response => (this.userDTO=response.data))
    },
    methods: {
        changeCustomer : function(){
            axios.post('/rest/customerHomePage/changeInfoCustomer/customer',this.userDTO);
            router.push('/customerHomePage/')
        },
        returnToCusHP : function(){

        }
    }
})