Vue.component("changeInfoManager",{
    data : function(){
        return{
            manager : null,
            managerDTO : null,
            facilities: null
        }
    },
    template:`
    <div>
        <form method="post">
                <table>
                        <tr><td>Username</td><td><input  type="text" id="username" v-model="managerDTO.username"></input></td></tr>
                        <tr><td>Password</td><td><input type="password" id="password" v-model="managerDTO.password" value="agdagag"/></td></tr>
                        <tr><td>Name</td><td><input type="text" id="name" v-model="managerDTO.name" value="agdagag"/></td></tr>
                        <tr><td>Lastname</td><td><input type="text" id="lastname" v-model="managerDTO.lastName" value="agdagag"/></td></tr>
                        <tr><td>Gender</td><td><select type="text" value='' id="gender" v-model="managerDTO.gender" >
                            <option>MALE</option>
                            <option>FEMALE</option>
                        </select></td></tr>
                        <tr><td>Date of birth</td><td><input type="text" id="dateOfBirth" v-model="managerDTO.dateOfBirth" value="agdagag"/></td></tr>
                        <tr><td>Facility</td><td ><select type="text" value='' id="facility" v-model="managerDTO.facility">
                                <option v-for="(p,index) in facilities">{{p.name}}</option></select></td></tr>
                        <tr><td><button  v-on:click = "changeData" style="padding: 7px 20px;
                                                                        background-color: aqua;" >Izmeni</button></td>
                        <td><button v-on:click= "returnToHome" style="padding: 7px 20px;
                                                                        background-color: aqua;">Vrati se</button></td></tr>
                </table>
        </form>
    </div>
    `,
    mounted() {
        axios.get('rest/facilities/').then(response => (this.facilities=response.data));
        axios.get('rest/managerHomePage/changeInfoManager').then(response => {
            this.managerDTO=response.data;
            
        });
    },
    methods : {
        changeData : function(event){
            axios.post('rest/managerHomePage/changeInfoManager/manager',managerDTO).then(response => console.log(data));
            event.preventDefault();
        }
    }
})