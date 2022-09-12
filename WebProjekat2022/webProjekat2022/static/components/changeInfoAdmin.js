Vue.component("changeInfoAdmin",{
    data : function(){
        return{
            adminDTO : null
        }
    },
    template:
    `
    <div>
    <form method="post">
    <table>
            <tr><td>Username</td><td><input type="text" id="username" v-model="adminDTO.username"/></td></tr>
            <tr><td>Password</td><td><input type="password" id="password" v-model="adminDTO.password"/></td></tr>
            <tr><td>Name</td><td><input type="text" id="name" v-model="adminDTO.name"/></td></tr>
            <tr><td>Lastname</td><td><input type="text" id="lastname" v-model="adminDTO.lastName"/></td></tr>
            <tr><td>Gender</td><td><select type="text" value="" id="gender" v-model="adminDTO.gender">
                <option>MALE</option>
                <option>FEMALE</option>
            </select></td></tr>
            <tr><td>Date of birth</td><td><input type="date" id="dateOfBirth" v-model="adminDTO.dateOfBirth"/></td></tr>
            <tr><td><button type="submit"  v-on:click = "changeAdmin" style="padding: 7px 20px;
                                                            background-color: aqua;" >Izmeni</button></td>
            <td><button v-on:click= "returnToAdminHomePage" style="padding: 7px 20px;
                                                            background-color: aqua;">Vrati se</button></td></tr>
    </table>
    </form>
    </div>
    `,
    mounted(){
        axios.get('/rest/adminHomePage/changeInfoAdmin/').then(response => (this.adminDTO=response.data));
    },
    methods : {
        changeAdmin : function(event){
            axios.post('rest/adminHomePage/changeInfoAdmin/admin',this.adminDTO);
            router.push('/adminHomePage/')
        },
        returnToAdminHomePage : function(event){
            router.push('/adminHomePage/')
        }
    }
})