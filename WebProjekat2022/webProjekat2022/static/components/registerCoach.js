Vue.component("registerCoach",{
    data : function(){
        return{
            coachDTO : {username : null,password : null,name : null,lastname : null,gender : null,dateOfBirth : null,role : null,trainingHistory : null}
        }
    },
    template:`
    <div>
        <form method="post">
                <table>
                        <tr><td>Username</td><td><input type="text" id="username" v-model="coachDTO.username"/></td></tr>
                        <tr><td>Password</td><td><input type="password" id="password" v-model="coachDTO.password"/></td></tr>
                        <tr><td>Name</td><td><input type="text" id="name" v-model="coachDTO.name"/></td></tr>
                        <tr><td>Lastname</td><td><input type="text" id="lastname" v-model="coachDTO.lastName"/></td></tr>
                        <tr><td>Gender</td><td><select type="text" value="" id="gender" v-model="coachDTO.gender">
                            <option>MALE</option>
                            <option>FEMALE</option>
                        </select></td></tr>
                        <tr><td>Date of birth</td><td><input type="text" id="dateOfBirth" v-model="coachDTO.dateOfBirth"/></td></tr>
                        <tr><td>Training history</td><td><input type="text" id="trainingHistory" v-model="coachDTO.trainingHistory"/></td></tr>
                        <tr><td><button  v-on:click = "addUser" style="padding: 7px 20px;
                                                                        background-color: aqua;" >Napravi</button></td>
                        <td><button v-on:click= "returnToHome" style="padding: 7px 20px;
                                                                        background-color: aqua;">Vrati se</button></td></tr>
                </table>
        </form>
    </div>
    `,
    methods : {
        addUser : function(event){
            axios.post('rest/adminHomePage/registerCoach/',this.coachDTO)
            .then(response => alert("Uspesno pravljenje"))
            .catch(error => alert("Neuspesno pravljenje trenera"));
            event.preventDefault();
        },
        returnToHome : function(){
            router.push('/');
        }
    }
})